package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public class SubItemPotionWeakness extends SubItemPotionHalfPower {

    public SubItemPotionWeakness(Item itemID, int subID) {
        super(itemID, subID, "Weakness");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 5, 6, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.weakness);
    }
}