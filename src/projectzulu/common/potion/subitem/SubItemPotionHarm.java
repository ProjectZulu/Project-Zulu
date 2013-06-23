package projectzulu.common.potion.subitem;

import net.minecraft.potion.Potion;
import projectzulu.common.api.PotionList;

import com.google.common.base.Optional;

public class SubItemPotionHarm extends SubItemPotionGeneric {

    public SubItemPotionHarm(int itemID, int subID) {
        super(itemID, subID, "Harming");
        setSubItemBounds(4, 1, 4, 0);
        setEffectScale(20 * 15, 20 * 20, 20 * 30, 20 * 40, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.harm2;
    }
}