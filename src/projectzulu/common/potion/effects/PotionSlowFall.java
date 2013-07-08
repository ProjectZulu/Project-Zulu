package projectzulu.common.potion.effects;

import net.minecraft.entity.EntityLivingBase;

public class PotionSlowFall extends PotionZulu {

    public PotionSlowFall(int par1, boolean par2, int par3) {
        super(par1, par2, par3);

        setIconIndex(0, 0);
        setEffectiveness(1.0D);
    }

    @Override
    public void performEffect(EntityLivingBase par1EntityLiving, int amplifier) {
        amplifier = amplifier > 10 ? 10 : amplifier < 0 ? 0 : amplifier;
        if (par1EntityLiving.fallDistance > (10 - amplifier) * 2) {
            par1EntityLiving.fallDistance = (10 - amplifier) * 2;
        }
        par1EntityLiving.motionY = Math.max(par1EntityLiving.motionY, -1.2f + (amplifier + 1f) * (0.1f));
        super.performEffect(par1EntityLiving, amplifier);
    }

    @Override
    public boolean isReady(int par1, int par2) {
        int var3 = 4 >> par2;
        return var3 > 0 ? par1 % var3 == 0 : true;
    }
}