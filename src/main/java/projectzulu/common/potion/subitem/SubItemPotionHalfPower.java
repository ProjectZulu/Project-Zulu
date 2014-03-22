package projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import projectzulu.common.potion.PotionParser;

public abstract class SubItemPotionHalfPower extends SubItemPotionGeneric {

    SubItemPotionHalfPower(Item itemID, int subID, String baseName) {
        super(itemID, subID, baseName);
    }

    @Override
    protected int calculatePower(int damageMeta) {
        int baseLevel = PotionParser.readLevel(damageMeta);
        int basePower = PotionParser.readPower(damageMeta);
        int power = (MathHelper.ceiling_float_int(basePower / 2f) + MathHelper.ceiling_float_int(baseLevel / 2f));
        return power > 3 ? 3 : power;
    }
}
