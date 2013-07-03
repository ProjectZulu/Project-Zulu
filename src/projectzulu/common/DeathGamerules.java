package projectzulu.common;

import java.io.File;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ObfuscationHelper;

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

    public DeathGamerules loadConfiguration(File modConfigDirectory) {
        Configuration config = new Configuration(new File(modConfigDirectory, DefaultProps.configDirectory
                + DefaultProps.defaultConfigFile));
        config.load();
        String category = "General Controls.gamerule_settings";
        maxDropXP = config.get(category + ".Experience", "maxDropXP", 100,
                "Maximum XP dropped on Death. The rest is lost. 100 is vanilla default").getInt(100);

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
        createGameruleIfAbsent(gameRule, "dropInventory", "false");
        createGameruleIfAbsent(gameRule, "dropHotbar", "false");
        createGameruleIfAbsent(gameRule, "dropArmor", "false");
        createGameruleIfAbsent(gameRule, "dropXP", "false");
    }

    private void createGameruleIfAbsent(GameRules gameRule, String gameruleName, String value) {
        if (!gameRule.hasRule(gameruleName)) {
            gameRule.addGameRule(gameruleName, value);
        }
    }

    @ForgeSubscribe
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.entity instanceof EntityPlayerMP) {
            GameRules gameRules = event.entity.worldObj.getGameRules();
            boolean dropInventory = gameRules.getGameRuleBooleanValue("dropInventory");
            boolean dropHotbar = gameRules.getGameRuleBooleanValue("dropHotbar");
            boolean dropArmor = gameRules.getGameRuleBooleanValue("dropArmor");
            boolean dropXP = gameRules.getGameRuleBooleanValue("dropXP");

            EntityPlayerMP player = (EntityPlayerMP) event.entity;
            player.captureDrops = true;
            player.capturedDrops.clear();

            if (dropXP) {
                if (!player.worldObj.isRemote) {
                    int i = player.experienceLevel * 7;
                    i = i > 100 ? 100 : i;
                    while (i > 0) {
                        int j = EntityXPOrb.getXPSplit(i);
                        i -= j;
                        player.worldObj.spawnEntityInWorld(new EntityXPOrb(player.worldObj, player.posX, player.posY,
                                player.posZ, j));
                    }
                    player.experienceLevel = 0;
                    player.experienceTotal = 0;
                    player.experience = 0;
                }
            }

            if (dropArmor) {
                dropArmor(player);
            }

            if (dropInventory) {
                dropInventory(player);
            }

            if (dropHotbar) {
                dropHotbar(player);
            }

            player.captureDrops = false;
            int recentlyHit;
            try {
                recentlyHit = ObfuscationHelper.getCatchableFieldFromReflection("field_70718_bc", EntityLiving.class,
                        player, Integer.class);
            } catch (NoSuchFieldException e) {
                recentlyHit = ObfuscationHelper.getFieldFromReflection("recentlyHit", EntityLiving.class, player,
                        Integer.class);
            }
            PlayerDropsEvent dropEvent = new PlayerDropsEvent(player, event.source, player.capturedDrops,
                    recentlyHit > 0);
            if (!MinecraftForge.EVENT_BUS.post(dropEvent)) {
                for (EntityItem item : player.capturedDrops) {
                    player.joinEntityItemWithWorld(item);
                }
            }
        }
    }

    private void dropArmor(EntityPlayerMP player) {
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
                        player.inventory.player.dropPlayerItemWithRandomChoice(itemStack, true);
                    }
                    player.inventory.armorInventory[slot] = null;
                    countDrops++;
                }
            }
        }
    }

    private void dropInventory(EntityPlayerMP player) {
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
                            player.inventory.player.dropPlayerItemWithRandomChoice(itemStack, true);
                        }
                        player.inventory.mainInventory[slot] = null;
                        countDrops++;
                    }
                }
            }
        }
    }

    public void dropHotbar(EntityPlayerMP player) {
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
                        player.inventory.player.dropPlayerItemWithRandomChoice(itemStack, true);
                    }
                    player.inventory.mainInventory[slot] = null;
                    countDrops++;
                }
            }
        }
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
