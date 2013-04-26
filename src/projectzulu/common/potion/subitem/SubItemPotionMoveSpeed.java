package projectzulu.common.potion.subitem;

import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionMoveSpeed extends SubItemPotionGeneric {

    public SubItemPotionMoveSpeed(int itemID, int subID) {
        super(itemID, subID, "Swiftness");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 10, 20 * 10, 20 * 15, 20 * 20, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.moveSpeed);
    }
}
