package projectzulu.common;

import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionMoveSlowdown extends SubItemPotionGeneric {

    SubItemPotionMoveSlowdown(int itemID, int subID) {
        super(itemID, subID, "Slowdown");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 10, 20 * 10, 20 * 15, 20 * 20, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.moveSlowdown);
    }
}