package projectzulu.common.mobs.entityai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAISmoothSwimming extends EntityAIBase{
    private EntityLiving theEntity;
    private boolean smootherSwimming = false;

    public EntityAISmoothSwimming(EntityLiving par1EntityLiving){
        this.theEntity = par1EntityLiving;
        this.setMutexBits(4);
        par1EntityLiving.getNavigator().setCanSwim(true);
    }
    
    public EntityAISmoothSwimming(EntityLiving par1EntityLiving, boolean smootherSwimming){
    	this(par1EntityLiving);
    	this.smootherSwimming = smootherSwimming;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute(){
        return this.theEntity.isInWater() || this.theEntity.handleLavaMovement();
    }

    /**
     * Updates the task
     */
    public void updateTask(){
//        if(smootherSwimming 
////        		&& this.theEntity.getRNG().nextFloat() < 0.8F 
////        		&& !theEntity.worldObj.isAirBlock((int)theEntity.posX, (int)theEntity.posY+1, (int)theEntity.posZ) 
//        		&&  !theEntity.worldObj.isAirBlock((int)theEntity.posX, (int)theEntity.posY+1, (int)theEntity.posZ)){
//            this.theEntity.getJumpHelper().setJumping();
//        }
        
        if(this.theEntity.getRNG().nextFloat() < 0.8F){
            this.theEntity.getJumpHelper().setJumping();
        }
    }
}
