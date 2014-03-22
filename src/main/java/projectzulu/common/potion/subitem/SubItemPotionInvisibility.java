package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionInvisibility extends SubItemPotionGeneric {

    public SubItemPotionInvisibility(Item itemID, int subID) {
        super(itemID, subID, "potion.invisibility");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 10, 20 * 5, 16, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.invisibility);
    }
}