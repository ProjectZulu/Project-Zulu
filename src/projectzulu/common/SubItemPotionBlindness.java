package projectzulu.common;

import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionBlindness extends SubItemPotionGeneric {

    SubItemPotionBlindness(int itemID, int subID) {
        super(itemID, subID, "Blindness");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 4, 20 * 4, 20 * 5, 20 * 5, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.blindness);
    }
}