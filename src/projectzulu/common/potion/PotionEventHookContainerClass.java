package projectzulu.common.potion;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import projectzulu.common.API.PotionList;

public class PotionEventHookContainerClass {
	
	@ForgeSubscribe
	public void EntitySelfCleansingPotion(LivingUpdateEvent event){
		/* If Cleansing is Disabled, do Not Continue. */
		if(!PotionList.cleansing.isPresent()){
			return;
		}
		PotionEffect cleansingPotionEffect = event.entityLiving.getActivePotionEffect(PotionList.cleansing.get());
		if(cleansingPotionEffect != null && Potion.potionTypes[cleansingPotionEffect.getPotionID()].isReady(cleansingPotionEffect.getDuration(), cleansingPotionEffect.getAmplifier())){
			
			/* Get Active Potion effect Collection from Entity*/
			Collection potionEffects = event.entityLiving.getActivePotionEffects();
			/* Get Number of potion Effects, then get a random number between 0 - num*/
			int numOfElemenents = potionEffects.size();		
			
			/* Check if we Should Try to Erase a buff, proportional to Potion Strength*/
			if( (cleansingPotionEffect.getAmplifier()+1)*25+25 - event.entityLiving.getRNG().nextInt(100) >= 0){
				
				/* Element to Erase */
				int elementToErase = numOfElemenents > 0 ? event.entityLiving.worldObj.rand.nextInt(numOfElemenents) : 0;
				/** Iteratate Through Potioneffects, keeping Track of our index, 
				 * If Index is equal to Effect (and is NOT Cleanse): Remove it*/
				Iterator iterator = potionEffects.iterator();
				PotionEffect potionEffectToRemove = null;
				boolean removeEffect = false;
				for (int i = 0; iterator.hasNext(); i++) {
					potionEffectToRemove = (PotionEffect) iterator.next();
					if(i == elementToErase && !event.entityLiving.worldObj.isRemote){
						if(potionEffectToRemove != null && potionEffectToRemove.getPotionID() != cleansingPotionEffect.getPotionID()){
							removeEffect = true;
						}
						break;
					}
				}
				
				if(removeEffect){
					event.entityLiving.removePotionEffect(potionEffectToRemove.getPotionID());
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void cactusArmorDamage(LivingHurtEvent event){
		if( PotionList.thorn.isPresent() && event.entityLiving.isPotionActive(PotionList.thorn.get()) && event.source.getSourceOfDamage() instanceof EntityLiving ){
			EntityLiving hurtEntity = event.entityLiving;
			EntityLiving attackingEntity = (EntityLiving)event.source.getSourceOfDamage();
			
			PotionEffect thornsEffect = event.entityLiving.getActivePotionEffect(PotionList.thorn.get());
			System.out.println("Amplifier " + event.ammount);
			attackingEntity.attackEntityFrom(DamageSource.causeMobDamage(hurtEntity), event.ammount*3/4);
		}		
	}
}
