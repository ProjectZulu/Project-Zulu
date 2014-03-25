package projectzulu.common.potion.subitem;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Optional;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import projectzulu.common.api.ItemList;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.core.ItemGenerics.Properties;
import projectzulu.common.potion.PotionParser;

public class SubItemPotionBubbling extends SubItemPotion {

    PotionRecipies recipies = new PotionRecipies();

    public SubItemPotionBubbling(Item item, int subID) {
        super(item, subID, "potion.shining");
    }

    @Override
    public void register() {
        recipies.addResultPotion(Items.golden_carrot, SubItemPotionList.NIGHT_VISION);
        recipies.addResultPotion(Items.magma_cream, SubItemPotionList.FIRE_RESISTANCE);
        recipies.addResultPotion(Items.sugar, SubItemPotionList.MOVE_SPEED);
        recipies.addResultPotion(Items.speckled_melon, SubItemPotionList.HEAL);
        recipies.addResultPotion(Items.spider_eye, SubItemPotionList.POISON);
        recipies.addResultPotion(Items.ghast_tear, SubItemPotionList.REGENERATION);
        recipies.addResultPotion(Items.fermented_spider_eye, SubItemPotionList.WEAKNESS);
        if (ItemList.genericCraftingItems.isPresent()) {
            recipies.addResultPotion(ItemList.genericCraftingItems.get(), Properties.Talon.meta,
                    SubItemPotionList.STRENGTH);
            recipies.addResultPotion(ItemList.genericCraftingItems.get(), Properties.LargeHeart.meta,
                    SubItemPotionList.STRENGTH);
            recipies.addResultPotion(ItemList.genericCraftingItems.get(), Properties.RabbitsFoot.meta,
                    SubItemPotionList.JUMP);
            recipies.addResultPotion(ItemList.genericCraftingItems.get(), Properties.FrogLegs.meta,
                    SubItemPotionList.JUMP);
            recipies.addResultPotion(ItemList.genericCraftingItems.get(), Properties.SmallHeart.meta,
                    SubItemPotionList.DIG_SPEED);
            recipies.addResultPotion(ItemList.genericCraftingItems.get(), Properties.PowderSlush.meta,
                    SubItemPotionList.CLEANSING);
            recipies.addResultPotion(ItemList.genericCraftingItems.get(), Properties.PricklyPowder.meta,
                    SubItemPotionList.THORNS);
            recipies.addResultPotion(ItemList.genericCraftingItems.get(), Properties.Gill.meta,
                    SubItemPotionList.WATER_BREATHING);
        }
    }

    @Override
    public ItemStack getPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        SubItemPotion resultingPotion = recipies.getResulingPotion(ingredient.getItem(), ingredient.getItemDamage());
        if (resultingPotion != null) {
            return new ItemStack(resultingPotion.item, 1, PotionParser.setID(resultingPotion.subID, 0));
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
    public void getSubItems(Item itemID, CreativeTabs creativeTab, List<ItemStack> list) {
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
