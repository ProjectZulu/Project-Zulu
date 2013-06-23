package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionNightVision extends SubItemPotionGeneric {

    public SubItemPotionNightVision(int itemID, int subID) {
        super(itemID, subID, "Night Vision");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 15, 20 * 20, 20 * 30, 20 * 40, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.nightVision);
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