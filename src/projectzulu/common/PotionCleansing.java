package projectzulu.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionCleansing extends PotionZulu{
	
	protected PotionCleansing(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
		
		setIconIndex(1, 0);
		setEffectiveness(0.25D);
	}
	
	@Override
	public void performEffect(EntityLiving effectedEntity, int par2) {
		
		/* Get Active Potion effect Collection from Entity*/
		Collection potionEffects = effectedEntity.getActivePotionEffects();
		/* Get Number of potion Effects, then get a random number between 0 - num*/
		int numOfElemenents = potionEffects.size();
		
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
			System.out.println("Server " + numOfElemenents);
		}else{
			System.out.println("Client " + numOfElemenents);
		}
		
		/* Check if we Should Try to Erase a buff, proportional to Potion Strength*/
		if( (par2+1)*25+25 - effectedEntity.getRNG().nextInt(100) >= 0){
			/* Element to Erase */
			int elementToErase = numOfElemenents > 0 ? effectedEntity.worldObj.rand.nextInt(numOfElemenents) : 0;
			Iterator iterator = potionEffects.iterator();

			/* Get Specific Element and assuming its not the Current Potion ID, mark it for Removal */
			int i = 0;
			PotionEffect potionEffectToRemove = null;
			while (iterator.hasNext() && i <= elementToErase) {
				potionEffectToRemove = (PotionEffect) iterator.next();
				if(i == elementToErase && !effectedEntity.worldObj.isRemote && potionEffectToRemove != null && potionEffectToRemove.getPotionID() != id){
					PotionCleansingTicker.addPotionEffectToRemove(effectedEntity.entityId, potionEffectToRemove.getPotionID());
				}
				i++;
			}
		}
		super.performEffect(effectedEntity, par2);
	}
	
	@Override
	public boolean isReady(int par1, int par2) {
		int var3 = 25 >> par2;
        return var3 > 0 ? par1 % var3 == 0 : true;
	}
}

