package projectzulu.common.potion;

import net.minecraft.entity.EntityLiving;

public class PotionSlowFall extends PotionZulu{
	
	public PotionSlowFall(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
		
		setIconIndex(0, 0);
		setEffectiveness(0.25D);
	}
	
	@Override
	public void performEffect(EntityLiving par1EntityLiving, int par2) {
		if(par2 > 4){
			par2 = 4;
		}
		if( par1EntityLiving.fallDistance > (4-par2+1)*2 ){
			par1EntityLiving.fallDistance = (4-par2+1)*2;
		}
		par1EntityLiving.motionY = Math.max(par1EntityLiving.motionY, -1+(par2+1)*0.15);
		
		super.performEffect(par1EntityLiving, par2);
	}
	
	@Override
	public boolean isReady(int par1, int par2) {
		int var3 = 4 >> par2;
        return var3 > 0 ? par1 % var3 == 0 : true;
	}
}
