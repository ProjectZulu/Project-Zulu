package projectzulu.common.potion.effects;

import net.minecraft.entity.EntityLivingBase;

public class PotionIncendiary extends PotionZulu {

    public PotionIncendiary(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
        setIconIndex(1, 2);
        setEffectiveness(1.0D);
    }

    @Override
    public void performEffect(EntityLivingBase par1EntityLiving, int par2) {
        super.performEffect(par1EntityLiving, par2);
    }

    @Override
    public void affectEntity(EntityLivingBase par1EntityLiving, EntityLivingBase par2EntityLiving, int par3, double par4) {
        par2EntityLiving.setFire((int) (2 + 1.5 * par3));
        super.affectEntity(par1EntityLiving, par2EntityLiving, par3, par4);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean isReady(int par1, int par2) {
        return true;
    }
}