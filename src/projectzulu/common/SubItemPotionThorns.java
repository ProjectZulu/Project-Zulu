package projectzulu.common;

import net.minecraft.potion.Potion;
import projectzulu.common.api.PotionList;

import com.google.common.base.Optional;

public class SubItemPotionThorns extends SubItemPotionGeneric {

    SubItemPotionThorns(int itemID, int subID) {
        super(itemID, subID, "Thorns");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 10, 20 * 10, 20 * 15, 20 * 20, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.thorn;
    }
}