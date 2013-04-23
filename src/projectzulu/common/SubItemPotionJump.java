package projectzulu.common;

import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionJump extends SubItemPotionGeneric {

    SubItemPotionJump(int itemID, int subID) {
        super(itemID, subID, "Jump");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 25, 20 * 35, 20 * 45, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.jump);
    }
}