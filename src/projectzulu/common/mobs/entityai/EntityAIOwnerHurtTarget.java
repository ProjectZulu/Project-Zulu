package projectzulu.common.mobs.entityai;

import net.minecraft.entity.EntityLiving;
import projectzulu.common.mobs.EntityGenericTameable;

public class EntityAIOwnerHurtTarget extends EntityAITarget{
	EntityGenericTameable theEntityTameable;
    EntityLiving theTarget;

    public EntityAIOwnerHurtTarget(EntityGenericTameable par1EntityTameable){
        super(par1EntityTameable, 32.0F, false);
        this.theEntityTameable = par1EntityTameable;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute(){
        if (!this.theEntityTameable.isTamed()){
            return false;
        }
        else{
            EntityLiving var1 = this.theEntityTameable.getOwner();

            if (var1 == null){
            	return false;
            }else{
                this.theTarget = var1.getLastAttackingEntity();
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

