package projectzulu.common;

import java.util.Iterator;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public abstract class SubItemPotion {

    public final int itemID;
    public final int subID;
    public final String baseName;

    SubItemPotion(int itemID, int subID, String baseName) {
        this.subID = subID;
        this.itemID = itemID;
        this.baseName = baseName;
    }

    public String getDisplayName(ItemStack itemStack) {
        return baseName;
    }

    public abstract boolean hasPotionEffects(ItemStack itemStack);

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

    public abstract void getSubItems(int itemID, CreativeTabs creativeTab, List<ItemStack> list);

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
                return new ItemStack(Item.glassBottle);
            }

            player.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle));
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
