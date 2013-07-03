package projectzulu.common.potion.effects;

import net.minecraft.entity.EntityLivingBase;

public class PotionCleansing extends PotionZulu {

    public PotionCleansing(int par1, boolean par2, int par3) {
        super(par1, par2, par3);

        setIconIndex(1, 0);
        setEffectiveness(1.0D);
    }

    @Override
    public void performEffect(EntityLivingBase effectedEntity, int par2) {
        super.performEffect(effectedEntity, par2);
    }

    @Override
    public boolean isReady(int par1, int par2) {
        int var3 = 25 >> par2;
        return var3 > 0 ? par1 % var3 == 0 : true;
    }
}
