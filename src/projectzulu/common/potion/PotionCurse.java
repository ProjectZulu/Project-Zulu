package projectzulu.common.potion;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.PotionEffect;

public class PotionCurse extends PotionZulu{
	
	protected PotionCurse(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
		
		setIconIndex(2, 0);
		setEffectiveness(0.25D);
	}
	
	@Override
	public void performEffect(EntityLiving effectedEntity, int par2) {
		
		/* Get Active Potion effect Collection from Entity*/
		Collection potionEffects = effectedEntity.getActivePotionEffects();
		
		/* Get Number of potion Effects, then get a random number between 0 - num*/
		int numOfElemenents = potionEffects.size();
		/* Check if we Should Try to Get a PotionEffect, proportional to Potion Strength*/
		if( (par2+1)*25+25 - effectedEntity.getRNG().nextInt(100) >= 0){

			/* Element to Erase */
			int elementToDo = numOfElemenents > 0 ? effectedEntity.worldObj.rand.nextInt(numOfElemenents) : 0;
			Iterator iterator = potionEffects.iterator();

			/* Get Specific Element and assuming its not the Current Potion ID, mark it for Removal */
			int i = 0;
			PotionEffect potionEffectToPerform = null;
			while (iterator.hasNext() && i <= elementToDo) {
				potionEffectToPerform = (PotionEffect) iterator.next();
				if(i == elementToDo && !effectedEntity.worldObj.isRemote && potionEffectToPerform != null && potionEffectToPerform.getPotionID() != id) {
					potionEffectToPerform.performEffect(effectedEntity);
					break;
				}
				i++;
			}
		}
	}
	
	@Override
	public boolean isReady(int par1, int par2) {
		int var3 = 25 >> par2;
        return var3 > 0 ? par1 % var3 == 0 : true;
	}
}


