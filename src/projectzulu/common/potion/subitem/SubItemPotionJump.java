package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionJump extends SubItemPotionGeneric {

    public SubItemPotionJump(int itemID, int subID) {
        super(itemID, subID, "Jump");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 25, 20 * 35, 20 * 45, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.jump);
    }

    @Override
    protected TYPE getIngredientType(ItemStack ingredient, ItemStack brewingStack) {
        if (ingredient.itemID == Item.feather.itemID) {
            return TYPE.CHEMICAL;
        } else {
            return super.getIngredientType(ingredient, brewingStack);
        }
    }

    @Override
    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        if (SubItemPotionList.JUMP.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.JUMP.get();
            return new ItemStack(subItemPotion.itemID, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}