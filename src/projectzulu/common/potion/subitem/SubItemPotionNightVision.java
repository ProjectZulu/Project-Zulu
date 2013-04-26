package projectzulu.common.potion.subitem;

import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionNightVision extends SubItemPotionGeneric {

    public SubItemPotionNightVision(int itemID, int subID) {
        super(itemID, subID, "Night Vision");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 15, 20 * 20, 20 * 30, 20 * 40, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.nightVision);
    }
}