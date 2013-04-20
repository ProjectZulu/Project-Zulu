package projectzulu.common;

import java.util.Collections;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class SubItemPotionBubbling extends SubItemPotion {

    SubItemPotionBubbling(int itemID, int subID) {
        super(itemID, subID, "Bubbling");
    }

    @Override
    public boolean hasPotionEffects(ItemStack itemStack) {
        return false;
    }

    @Override
    public List<PotionEffect> getPotionEffects(int damageMeta) {
        return Collections.emptyList();
    }

    @Override
    public void getSubItems(int itemID, CreativeTabs creativeTab, List<ItemStack> list) {
        list.add(new ItemStack(itemID, 1, subID));
    }

    @Override
    public boolean isEffectInstant(int damageMeta) {
        return false;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<PotionEffect> list, boolean par4) {

    }
}
