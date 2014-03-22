package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionResistance extends SubItemPotionHalfPower {

    public SubItemPotionResistance(Item itemID, int subID) {
        super(itemID, subID, "Resistance");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 5, 13, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.resistance);
    }
}
