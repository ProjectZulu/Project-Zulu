package projectzulu.common.potion;

import net.minecraft.entity.EntityLiving;

public class PotionCleansing extends PotionZulu{
	
	protected PotionCleansing(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
		
		setIconIndex(1, 0);
		setEffectiveness(0.25D);
	}
	
	@Override
	public void performEffect(EntityLiving effectedEntity, int par2) {
		super.performEffect(effectedEntity, par2);
	}
	
	@Override
	public boolean isReady(int par1, int par2) {
		int var3 = 25 >> par2;
        return var3 > 0 ? par1 % var3 == 0 : true;
	}
}

