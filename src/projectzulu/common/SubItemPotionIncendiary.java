package projectzulu.common;

import net.minecraft.potion.Potion;
import projectzulu.common.api.PotionList;

import com.google.common.base.Optional;

public class SubItemPotionIncendiary extends SubItemPotionGeneric {

    SubItemPotionIncendiary(int itemID, int subID) {
        super(itemID, subID, "Incindiary");
        setSubItemBounds(2, 2, 2, 2);
        setEffectScale(20 * 5, 20 * 5, 20 * 10);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.incendiary;
    }
}
