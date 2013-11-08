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
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.tombstone.TileEntityTombstone;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.core.ProjectZuluLog;

import com.google.common.base.Optional;

public class DeathGamerules {
    int maxDropXP = 100;

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

    boolean dropArmorDefault = false;
    boolean dropInventoryDefault = false;
    boolean dropHotbarDefault = false;
    boolean dropXPDefault = false;

    public DeathGamerules loadConfiguration(File modConfigDirectory) {
        Configuration config = new Configuration(new File(modConfigDirectory, DefaultProps.configDirectory
                + DefaultProps.defaultConfigFile));
        config.load();
        tombstoneOnDeath = config.get("General Controls", "Drop Tombstone On Death", tombstoneOnDeath).getBoolean(
                tombstoneOnDeath);
        tombstoneAbsorbDrops = config.get("General Controls", "Tombstone Absorb Drops", tombstoneAbsorbDrops)
                .getBoolean(tombstoneAbsorbDrops);
        doDropEvent = config.get("General Controls", "doDropEvent", doDropEvent).getBoolean(doDropEvent);

        String category = "General Controls.gamerule_settings";
        maxDropXP = config.get(category + ".Experience", "maxDropXP", 100,
                "Maximum XP dropped on Death. The rest is lost. 100 is vanilla default").getInt(100);

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

    @ForgeSubscribe
    public void worldLoad(WorldEvent.Load event) {
        GameRules gameRule = event.world.getGameRules();
        createGameruleIfAbsent(gameRule, "dropInventory", dropInventoryDefault);
        createGameruleIfAbsent(gameRule, "dropHotbar", dropHotbarDefault);
        createGameruleIfAbsent(gameRule, "dropArmor", dropArmorDefault);
        createGameruleIfAbsent(gameRule, "dropXP", dropXPDefault);
    }

    private void createGameruleIfAbsent(GameRules gameRule, String gameruleName, boolean value) {
        if (!gameRule.hasRule(gameruleName)) {
            gameRule.addGameRule(gameruleName, Boolean.toString(value));
        }
        ProjectZuluLog.info("Gamerule %s is set to %s", gameruleName, gameRule.getGameRuleBooleanValue(gameruleName));
    }

    @ForgeSubscribe
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
                tombstone.setSignString(event.source.getDeathMessage((EntityPlayer) event.entity).toString());
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
                    xpDropped = player.experienceLevel * 7;
                    xpDropped = xpDropped > maxDropXP ? maxDropXP : xpDropped;
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
                    chunkCoordinate.get().posZ, BlockList.tombstone.get().blockID);
            TileEntity tileEntity = player.worldObj.getBlockTileEntity(chunkCoordinate.get().posX,
                    chunkCoordinate.get().posY, chunkCoordinate.get().posZ);
            if (tileEntity != null && tileEntity instanceof TileEntityTombstone) {
                ProjectZuluLog.debug(Level.INFO, "Tombstone placed at [%s, %s, %s]", chunkCoordinate.get().posX,
                        chunkCoordinate.get().posY, chunkCoordinate.get().posZ);
                return (TileEntityTombstone) tileEntity;
            }
        }
        return null;
    }

    private Optional<ChunkCoordinates> findValidTombstoneLocation(EntityPlayer player) {
        final int maxRadius = 100;
        for (int radius = 0; radius < maxRadius; radius++) {
            for (int xOffset = 0; xOffset < radius + 1; xOffset++) {
                for (int zOffset = 0; zOffset < radius; zOffset++) {
                    final int yIncremenet = 5;
                    int upperYOffset = yIncremenet;
                    int lowerYOffset = 0;
                    final int maxYOffset = 30;
                    while (upperYOffset <= maxYOffset) {
                        for (int yOffset = lowerYOffset; yOffset < upperYOffset; yOffset++) {
                            ChunkCoordinates chunkCoordinates = new ChunkCoordinates((int) (player.posX + xOffset),
                                    (int) (player.posY + yOffset), (int) (player.posZ + zOffset));
                            if (isLocationValid(player, chunkCoordinates.posX, chunkCoordinates.posY,
                                    chunkCoordinates.posZ)) {
                                return Optional.of(chunkCoordinates);
                            }
                        }

                        for (int yOffset = -lowerYOffset; yOffset > -upperYOffset; yOffset--) {
                            ChunkCoordinates chunkCoordinates = new ChunkCoordinates((int) (player.posX + xOffset),
                                    (int) (player.posY + yOffset), (int) (player.posZ + zOffset));
                            if (isLocationValid(player, chunkCoordinates.posX, chunkCoordinates.posY,
                                    chunkCoordinates.posZ)) {
                                return Optional.of(chunkCoordinates);
                            }
                        }
                        lowerYOffset = upperYOffset;
                        upperYOffset += yIncremenet;
                    }
                }
            }
        }
        return Optional.absent();
    }

    private boolean isLocationValid(EntityPlayer player, int posX, int posY, int posZ) {
        if (player.worldObj.isAirBlock(posX, posY, posZ) && player.worldObj.isBlockOpaqueCube(posX, posY - 1, posZ)) {
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

            if (itemStack != null) {
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

                if (itemStack != null) {
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
                        player.inventory.armorInventory[slot] = null;
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

            if (itemStack != null) {
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
                    player.inventory.armorInventory[slot] = null;
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

    private void shuffleArray(int[] array, Random random) {
        for (int i = array.length - 1; i >= 0; i--) {
            int index = random.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
}
