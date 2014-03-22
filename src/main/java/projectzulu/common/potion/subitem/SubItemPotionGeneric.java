package projectzulu.common.potion.subitem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics.Properties;
import projectzulu.common.potion.EntityPZPotion;
import projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public abstract class SubItemPotionGeneric extends SubItemPotion {

    protected int maxLevel = 2;
    protected int maxDuration = 2;
    protected int maxPower = 2;
    /* Forms This Potion can Take. 0 = Regular and Splash, 1 == Regular Only, 2 == Only Splash */
    protected int type = 2;

    protected int initialTicks = 0;
    protected int ticksPerLevel = 10;
    protected int durationScale = 12;
    protected int durationSpread = 10;
    protected int powerPerLevel = 1;

    protected String[] strengthPrefixes = new String[] { "", "Thickened", "Strengthened", "Fortified" };
    protected String[] durationPrefixes = new String[] { "", "Extended", "Prolonged", "Continuous" };

    protected String[] durationPostfixes = new String[] { "", "of Extended", "of Prolonged", "of Continuous" };
    protected String[] strengthPostfixes = new String[] { "", "Thickness", "Strength", "Fortification" };

    enum TYPE {
        CHEMICAL, POWER, DURATION, TIER, SPLASH, NONE;
    }

    SubItemPotionGeneric(Item item, int subID, String baseName) {
        super(item, subID, baseName);
    }

    @Override
    public void register() {

    }

    @Override
    public final ItemStack getPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        switch (getIngredientType(ingredient, brewingStack)) {
        case POWER: {
            int power = PotionParser.readPower(brewingStack.getItemDamage());
            int duration = PotionParser.readDuration(brewingStack.getItemDamage());
            int metaDamage = brewingStack.getItemDamage();
            if (power < maxPower - 1) {
                if (duration > 0) {
                    metaDamage = PotionParser.setDuration(duration - 1, metaDamage);
                }
                return new ItemStack(brewingStack.getItem(), brewingStack.stackSize, PotionParser.setPower(power + 1,
                        metaDamage));
            }
            break;
        }
        case DURATION: {
            int power = PotionParser.readPower(brewingStack.getItemDamage());
            int duration = PotionParser.readDuration(brewingStack.getItemDamage());
            int metaDamage = brewingStack.getItemDamage();
            if (duration < maxDuration - 1) {
                if (power > 0) {
                    metaDamage = PotionParser.setPower(power - 1, metaDamage);
                }
                return new ItemStack(brewingStack.getItem(), brewingStack.stackSize, PotionParser.setDuration(
                        duration + 1, metaDamage));
            }
            break;
        }
        case TIER: {
            int level = PotionParser.readLevel(brewingStack.getItemDamage());
            int power = PotionParser.readPower(brewingStack.getItemDamage());
            int duration = PotionParser.readDuration(brewingStack.getItemDamage());
            int metaDamage = brewingStack.getItemDamage();
            if (level < maxLevel - 1) {
                if (power > 0) {
                    metaDamage = PotionParser.setPower(power - 1, metaDamage);
                }
                if (duration > 0) {
                    metaDamage = PotionParser.setDuration(duration - 1, metaDamage);
                }
                return new ItemStack(brewingStack.getItem(), brewingStack.stackSize, PotionParser.setLevel(level + 1,
                        metaDamage));
            }
            break;
        }
        case CHEMICAL: {
            return getChemicalPotionResult(ingredient, brewingStack);
        }
        case SPLASH: {
            if (!PotionParser.isSplash(brewingStack.getItemDamage())) {
                return new ItemStack(brewingStack.getItem(), brewingStack.stackSize, PotionParser.setSplash(brewingStack
                        .getItemDamage()));
            }
            break;
        }
        case NONE:
            break;
        }
        return super.getPotionResult(ingredient, brewingStack);
    }

    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        return null;
    }

    protected TYPE getIngredientType(ItemStack ingredient, ItemStack brewingStack) {
        if (ingredient.getItem() == Items.redstone) {
            return TYPE.POWER;
        } else if (ingredient.getItem() == Items.glowstone_dust) {
            return TYPE.DURATION;
        } else if (ingredient.getItem() == Items.gunpowder) {
            return TYPE.SPLASH;
        } else if (ItemList.genericCraftingItems.isPresent()
                && ingredient.getItem() == ItemList.genericCraftingItems.get()
                && ingredient.getItemDamage() == Properties.ShinyBauble.meta) {
            return TYPE.TIER;
        }
        return TYPE.NONE;
    }

    protected void setSubItemBounds(int maxLevel, int maxDuration, int maxPower, int type) {
        this.maxLevel = maxLevel;
        this.maxDuration = maxDuration;
        this.maxPower = maxPower;
        this.type = type;
    }

    protected void setEffectScale(int initialTicks, int ticksPerLevel, int durationScale, int durationSpread,
            int powerPerLevel) {
        this.initialTicks = initialTicks;
        this.ticksPerLevel = ticksPerLevel;
        this.durationScale = durationScale;
        this.durationSpread = durationSpread;
        this.powerPerLevel = powerPerLevel;
    }

    abstract Optional<? extends Potion> getPotion();

    @Override
    public String getDisplayName(ItemStack itemStack) {
        int level = PotionParser.readLevel(itemStack.getItemDamage());
        int duration = PotionParser.readDuration(itemStack.getItemDamage());
        int power = PotionParser.readPower(itemStack.getItemDamage());
        String nameBuilder = "";

        /* Check For Prefix */
        if (power <= 0 && duration > 0) {
            nameBuilder = nameBuilder + durationPrefixes[duration] + " ";
        } else if (power > 0 && duration <= 0) {
            nameBuilder = nameBuilder + strengthPrefixes[power] + " ";
        }

        /* Add Potion Name */
        nameBuilder = nameBuilder + StatCollector.translateToLocal(baseName).trim() + " Potion";

        /* Check for Postfix */
        if (power > 0 && duration > 0) {
            nameBuilder = nameBuilder + " " + durationPostfixes[duration] + " " + strengthPostfixes[power];
        }
        if (level > 0) {
            nameBuilder = nameBuilder + " ";
            for (int i = 1; i <= level; i++) {
                nameBuilder = nameBuilder + "I";
            }
        }
        return nameBuilder;
    }

    @Override
    public boolean hasPotionEffects(ItemStack itemStack) {
        return true;
    }

    @Override
    public List<PotionEffect> getPotionEffects(int damageMeta) {
        List<PotionEffect> effectList = new ArrayList<PotionEffect>();
        if (getPotion().isPresent()) {
            effectList.add(new PotionEffect(getPotion().get().id, getPotion().get().isInstant() ? 1
                    : calculateDuration(damageMeta), calculatePower(damageMeta)));
        }
        return effectList;
    }

    protected int calculateDuration(int damageMeta) {
        int baseLevel = PotionParser.readLevel(damageMeta);
        int baseDuration = PotionParser.readDuration(damageMeta);
        int tempBase = (initialTicks + baseLevel * ticksPerLevel);
        double tempBonus = (Math.pow(baseDuration + 11 - durationSpread, 2))
                / (Math.pow(maxDuration - 1, 2) + maxDuration - 1) * durationScale;
        return (int) (tempBase * tempBonus);
    }
    
    protected int calculatePower(int damageMeta) {
        return (PotionParser.readPower(damageMeta) + powerPerLevel * PotionParser.readLevel(damageMeta));
    }
    
    @Override
    public void getSubItems(Item itemID, CreativeTabs creativeTab, List<ItemStack> list) {
        if (!getPotion().isPresent()) {
            return;
        }

        /* Add Regular Potion Variations */
        if (type == 0 || type == 1) {
            for (int level = 0; level < maxLevel; level++) {
                for (int power = 0; power < maxPower; power++) {
                    for (int duration = 0; duration < maxDuration; duration++) {
                        int damage = PotionParser.setPower(power,
                                PotionParser.setDuration(duration, PotionParser.setLevel(level, subID)));
                        list.add(new ItemStack(itemID, 1, damage));
                    }
                }
            }
        }

        /* Add Splash Potion Variations */
        if (type == 0 || type == 2) {
            for (int level = 0; level < maxLevel; level++) {
                for (int power = 0; power < maxPower; power++) {
                    for (int duration = 0; duration < maxDuration; duration++) {
                        int damage = PotionParser.setSplash(PotionParser.setPower(power,
                                PotionParser.setDuration(duration, PotionParser.setLevel(level, subID))));
                        list.add(new ItemStack(itemID, 1, damage));
                    }
                }
            }
        }
    }

    @Override
    public boolean isEffectInstant(int damageMeta) {
        if (getPotion().isPresent()) {
            return getPotion().get().isInstant();
        } else {
            return false;
        }
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean par4) {
        List<PotionEffect> effectList = getPotionEffects(itemStack);
        if (effectList != null && !effectList.isEmpty()) {

            Iterator<PotionEffect> iterator = effectList.iterator();
            while (iterator.hasNext()) {
                /* Line 1: PotionName - Power X (Duration) */
                PotionEffect potioneffect = iterator.next();
                String line1 = StatCollector.translateToLocal(potioneffect.getEffectName()).trim();

                line1 = line1.concat(" - Power ").concat(Integer.toString(potioneffect.getAmplifier() + 1));

                if (potioneffect.getDuration() > 20 && !isEffectInstant(itemStack.getItemDamage())) {
                    line1 = line1 + " (" + Potion.getDurationString(potioneffect) + ")";
                }

                if (Potion.potionTypes[potioneffect.getPotionID()].isBadEffect()) {
                    list.add(EnumChatFormatting.RED + line1);
                } else {
                    list.add(EnumChatFormatting.GRAY + line1);
                }
            }
        } else {
            String s1 = StatCollector.translateToLocal("potion.empty").trim();
            list.add(EnumChatFormatting.GRAY + s1);
        }
    }

    @Override
    protected EntityPotion getEntityPotion(ItemStack itemStack, World world, EntityPlayer player) {
        return new EntityPZPotion(world, player, itemStack);
    }
}