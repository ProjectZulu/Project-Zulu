package projectzulu.common.potion.effects;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class PotionHeal2 extends PotionZulu {

    public PotionHeal2(int par1, boolean par2) {
        super(par1, par2, 16262179);
        setIconIndex(3, 0);
        setEffectiveness(0.25D);
    }

    @Override
    public void performEffect(EntityLivingBase par1EntityLiving, int amplifier) {
        par1EntityLiving.heal(amplifier * 3);
    }

    @Override
    public void affectEntity(EntityLivingBase par1EntityLiving, EntityLivingBase par2EntityLiving, int amplifier,
            double par4) {
        int damage = par4 < 0.5 ? (int) (0.5f * (amplifier * 3) + 1.0D) : (int) ((amplifier * 3) + 1.0D);

        if (par2EntityLiving.isEntityUndead()) {
            if (par1EntityLiving == null) {
                par2EntityLiving.attackEntityFrom(DamageSource.magic, damage);
            } else {
                par2EntityLiving.attackEntityFrom(
                        DamageSource.causeIndirectMagicDamage(par2EntityLiving, par1EntityLiving), damage);
            }
        } else {
            par2EntityLiving.heal(damage);
        }
    }

    /**
     * Returns true if the potion has an instant effect instead of a continuous one (eg Harming)
     */
    @Override
    public boolean isInstant() {
        return true;
    }

    /**
     * checks if Potion effect is ready to be applied this tick.
     */
    @Override
    public boolean isReady(int par1, int par2) {
        return true;
    }
}
