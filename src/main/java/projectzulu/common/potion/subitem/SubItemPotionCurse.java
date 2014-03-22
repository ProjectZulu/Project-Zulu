package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import projectzulu.common.api.PotionList;

import com.google.common.base.Optional;

public class SubItemPotionCurse extends SubItemPotionGeneric {

    public SubItemPotionCurse(Item item, int subID) {
        super(item, subID, "potion.curse");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 5, 6, 10, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.curse;
    }
}
