package projectzulu.common.potion.subitem;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionPoison extends SubItemPotionHalfPower {

    public SubItemPotionPoison(Item itemID, int subID) {
        super(itemID, subID, "Poison");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 10, 15, 6, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.poison);
    }

    @Override
    protected TYPE getIngredientType(ItemStack ingredient, ItemStack brewingStack) {
        if (ingredient.getItem() == Items.fermented_spider_eye) {
            return TYPE.CHEMICAL;
        } else {
            return super.getIngredientType(ingredient, brewingStack);
        }
    }

    @Override
    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        if (SubItemPotionList.HARM.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.HARM.get();
            return new ItemStack(subItemPotion.item, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}