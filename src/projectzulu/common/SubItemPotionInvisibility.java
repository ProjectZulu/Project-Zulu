package projectzulu.common;

import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionInvisibility extends SubItemPotionGeneric {

    SubItemPotionInvisibility(int itemID, int subID) {
        super(itemID, subID, "Invisibility");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 15, 20 * 20, 20 * 30, 20 * 40, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.invisibility);
    }
}