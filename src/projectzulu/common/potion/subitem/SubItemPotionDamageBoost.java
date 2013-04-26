package projectzulu.common.potion.subitem;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionDamageBoost extends SubItemPotionGeneric {

    public SubItemPotionDamageBoost(int itemID, int subID) {
        super(itemID, subID, "Strength");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 10, 20 * 10, 20 * 15, 20 * 20, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.damageBoost);
    }

    @Override
    public List<PotionEffect> getPotionEffects(int damageMeta) {
        List<PotionEffect> effectList = new ArrayList<PotionEffect>();
        if (getPotion().isPresent()) {
            int baseLevel = PotionParser.readLevel(damageMeta);
            int baseDuration = PotionParser.readDuration(damageMeta);
            int basePower = PotionParser.readPower(damageMeta);

            int duration = initialTicks + ticksPerDuration * baseDuration + ticksPerLevel * baseLevel
                    + dTicksPerLevel_dLevel * baseLevel * baseLevel;
            int power = (MathHelper.ceiling_float_int(basePower / 2f) + MathHelper.ceiling_float_int(baseLevel / 2f));
            if (power > 3) {
                power = 3;
            }
            effectList.add(new PotionEffect(getPotion().get().id, duration, power));
        }
        return effectList;
    }
}
