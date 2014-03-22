package projectzulu.common.potion.subitem;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionJump extends SubItemPotionGeneric {

    public SubItemPotionJump(Item itemID, int subID) {
        super(itemID, subID, "potion.jump");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 5, 14, 10, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.jump);
    }

    @Override
    protected TYPE getIngredientType(ItemStack ingredient, ItemStack brewingStack) {
        if (ingredient.getItem() == Items.feather) {
            return TYPE.CHEMICAL;
        } else {
            return super.getIngredientType(ingredient, brewingStack);
        }
    }

    @Override
    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        if (SubItemPotionList.SLOWFALL.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.SLOWFALL.get();
            return new ItemStack(subItemPotion.item, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}