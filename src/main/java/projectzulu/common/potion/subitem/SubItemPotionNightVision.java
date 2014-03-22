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

public class SubItemPotionNightVision extends SubItemPotionGeneric {

    public SubItemPotionNightVision(Item itemID, int subID) {
        super(itemID, subID, "potion.nightVision");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 13, 20 * 5, 14, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.nightVision);
    }

    @Override
    protected TYPE getIngredientType(ItemStack ingredient, ItemStack brewingStack) {
        if (ingredient.getItem() == Items.fermented_spider_eye) {
            return TYPE.CHEMICAL;
        } else if (ingredient.getItem() == ItemList.genericCraftingItems.get()
                && ingredient.getItemDamage() == Properties.Antennae.meta) {
            return TYPE.CHEMICAL;
        } else {
            return super.getIngredientType(ingredient, brewingStack);
        }
    }

    @Override
    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        if (ingredient.getItem() == Items.fermented_spider_eye) {
            if (SubItemPotionList.INVISIBILITY.isPresent()) {
                SubItemPotion subItemPotion = SubItemPotionList.INVISIBILITY.get();
                return new ItemStack(subItemPotion.item, 1, PotionParser.setID(subItemPotion.subID,
                        brewingStack.getItemDamage()));
            }
        } else if (ingredient.getItem() == ItemList.genericCraftingItems.get()
                && ingredient.getItemDamage() == Properties.Antennae.meta) {
            if (SubItemPotionList.BLINDNESS.isPresent()) {
                SubItemPotion subItemPotion = SubItemPotionList.BLINDNESS.get();
                return new ItemStack(subItemPotion.item, 1, PotionParser.setID(subItemPotion.subID,
                        brewingStack.getItemDamage()));
            }
        }
        return null;
    }
}