package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionBlindness extends SubItemPotionGeneric {

    public SubItemPotionBlindness(Item itemID, int subID) {
        super(itemID, subID, "potion.blindness");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 13, 20 * 5, 2, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.blindness);
    }
}