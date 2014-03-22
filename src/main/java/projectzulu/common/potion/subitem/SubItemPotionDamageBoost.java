package projectzulu.common.potion.subitem;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.api.ItemList;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.core.ItemGenerics.Properties;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionDamageBoost extends SubItemPotionHalfPower {

    public SubItemPotionDamageBoost(Item itemID, int subID) {
        super(itemID, subID, "Strength");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 5, 6, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.damageBoost);
    }

    @Override
    protected TYPE getIngredientType(ItemStack ingredient, ItemStack brewingStack) {
        if (ItemList.genericCraftingItems.isPresent() && ingredient.getItem() == ItemList.genericCraftingItems.get()
                && ingredient.getItemDamage() == Properties.LargeUnhealthyHeart.meta) {
            return TYPE.CHEMICAL;
        } else if (ingredient.getItem() == Items.fermented_spider_eye) {
            return TYPE.CHEMICAL;
        } else {
            return super.getIngredientType(ingredient, brewingStack);
        }
    }

    @Override
    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        if (SubItemPotionList.WEAKNESS.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.WEAKNESS.get();
            return new ItemStack(subItemPotion.item, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}
