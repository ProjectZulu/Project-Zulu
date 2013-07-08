package projectzulu.common.potion.subitem;

import java.util.Collections;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import projectzulu.common.api.ItemList;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.core.ItemGenerics.Properties;
import projectzulu.common.potion.PotionParser;

public class SubItemPotionBubbling extends SubItemPotion {

    PotionRecipies recipies = new PotionRecipies();

    public SubItemPotionBubbling(int itemID, int subID) {
        super(itemID, subID, "Bubbling");
    }

    @Override
    public void register() {
        recipies.addResultPotion(Item.goldenCarrot.itemID, SubItemPotionList.NIGHT_VISION);
        recipies.addResultPotion(Item.magmaCream.itemID, SubItemPotionList.FIRE_RESISTANCE);
        recipies.addResultPotion(Item.sugar.itemID, SubItemPotionList.MOVE_SPEED);
        recipies.addResultPotion(Item.speckledMelon.itemID, SubItemPotionList.HEAL);
        recipies.addResultPotion(Item.spiderEye.itemID, SubItemPotionList.POISON);
        recipies.addResultPotion(Item.ghastTear.itemID, SubItemPotionList.REGENERATION);
        recipies.addResultPotion(Item.fermentedSpiderEye.itemID, SubItemPotionList.WEAKNESS);
        recipies.addResultPotion(ItemList.genericCraftingItems.get().itemID, Properties.Talon.meta,
                SubItemPotionList.STRENGTH);
        recipies.addResultPotion(ItemList.genericCraftingItems.get().itemID, Properties.LargeHeart.meta,
                SubItemPotionList.STRENGTH);
        recipies.addResultPotion(ItemList.genericCraftingItems.get().itemID, Properties.RabbitsFoot.meta,
                SubItemPotionList.JUMP);
        recipies.addResultPotion(ItemList.genericCraftingItems.get().itemID, Properties.FrogLegs.meta,
                SubItemPotionList.JUMP);
        recipies.addResultPotion(ItemList.genericCraftingItems.get().itemID, Properties.SmallHeart.meta,
                SubItemPotionList.DIG_SPEED);
        recipies.addResultPotion(ItemList.genericCraftingItems.get().itemID, Properties.PowderSlush.meta,
                SubItemPotionList.CLEANSING);
        recipies.addResultPotion(ItemList.genericCraftingItems.get().itemID, Properties.PricklyPowder.meta,
                SubItemPotionList.THORNS);
        recipies.addResultPotion(ItemList.genericCraftingItems.get().itemID, Properties.Gill.meta,
                SubItemPotionList.WATER_BREATHING);
    }

    @Override
    public ItemStack getPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        SubItemPotion resultingPotion = recipies.getResulingPotion(ingredient.itemID, ingredient.getItemDamage());
        if (resultingPotion != null) {
            return new ItemStack(resultingPotion.itemID, 1, PotionParser.setID(resultingPotion.subID, 0));
        }
        return null;
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
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean par4) {

    }
}
