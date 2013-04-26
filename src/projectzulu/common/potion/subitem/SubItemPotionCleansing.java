package projectzulu.common.potion.subitem;

import net.minecraft.potion.Potion;
import projectzulu.common.api.PotionList;

import com.google.common.base.Optional;

public class SubItemPotionCleansing extends SubItemPotionGeneric {

    public SubItemPotionCleansing(int itemID, int subID) {
        super(itemID, subID, "Cleansing");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 10, 20 * 10, 20 * 15, 20 * 20, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.cleansing;
    }
}