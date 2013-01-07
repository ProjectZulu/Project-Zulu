package projectzulu.common;

import java.util.Collection;

import org.lwjgl.input.Keyboard;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionEventHookContainerClass {
	
	@ForgeSubscribe
	public void EntitySelfCleansingPotion(LivingUpdateEvent event){
		if(!event.entityLiving.worldObj.isRemote && PotionCleansingTicker.isEntityPresentInMap(event.entityLiving.entityId)){
			EntityLiving entityWithPotions = event.entityLiving;
			Collection potionEffectsToRemove = PotionCleansingTicker.getValuesOfEntity(entityWithPotions.entityId);
			for (Object potionEffectID : potionEffectsToRemove) {
				entityWithPotions.removePotionEffect((Integer)potionEffectID);
			}
			PotionCleansingTicker.removeEntityandValues(entityWithPotions.entityId);
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
