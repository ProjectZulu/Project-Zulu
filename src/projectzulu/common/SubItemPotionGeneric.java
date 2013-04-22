package projectzulu.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.google.common.base.Optional;

public abstract class SubItemPotionGeneric extends SubItemPotion {

    private int maxLevel = 2;
    private int maxDuration = 2;
    private int maxPower = 2;
    /* Forms This Potion can Take. 0 = Regular and Splash, 1 == Regular Only, 2 == Only Splash */
    private int type = 2;

    private int initialTicks = 0;
    private int ticksPerDuration = 20;
    private int ticksPerLevel = 10;
    private int dTicksPerLevel_dLevel = 10;
    private int powerPerLevel = 1;

    protected String[] strengthPrefixes = new String[] { "", "Thickened", "Strengthened", "Fortified" };
    protected String[] durationPrefixes = new String[] { "", "Extended", "Prolonged", "Continuous" };

    protected String[] durationPostfixes = new String[] { "", "of Extended", "of Prolonged", "of Continuous" };
    protected String[] strengthPostfixes = new String[] { "", "Thickness", "Strength", "Fortification" };

    SubItemPotionGeneric(int itemID, int subID, String baseName) {
        super(itemID, subID, baseName);
    }

    protected void setSubItemBounds(int maxLevel, int maxDuration, int maxPower, int type) {
        this.maxLevel = maxLevel;
        this.maxDuration = maxDuration;
        this.maxPower = maxPower;
        this.type = type;
    }

    protected void setEffectScale(int initialTicks, int ticksPerDuration, int ticksPerLevel, int dTicksPerLevel_dLevel,
            int powerPerLevel) {
        this.initialTicks = initialTicks;
        this.ticksPerDuration = ticksPerDuration;
        this.ticksPerLevel = ticksPerLevel;
        this.powerPerLevel = powerPerLevel;
        this.dTicksPerLevel_dLevel = dTicksPerLevel_dLevel;
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
        nameBuilder = nameBuilder + baseName + " Potion";

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
            int baseLevel = PotionParser.readLevel(damageMeta);
            int baseDuration = PotionParser.readDuration(damageMeta);

            int duration = initialTicks + ticksPerDuration * baseDuration + ticksPerLevel * baseLevel
                    + dTicksPerLevel_dLevel * baseLevel * baseLevel;
            int power = (PotionParser.readPower(damageMeta) + powerPerLevel * PotionParser.readLevel(damageMeta));
            effectList.add(new PotionEffect(getPotion().get().id, duration, power));
        }
        return effectList;
    }

    @Override
    public void getSubItems(int itemID, CreativeTabs creativeTab, List<ItemStack> list) {
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
                    line1 = line1 + " (" + parseDuration(potioneffect.getDuration()) + ")";
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

    private String parseDuration(int duration) {
        duration = duration / 20;
        return String.format("%02d:%02d", (duration % 3600) / 60, (duration % 60)).trim();
    }

    @Override
    protected EntityPotion getEntityPotion(ItemStack itemStack, World world, EntityPlayer player) {
        return new EntityPZPotion(world, player, itemStack);
    }
}