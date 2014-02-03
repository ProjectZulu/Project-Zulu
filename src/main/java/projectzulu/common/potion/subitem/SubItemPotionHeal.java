package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.api.PotionList;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionHeal extends SubItemPotionGeneric {

    public SubItemPotionHeal(int itemID, int subID) {
        super(itemID, subID, "potion.heal2");
        setSubItemBounds(4, 1, 4, 0);
        setEffectScale(20 * 10, 20 * 5, 16, 10, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.heal2;
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
        if (SubItemPotionList.HARM.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.HARM.get();
            return new ItemStack(subItemPotion.itemID, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}