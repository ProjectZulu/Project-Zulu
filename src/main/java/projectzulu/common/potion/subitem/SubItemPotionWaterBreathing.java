package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionWaterBreathing extends SubItemPotionGeneric {

    public SubItemPotionWaterBreathing(Item itemID, int subID) {
        super(itemID, subID, "potion.waterBreathing");
        setSubItemBounds(4, 4, 1, 0);
        setEffectScale(20 * 20, 20 * 5, 14, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.waterBreathing);
    }
}