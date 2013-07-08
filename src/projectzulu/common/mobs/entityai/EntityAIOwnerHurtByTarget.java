package projectzulu.common.mobs.entityai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import projectzulu.common.mobs.entity.EntityGenericTameable;

public class EntityAIOwnerHurtByTarget extends EntityAITarget{
	EntityGenericTameable theDefendingTameable;
    EntityLivingBase theOwnerAttacker;
    
    public EntityAIOwnerHurtByTarget(EntityGenericTameable par1EntityTameable){
        super(par1EntityTameable, 32.0F, false);
        this.theDefendingTameable = par1EntityTameable;
        this.setMutexBits(1);
    }
    
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute(){
        if (!this.theDefendingTameable.isTamed()){
            return false;
        }else{
            EntityLivingBase var1 = this.theDefendingTameable.getOwner();

            if (var1 == null){
                return false;
            }
            else{
                this.theOwnerAttacker = var1.getAITarget();
                return this.isSuitableTarget(this.theOwnerAttacker, false);
            }
        }
    }
    
//    /**
//     * Returns whether an in-progress EntityAIBase should continue executing
//     */
//    public boolean continueExecuting() {
//    	return shouldExecute();
//    }
    
    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting(){
        this.taskOwner.setAttackTarget(this.theOwnerAttacker);
        super.startExecuting();
    }
}

