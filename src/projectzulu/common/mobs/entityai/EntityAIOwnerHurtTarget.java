package projectzulu.common.mobs.entityai;

import net.minecraft.entity.EntityLivingBase;
import projectzulu.common.mobs.entity.EntityGenericTameable;

public class EntityAIOwnerHurtTarget extends EntityAITarget{
	EntityGenericTameable theDefendingTameable;
	EntityLivingBase theTarget;

    public EntityAIOwnerHurtTarget(EntityGenericTameable par1EntityTameable){
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
        }
        else{
            EntityLivingBase var1 = this.theDefendingTameable.getOwner();

            if (var1 == null){
            	return false;
            }else{
                this.theTarget = var1.getAITarget();
                return this.isSuitableTarget(this.theTarget, false);
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting(){
        this.taskOwner.setAttackTarget(this.theTarget);
        super.startExecuting();
    }
}

