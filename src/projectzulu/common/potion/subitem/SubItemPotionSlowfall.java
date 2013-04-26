package projectzulu.common.potion.subitem;

import net.minecraft.potion.Potion;
import projectzulu.common.api.PotionList;

import com.google.common.base.Optional;

public class SubItemPotionSlowfall extends SubItemPotionGeneric {

    public SubItemPotionSlowfall(int itemID, int subID) {
        super(itemID, subID, "Slowfall");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 5, 20 * 5, 20 * 10, 20 * 25, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.slowfall;
    }
}