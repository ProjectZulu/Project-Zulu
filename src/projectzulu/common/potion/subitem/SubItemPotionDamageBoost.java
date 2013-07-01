package projectzulu.common.potion.subitem;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import projectzulu.common.api.ItemList;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.core.ItemGenerics.Properties;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionDamageBoost extends SubItemPotionGeneric {

    public SubItemPotionDamageBoost(int itemID, int subID) {
        super(itemID, subID, "Strength");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 5, 6, 10, 1);
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

            int tempBase = (initialTicks + baseLevel * ticksPerLevel);
            int tempBonus = (baseDuration * baseDuration + 11 - durationSpread + baseDuration)
                    / (maxDuration * maxDuration + maxDuration);
            int duration = tempBase * tempBonus;
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
        if (ItemList.genericCraftingItems.isPresent()
                && ingredient.itemID == ItemList.genericCraftingItems.get().itemID
                && ingredient.getItemDamage() == Properties.LargeUnhealthyHeart.meta) {
            return TYPE.CHEMICAL;
        } else if (ingredient.itemID == Item.fermentedSpiderEye.itemID) {
            return TYPE.CHEMICAL;
        } else {
            return super.getIngredientType(ingredient, brewingStack);
        }
    }

    @Override
    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        if (SubItemPotionList.WEAKNESS.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.WEAKNESS.get();
            return new ItemStack(subItemPotion.itemID, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}
