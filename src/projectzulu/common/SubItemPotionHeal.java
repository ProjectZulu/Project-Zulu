package projectzulu.common;

import net.minecraft.potion.Potion;
import projectzulu.common.api.PotionList;

import com.google.common.base.Optional;

public class SubItemPotionHeal extends SubItemPotionGeneric {

    SubItemPotionHeal(int itemID, int subID) {
        super(itemID, subID, "Healing");
        setSubItemBounds(4, 1, 4, 0);
        setEffectScale(20 * 15, 20 * 20, 20 * 30, 20 * 40, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.heal2;
    }
}