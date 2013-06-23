package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionBlindness extends SubItemPotionGeneric {

    public SubItemPotionBlindness(int itemID, int subID) {
        super(itemID, subID, "Blindness");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 4, 20 * 4, 20 * 5, 20 * 5, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.blindness);
    }

    @Override
    protected TYPE getIngredientType(ItemStack ingredient, ItemStack brewingStack) {
        if (ingredient.itemID == Item.fermentedSpiderEye.itemID) {
            return TYPE.CHEMICAL;
        } else {
            return super.getIngredientType(ingredient, brewingStack);
        }
    }

    @Override
    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        if (SubItemPotionList.INVISIBILITY.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.INVISIBILITY.get();
            return new ItemStack(subItemPotion.itemID, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}