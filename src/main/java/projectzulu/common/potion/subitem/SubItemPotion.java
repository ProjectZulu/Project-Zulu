package projectzulu.common.potion.subitem;

import java.util.Iterator;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import projectzulu.common.potion.PotionParser;

public abstract class SubItemPotion {

    public final Item item;
    public final int subID;
    public final String baseName;

    SubItemPotion(Item item, int subID, String baseName) {
        this.subID = subID;
        this.item = item;
        this.baseName = baseName;
    }

    public String getDisplayName(ItemStack itemStack) {
        return StatCollector.translateToLocal(baseName).trim() + " Potion";
    }

    public abstract void register();

    public abstract boolean hasPotionEffects(ItemStack itemStack);

    /**
     * Potion Result between Ingredient and Brewing Stack. Return null if no result is viable.
     * 
     * @param ingredient Ingredient that is being brewed with potion
     * @param brewingStack Current Potion
     * @return Resulting Potion, null if Items should not be brewed
     */
    public ItemStack getPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        return null;
    }

    /**
     * 
     * @return Return Collections.emptyList if No PotionEffects are relevent
     */
    public abstract List<PotionEffect> getPotionEffects(int damageMeta);

    /**
     * 
     * @return Return Collections.emptyList if No PotionEffects are relevent
     */
    public List<PotionEffect> getPotionEffects(ItemStack itemStack) {
        return getPotionEffects(itemStack.getItemDamage());
    }

    public abstract void getSubItems(Item itemID, CreativeTabs creativeTab, List<ItemStack> list);

    public abstract boolean isEffectInstant(int damageMeta);

    public abstract void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean par4);

    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            --itemStack.stackSize;
        }
        if (!world.isRemote) {
            List<PotionEffect> list = getPotionEffects(itemStack);

            if (list != null) {
                Iterator<PotionEffect> iterator = list.iterator();

                while (iterator.hasNext()) {
                    PotionEffect potioneffect = iterator.next();
                    player.addPotionEffect(new PotionEffect(potioneffect));
                }
            }
        }
        if (!player.capabilities.isCreativeMode) {
            if (itemStack.stackSize <= 0) {
                return new ItemStack(Items.glass_bottle);
            }

            player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }
        return itemStack;
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (PotionParser.isSplash(itemStack.getItemDamage())) {
            if (!player.capabilities.isCreativeMode) {
                --itemStack.stackSize;
            }
            world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));

            if (!world.isRemote) {
                world.spawnEntityInWorld(getEntityPotion(itemStack, world, player));
            }
            return itemStack;
        } else {
            player.setItemInUse(itemStack, itemStack.getItem().getMaxItemUseDuration(itemStack));
            return itemStack;
        }
    }

    /**
     * Gets an Instance of The Entity Potion used for Throwing.
     * 
     * @param itemStack
     * @param world
     * @param player
     * @return ? extends EntityPotion. Does not Return Null
     */
    protected EntityPotion getEntityPotion(ItemStack itemStack, World world, EntityPlayer player) {
        return new EntityPotion(world, player, itemStack);
    }
}
