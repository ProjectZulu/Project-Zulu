package projectzulu.common.potion.subitem;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionResistance extends SubItemPotionGeneric {

    public SubItemPotionResistance(int itemID, int subID) {
        super(itemID, subID, "Resistance");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 5, 13, 10, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.resistance);
    }

    @Override
    public List<PotionEffect> getPotionEffects(int damageMeta) {
        List<PotionEffect> effectList = new ArrayList<PotionEffect>();
        if (getPotion().isPresent()) {
            int baseLevel = PotionParser.readLevel(damageMeta);
            int baseDuration = PotionParser.readDuration(damageMeta);
            int basePower = PotionParser.readPower(damageMeta);

            int tempBase = (initialTicks + baseLevel * ticksPerLevel);
            int tempBonus = (baseDuration * baseDuration + 11 - durationSpread + baseDuration)
                    / (maxDuration * maxDuration + maxDuration);
            int duration = tempBase * tempBonus;
            int power = (MathHelper.ceiling_float_int(basePower / 2f) + MathHelper.ceiling_float_int(baseLevel / 2f));
            effectList.add(new PotionEffect(getPotion().get().id, duration, power));
        }
        return effectList;
    }
}
