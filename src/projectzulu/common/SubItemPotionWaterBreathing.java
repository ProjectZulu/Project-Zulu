package projectzulu.common;

import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionWaterBreathing extends SubItemPotionGeneric {

    SubItemPotionWaterBreathing(int itemID, int subID) {
        super(itemID, subID, "Water Breathing");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 20, 20 * 25, 20 * 35, 20 * 45, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.waterBreathing);
    }
}