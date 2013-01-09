package projectzulu.common.core;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import projectzulu.common.mobs.EntityMimic;
import projectzulu.common.mobs.EntityMummy;
import projectzulu.common.mobs.EntityMummyPharaoh;
import projectzulu.common.mobs.EntitySandWorm;
import projectzulu.common.mobs.EntityTreeEnt;

public class SoundHookContainerClass {

	Random classRand = new Random();

    @ForgeSubscribe
    public void soundOnHurt(LivingHurtEvent livingHurtEvent){
    	//Get Instance of World
    	World worldObj = ModLoader.getMinecraftInstance().theWorld;
    	//Copy of Entity
    	Entity hurtEntity = livingHurtEvent.entity;
    	if(worldObj != null && hurtEntity != null && Minecraft.getMinecraft().thePlayer != null){
        	if (livingHurtEvent.entity instanceof EntitySandWorm) {
        		worldObj.playSound(hurtEntity.posX, hurtEntity.posY, hurtEntity.posZ, "sounds.sandwormroar", 1.0f, 1.0f, false);
    		}else if (livingHurtEvent.entity instanceof EntityMummy && 10-classRand.nextInt(10) >= 0) {
        		worldObj.playSound(hurtEntity.posX, hurtEntity.posY, hurtEntity.posZ, "sounds.MummyShortRoar", 1.0f, 1.0f, false);
    		}else if (livingHurtEvent.entity instanceof EntityMummyPharaoh && 10-classRand.nextInt(10) >= 0) {
        		worldObj.playSound(hurtEntity.posX, hurtEntity.posY, hurtEntity.posZ, "sounds.MummyShortRoar", 2.0f, 1.0f, false);
    		}
    		else if (livingHurtEvent.entity instanceof EntityTreeEnt) {
        		worldObj.playSound(hurtEntity.posX, hurtEntity.posY, hurtEntity.posZ, "sounds.treeenthurt", 1.0f, 1.0f, false);
    		}else if (livingHurtEvent.entity instanceof EntityMimic) {
        		worldObj.playSound(hurtEntity.posX, hurtEntity.posY, hurtEntity.posZ, "sounds.mimicliving", 1.0f, 1.0f, false);
    		}
    	}
    }
    
}
