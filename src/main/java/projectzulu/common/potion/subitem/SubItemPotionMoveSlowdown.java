package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionMoveSlowdown extends SubItemPotionGeneric {

    public SubItemPotionMoveSlowdown(Item itemID, int subID) {
        super(itemID, subID, "potion.moveSlowdown");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 10, 20 * 5, 6, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.moveSlowdown);
    }
}