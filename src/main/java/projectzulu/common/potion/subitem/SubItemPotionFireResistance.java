package projectzulu.common.potion.subitem;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionFireResistance extends SubItemPotionGeneric {

    public SubItemPotionFireResistance(Item item, int subID) {
        super(item, subID, "potion.fireResistance");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 20, 20 * 5, 6, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.fireResistance);
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
        if (SubItemPotionList.INCENDIARY.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.INCENDIARY.get();
            return new ItemStack(subItemPotion.item, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}
