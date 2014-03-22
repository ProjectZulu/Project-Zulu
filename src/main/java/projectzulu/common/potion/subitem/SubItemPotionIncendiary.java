package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import projectzulu.common.api.PotionList;

import com.google.common.base.Optional;

public class SubItemPotionIncendiary extends SubItemPotionGeneric {

    public SubItemPotionIncendiary(Item itemID, int subID) {
        super(itemID, subID, "potion.incendiary");
        setSubItemBounds(1, 1, 4, 2);
//        setEffectScale(20 * 5, 20 * 5, 20 * 10, 20 * 1, 2);
        setEffectScale(0, 0, 0, 0, 2);

        /* Invert Regular Potion Names as Incendiary "Duration" is useless "Power" controls how long a Fire is Set */
        strengthPrefixes = new String[] { "", "Extended", "Prolonged", "Continuous" };
        durationPrefixes = new String[] { "", "Thickened", "Strengthened", "Fortified" };

        durationPostfixes = new String[] { "", "of Thickness", "of Strength", "of Fortification" };
        strengthPostfixes = new String[] { "", "Extended", "Prolonged", "Continuous" };
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.incendiary;
    }
}