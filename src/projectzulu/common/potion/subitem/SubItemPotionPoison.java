package projectzulu.common.potion.subitem;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionPoison extends SubItemPotionGeneric {

    public SubItemPotionPoison(int itemID, int subID) {
        super(itemID, subID, "Poison");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 10, 20 * 10, 20 * 15, 20 * 20, 1);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return Optional.of(Potion.poison);
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

    @Override
    protected TYPE getIngredientType(ItemStack ingredient, ItemStack brewingStack) {
        if (ingredient.itemID == Item.fermentedSpiderEye.itemID) {
            return TYPE.CHEMICAL;
        } else {
            return super.getIngredientType(ingredient, brewingStack);
        }
    }

    @Override
    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        if (SubItemPotionList.HARM.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.HARM.get();
            return new ItemStack(subItemPotion.itemID, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}