package projectzulu.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.tombstone.TileEntityTombstone;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.core.ProjectZuluLog;

import com.google.common.base.Optional;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DeathGamerules {
    int maxDropXP = 100;
    int percKeptXp = 0;

    int armorDeathDamage = 0;
    int inventoryDeathDamage = 0;
    int hotbarDeathDamage = 0;

    int armorDropDamage = 0;
    int inventoryDropDamage = 0;
    int hotbarDropDamage = 0;

    int armorDropChance = 100;
    int inventoryDropChance = 100;
    int hotbarDropChance = 100;

    int armorMaxDrop = 0;
    int inventoryMaxDrop = 0;
    int hotbarMaxDrop = 0;

    boolean tombstoneOnDeath = true;
    boolean tombstoneAbsorbDrops = true;
    boolean doDropEvent = true;

    boolean keepInventoryDefault = false;
    boolean dropArmorDefault = false;
    boolean dropInventoryDefault = false;
    boolean dropHotbarDefault = false;
    boolean dropXPDefault = false;
    private ExperienceRedistributor redistributor;
    private ItemBlacklist itemBlacklist;

    public DeathGamerules() {
        redistributor = new ExperienceRedistributor();
        FMLCommonHandler.instance().bus().register(redistributor);
    }

    public DeathGamerules loadConfiguration(File modConfigDirectory) {
        itemBlacklist = new ItemBlacklist();
        Configuration config = new Configuration(new File(modConfigDirectory, DefaultProps.configDirectory
                + DefaultProps.defaultConfigFile));
        config.load();
        itemBlacklist.loadFromConfig(config);
        tombstoneOnDeath = config.get("General Controls", "Drop Tombstone On Death", tombstoneOnDeath).getBoolean(
                tombstoneOnDeath);
        tombstoneAbsorbDrops = config.get("General Controls", "Tombstone Absorb Drops", tombstoneAbsorbDrops)
                .getBoolean(tombstoneAbsorbDrops);
        doDropEvent = config.get("General Controls", "doDropEvent", doDropEvent).getBoolean(doDropEvent);

        String category = "General Controls.gamerule_settings";
        maxDropXP = config
                .get(category + ".Experience", "maxDropXP", 100,
                        "Maximum amount XP dropped on Death. The rest is lost if not kept via percKeptXp. 100 is vanilla default")
                .getInt(100);
        keepInventoryDefault = config.get(category, "keepInventoryDefault", false,
                "The Default settings for the keepInventory gamerule").getBoolean(false);
        Property keptXpProperty = config.get(category + ".Experience", "percKeptXp", 0,
                "Percentage of XP (minus dropped amount) kept with the player on respawn.");
        percKeptXp = keptXpProperty.getInt();
        if (percKeptXp < 0 || percKeptXp > 100) {
            percKeptXp = percKeptXp < 0 ? 0 : percKeptXp > 100 ? 100 : percKeptXp;
            keptXpProperty.set(percKeptXp);
        }
        dropArmorDefault = config.get(category + ".Armor", "isEnabledDefault", false,
                "The Default settings for the dropArmor gamerule").getBoolean(false);
        dropInventoryDefault = config.get(category + ".Inventory", "isEnabledDefault", false,
                "The Default settings for the dropInventory gamerule").getBoolean(false);
        dropHotbarDefault = config.get(category + ".Hotbar", "isEnabledDefault", false,
                "The Default settings for the dropHotbar gamerule").getBoolean(false);
        dropXPDefault = config.get(category + ".Experience", "isEnabledDefault", false,
                "The Default settings for the dropXP gamerule").getBoolean(false);

        armorDeathDamage = handlePropMinMax(config.get(category + ".Armor", "armorDeathDamage", 0,
                "Percentage of Max durability dealth on Death to armor slot items"), 0, 0, 100);
        inventoryDeathDamage = handlePropMinMax(config.get(category + ".Inventory", "inventoryDeathDamage", 0,
                "Percentage of Max durability dealth on Death to inventory slot items"), 0, 0, 100);
        hotbarDeathDamage = handlePropMinMax(config.get(category + ".Hotbar", "hotbarDeathDamage", 0,
                "Percentage of Max durability dealth on Death to hotbar slot items"), 0, 0, 100);

        armorDropDamage = handlePropMinMax(config.get(category + ".Armor", "armorDropDamage", 0,
                "Percentage of Max durability dealth on Drop to armor slot items"), 0, 0, 100);
        inventoryDropDamage = handlePropMinMax(config.get(category + ".Inventory", "inventoryDropDamage", 0,
                "Percentage of Max durability dealth on Drop to inventory slot items"), 0, 0, 100);
        hotbarDropDamage = handlePropMinMax(config.get(category + ".Hotbar", "hotbarDropDamage", 0,
                "Percentage of Max durability dealth on Drop to hotbar slot items"), 0, 0, 100);

        armorDropChance = handlePropMinMax(config.get(category + ".Armor", "armorDropChance", 100,
                "Chance that each armor slot item will drop on death"), 100, 0, 100);
        inventoryDropChance = handlePropMinMax(config.get(category + ".Inventory", "inventoryDropChance", 100,
                "Chance that each inventory slot item will drop on death"), 100, 0, 100);
        hotbarDropChance = handlePropMinMax(config.get(category + ".Hotbar", "hotbarDropChance", 100,
                "Chance that each hotbar slot item will drop on death"), 100, 0, 100);

        armorMaxDrop = handlePropMinMax(config.get(category + ".Armor", "armorMaxDrop", 0,
                "Max number of armor slot items that can drop on death."), 0, 0, null);
        inventoryMaxDrop = handlePropMinMax(config.get(category + ".Inventory", "inventoryMaxDrop", 0,
                "Max number of inventory slot items that can drop on death"), 0, 0, null);
        hotbarMaxDrop = handlePropMinMax(config.get(category + ".Hotbar", "hotbarMaxDrop", 0,
                "Max number of hotbar slot items that can drop on death"), 0, 0, null);
        config.save();
        return this;
    }

    private int handlePropMinMax(Property prop, int value, Integer min, Integer max) {
        value = prop.getInt(value);
        if (min != null && value < min) {
            prop.set(min);
            value = min;
        } else if (max != null && value > max) {
            prop.set(max);
            value = max;
        }
        return value;
    }

    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event) {
        GameRules gameRule = event.world.getGameRules();
        if (createGameruleIfAbsent(gameRule, "pzKeepInventory", keepInventoryDefault)) {
            gameRule.setOrCreateGameRule("keepInventory", Boolean.toString(keepInventoryDefault));
        }
        createGameruleIfAbsent(gameRule, "dropInventory", dropInventoryDefault);
        createGameruleIfAbsent(gameRule, "dropHotbar", dropHotbarDefault);
        createGameruleIfAbsent(gameRule, "dropArmor", dropArmorDefault);
        createGameruleIfAbsent(gameRule, "dropXP", dropXPDefault);
    }

    private boolean createGameruleIfAbsent(GameRules gameRule, String gameruleName, boolean value) {
        boolean added = false;
        if (!gameRule.hasRule(gameruleName)) {
            gameRule.addGameRule(gameruleName, Boolean.toString(value));
            added = true;
        }
        ProjectZuluLog.info("Gamerule %s is set to %s", gameruleName, gameRule.getGameRuleBooleanValue(gameruleName));
        return added;
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.entity instanceof EntityPlayerMP) {
            GameRules gameRules = event.entity.worldObj.getGameRules();
            boolean dropInventory = gameRules.getGameRuleBooleanValue("dropInventory");
            boolean dropHotbar = gameRules.getGameRuleBooleanValue("dropHotbar");
            boolean dropArmor = gameRules.getGameRuleBooleanValue("dropArmor");
            boolean dropXP = gameRules.getGameRuleBooleanValue("dropXP");

            EntityPlayer player = (EntityPlayer) event.entity;

            TileEntityTombstone tombstone = tombstoneOnDeath ? placeTombstone(player) : null;
            if (tombstone != null) {
                tombstone.setSignString(event.source.func_151519_b((EntityPlayer) event.entity).toString());
            }

            if (!dropInventory && !dropHotbar && !dropArmor && !dropXP) {
                return;
            }

            player.captureDrops = true;
            player.capturedDrops.clear();

            /* Get items/XP to drop and clear them from Player */
            int xpDropped = 0;
            if (dropXP) {
                if (!player.worldObj.isRemote) {
                    xpDropped = player.experienceTotal;
                    xpDropped = xpDropped > maxDropXP ? maxDropXP : xpDropped;
                    int keptXp = (player.experienceTotal - xpDropped) * percKeptXp / 100;
                    redistributor.addExpereince(player, keptXp >= 0 ? keptXp : 0);
                    player.experienceLevel = 0;
                    player.experienceTotal = 0;
                    player.experience = 0;
                }
            }

            List<ItemStack> itemsToDrop = new ArrayList<ItemStack>();
            if (dropArmor) {
                itemsToDrop.addAll(dropArmor(player));
            }

            if (dropInventory) {
                itemsToDrop.addAll(dropInventory(player));
            }

            if (dropHotbar) {
                itemsToDrop.addAll(dropHotbar(player));
            }

            dropItems(player, itemsToDrop);
            boolean isCancelled = false;
            if (doDropEvent) {
                PlayerDropsEvent dropEvent = createPlayerDropEvent(player, event.source, player.capturedDrops);
                isCancelled = MinecraftForge.EVENT_BUS.post(dropEvent);
            }
            player.captureDrops = false;
            if (!isCancelled) {
                /* Handler actually Dropping Items or Placing them in Tombstone */
                if (tombstoneAbsorbDrops && tombstone != null) {
                    for (EntityItem entityItem : player.capturedDrops) {
                        tombstone.addDrop(entityItem.getEntityItem());
                    }
                    tombstone.experience = xpDropped;
                } else {
                    if (tombstoneAbsorbDrops) {
                        ProjectZuluLog.warning("Tombstone could not be placed so items dropping normally.");
                    }
                    while (xpDropped > 0) {
                        int j = EntityXPOrb.getXPSplit(xpDropped);
                        xpDropped -= j;
                        player.worldObj.spawnEntityInWorld(new EntityXPOrb(player.worldObj, player.posX, player.posY,
                                player.posZ, j));
                    }
                    for (EntityItem item : player.capturedDrops) {
                        player.joinEntityItemWithWorld(item);
                    }
                }
            } else {
                ProjectZuluLog.warning("Player drop event was cancelled, so items will not be dropped per convention."
                        + "Results may not desireable, consider disabling 'doDropEvent' in the config.");
            }
        }
    }

    private void dropItems(EntityPlayer player, List<ItemStack> drops) {
        for (ItemStack itemDrop : drops) {
            player.inventory.player.dropPlayerItemWithRandomChoice(itemDrop, true);
        }
    }

    private PlayerDropsEvent createPlayerDropEvent(EntityPlayer player, DamageSource damageSource,
            ArrayList<EntityItem> drops) {
        int recentlyHit;
        try {
            recentlyHit = ObfuscationHelper.getCatchableFieldFromReflection("field_70718_bc", EntityLivingBase.class,
                    player, Integer.class);
        } catch (NoSuchFieldException e) {
            recentlyHit = ObfuscationHelper.getFieldFromReflection("recentlyHit", EntityLivingBase.class, player,
                    Integer.class);
        }
        return new PlayerDropsEvent(player, damageSource, player.capturedDrops, recentlyHit > 0);
    }

    private TileEntityTombstone placeTombstone(EntityPlayer player) {
        Optional<ChunkCoordinates> chunkCoordinate = findValidTombstoneLocation(player);
        if (chunkCoordinate.isPresent()) {
            /* Place a Tombstone */
            player.worldObj.setBlock(chunkCoordinate.get().posX, chunkCoordinate.get().posY,
                    chunkCoordinate.get().posZ, BlockList.tombstone.get());
            TileEntity tileEntity = player.worldObj.getTileEntity(chunkCoordinate.get().posX,
                    chunkCoordinate.get().posY, chunkCoordinate.get().posZ);
            StringBuilder sb = new StringBuilder();
            sb.append("Tombstone summoned to mark the 'passing' of ").append(player.getCommandSenderName()).append(" at [");
            sb.append(chunkCoordinate.get().posX).append(", ");
            sb.append(chunkCoordinate.get().posY).append(", ");
            sb.append(chunkCoordinate.get().posZ).append("]");
            player.addChatMessage(new ChatComponentText(sb.toString()));
            if (tileEntity != null && tileEntity instanceof TileEntityTombstone) {
                ProjectZuluLog.debug(Level.INFO, sb.toString());
                return (TileEntityTombstone) tileEntity;
            }
        }
        ProjectZuluLog.warning("Failed to find location to place tombstone at player location {X:%s, Y:%s, Z:%s}",
                player.posX, player.posY, player.posZ);
        return null;
    }

    private Optional<ChunkCoordinates> findValidTombstoneLocation(EntityPlayer player) {
        final int maxRadius = 100;
        /** Search an increasing square box (only check edge) for a valid location */
        for (int radius = 0; radius < maxRadius; radius++) {
            List<ChunkCoordinates> validLocations = new ArrayList<ChunkCoordinates>();
            validLocations.addAll(searchXPlaneAt(-radius, radius, player));
            validLocations.addAll(searchXPlaneAt(radius, radius, player));
            validLocations.addAll(searchZPlaneAt(-radius, radius, player));
            validLocations.addAll(searchZPlaneAt(radius, radius, player));
            validLocations.addAll(searchYPlaneAt(-radius, radius, player));
            validLocations.addAll(searchYPlaneAt(radius, radius, player));
            ChunkCoordinates closestPoint = null;
            float bestDistance = 0;
            for (ChunkCoordinates chunkCoordinates : validLocations) {
                if (closestPoint != null) {
                    float distance = chunkCoordinates.getDistanceSquared((int) player.posX, (int) player.posY,
                            (int) player.posZ);
                    if (distance < bestDistance) {
                        bestDistance = distance;
                        closestPoint = chunkCoordinates;
                    }
                } else {
                    closestPoint = chunkCoordinates;
                    bestDistance = closestPoint.getDistanceSquared((int) player.posX, (int) player.posY,
                            (int) player.posZ);
                }
            }
            if (closestPoint != null) {
                return Optional.of(closestPoint);
            }
        }
        return Optional.absent();
    }

    private List<ChunkCoordinates> searchXPlaneAt(int xOffset, int radius, EntityPlayer player) {
        List<ChunkCoordinates> matches = new ArrayList<ChunkCoordinates>();
        for (int zOffset = -radius; zOffset <= radius; zOffset++) {
            for (int yOffset = -radius; yOffset <= radius; yOffset++) {
                ChunkCoordinates chunkCoordinates = new ChunkCoordinates((int) (player.posX + xOffset),
                        (int) (player.posY + yOffset), (int) (player.posZ + zOffset));
                if (isLocationValid(player, chunkCoordinates.posX, chunkCoordinates.posY, chunkCoordinates.posZ)) {
                    matches.add(chunkCoordinates);
                }
            }
        }
        return matches;
    }

    private List<ChunkCoordinates> searchZPlaneAt(int zOffset, int radius, EntityPlayer player) {
        List<ChunkCoordinates> matches = new ArrayList<ChunkCoordinates>();
        for (int xOffset = -radius; xOffset <= radius; xOffset++) {
            for (int yOffset = -radius; yOffset <= radius; yOffset++) {
                ChunkCoordinates chunkCoordinates = new ChunkCoordinates((int) (player.posX + xOffset),
                        (int) (player.posY + yOffset), (int) (player.posZ + zOffset));
                if (isLocationValid(player, chunkCoordinates.posX, chunkCoordinates.posY, chunkCoordinates.posZ)) {
                    matches.add(chunkCoordinates);
                }
            }
        }
        return matches;
    }

    private List<ChunkCoordinates> searchYPlaneAt(int yOffset, int radius, EntityPlayer player) {
        List<ChunkCoordinates> matches = new ArrayList<ChunkCoordinates>();
        for (int xOffset = -radius; xOffset <= radius; xOffset++) {
            for (int zOffset = -radius; zOffset <= radius; zOffset++) {
                ChunkCoordinates chunkCoordinates = new ChunkCoordinates((int) (player.posX + xOffset),
                        (int) (player.posY + yOffset), (int) (player.posZ + zOffset));
                if (isLocationValid(player, chunkCoordinates.posX, chunkCoordinates.posY, chunkCoordinates.posZ)) {
                    matches.add(chunkCoordinates);
                }
            }
        }
        return matches;
    }

    private boolean isLocationValid(EntityPlayer player, int posX, int posY, int posZ) {
        if (player.worldObj.isAirBlock(posX, posY, posZ) && player.worldObj.getBlock(posX, posY - 1, posZ).isOpaqueCube()) {
            return true;
        }
        return false;
    }

    private List<ItemStack> dropArmor(EntityPlayer player) {
        List<ItemStack> itemsToDrop = new ArrayList<ItemStack>();

        /* Array to determine the order the inventory array is processed. */
        int[] placeArray = new int[player.inventory.armorInventory.length];
        for (int i = 0; i < placeArray.length; i++) {
            placeArray[i] = i;
        }

        shuffleArray(placeArray, player.worldObj.rand);
        int countDrops = 0;
        for (int i = 0; i < placeArray.length; ++i) {
            int slot = placeArray[i];
            ItemStack itemStack = player.inventory.armorInventory[slot];
            if (itemStack == null || itemBlacklist.isItemBlacklisted(itemStack)) {
                continue;
            }
            boolean shouldDrop = false;
            if ((armorMaxDrop == 0 || countDrops < armorMaxDrop)
                    && (armorDropChance - player.worldObj.rand.nextInt(100) >= 1)) {
                shouldDrop = true;
            }
            int percentDamage = shouldDrop ? armorDeathDamage + armorDropDamage : armorDeathDamage;
            percentDamage = percentDamage > 100 ? 100 : percentDamage;
            itemStack.attemptDamageItem(itemStack.getMaxDamage() * percentDamage / 100, player.worldObj.rand);
            boolean itemDestroyed = itemStack.isItemStackDamageable()
                    && itemStack.getItemDamage() > itemStack.getMaxDamage();
            if (itemDestroyed) {
                player.inventory.armorInventory[slot] = null;
            }

            if (shouldDrop) {
                if (!itemDestroyed) {
                    itemsToDrop.add(itemStack);
                }
                player.inventory.armorInventory[slot] = null;
                countDrops++;
            }

        }
        return itemsToDrop;
    }

    private List<ItemStack> dropInventory(EntityPlayer player) {
        List<ItemStack> itemsToDrop = new ArrayList<ItemStack>();

        if (player.inventory.mainInventory.length > 8) {
            /* Array to determine the order the inventory array is processed. */
            int[] placeArray = new int[player.inventory.mainInventory.length - 9];
            for (int i = 0; i < placeArray.length; i++) {
                placeArray[i] = i + 9;
            }
            shuffleArray(placeArray, player.worldObj.rand);

            int countDrops = 0;
            for (int i = 0; i < placeArray.length; ++i) {
                int slot = placeArray[i];
                ItemStack itemStack = player.inventory.mainInventory[slot];
                if (itemStack == null || itemBlacklist.isItemBlacklisted(itemStack)) {
                    continue;
                }
                boolean shouldDrop = false;
                if ((inventoryMaxDrop == 0 || countDrops < inventoryMaxDrop)
                        && inventoryDropChance - player.worldObj.rand.nextInt(100) >= 1) {
                    shouldDrop = true;
                }
                int percentDamage = shouldDrop ? inventoryDeathDamage + inventoryDropDamage : inventoryDeathDamage;
                percentDamage = percentDamage > 100 ? 100 : percentDamage;
                itemStack.attemptDamageItem(itemStack.getMaxDamage() * percentDamage / 100, player.worldObj.rand);

                boolean itemDestroyed = itemStack.isItemStackDamageable()
                        && itemStack.getItemDamage() > itemStack.getMaxDamage();
                if (itemDestroyed) {
                    player.inventory.mainInventory[slot] = null;
                }

                if (shouldDrop) {
                    if (!itemDestroyed) {
                        itemsToDrop.add(itemStack);
                    }
                    player.inventory.mainInventory[slot] = null;
                    countDrops++;
                }

            }
        }
        return itemsToDrop;
    }

    private List<ItemStack> dropHotbar(EntityPlayer player) {
        List<ItemStack> itemsToDrop = new ArrayList<ItemStack>();

        int inventorySize = player.inventory.mainInventory.length > 9 ? 9 : player.inventory.mainInventory.length;
        /* Array to determine the order the inventory array is processed. */
        int[] placeArray = new int[inventorySize];
        for (int i = 0; i < placeArray.length; i++) {
            placeArray[i] = i;
        }
        shuffleArray(placeArray, player.worldObj.rand);

        int countDrops = 0;
        for (int i = 0; i < placeArray.length; ++i) {
            int slot = placeArray[i];
            ItemStack itemStack = player.inventory.mainInventory[slot];
            if (itemStack == null || itemBlacklist.isItemBlacklisted(itemStack)) {
                continue;
            }
            boolean shouldDrop = false;
            if ((hotbarMaxDrop == 0 || countDrops < hotbarMaxDrop)
                    && hotbarDropChance - player.worldObj.rand.nextInt(100) >= 1) {
                shouldDrop = true;
            }
            int percentDamage = shouldDrop ? hotbarDeathDamage + hotbarDropDamage : hotbarDeathDamage;
            percentDamage = percentDamage > 100 ? 100 : percentDamage;
            itemStack.attemptDamageItem(itemStack.getMaxDamage() * percentDamage / 100, player.worldObj.rand);

            boolean itemDestroyed = itemStack.isItemStackDamageable()
                    && itemStack.getItemDamage() > itemStack.getMaxDamage();
            if (itemDestroyed) {
                player.inventory.mainInventory[slot] = null;
            }

            if (shouldDrop) {
                if (!itemDestroyed) {
                    itemsToDrop.add(itemStack);
                }
                player.inventory.mainInventory[slot] = null;
                countDrops++;
            }

        }
        return itemsToDrop;
    }

    private void shuffleArray(int[] array, Random random) {
        for (int i = array.length - 1; i >= 0; i--) {
            int index = random.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
}
