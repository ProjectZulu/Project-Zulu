package projectzulu.common.mobs.entityai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import projectzulu.common.mobs.entity.EntityGenericCreature;

public class EntityAIHurtByTarget extends EntityAITarget {
    boolean shouldCallAllies;

    /** The PathNavigate of our entity. */
    EntityLivingBase entityPathNavigate;
    int fleeChance = 0;
    /** Task Owner in the form of out Generic Creature, used to access Specialized information, such as Anger or EntityState */
    EntityGenericCreature genericTaskOwner;
    
    public EntityAIHurtByTarget(EntityGenericCreature par1EntityLiving, boolean shouldCallAllies, boolean shouldCheckSight) {
        super(par1EntityLiving, 16.0F, shouldCheckSight);
        this.shouldCallAllies = shouldCallAllies;
        this.setMutexBits(1);
        this.genericTaskOwner = par1EntityLiving;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
    	return this.isSuitableTarget(this.taskOwner.getAITarget(), false);
    	
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
    	return shouldExecute();
//        return this.taskOwner.getAITarget() != null && this.taskOwner.getAITarget() != this.entityPathNavigate;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
        this.entityPathNavigate = this.taskOwner.getAITarget();
        
        if (this.shouldCallAllies){
            List var1 = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), AxisAlignedBB.getBoundingBox(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D).expand((double)this.targetDistance, 4.0D, (double)this.targetDistance));
            Iterator var2 = var1.iterator();

            while (var2.hasNext()){
                EntityLiving var3 = (EntityLiving)var2.next();

                if (this.taskOwner != var3 && var3.getAttackTarget() == null){
                    var3.setAttackTarget(this.taskOwner.getAITarget());
                }
            }
        }
        super.startExecuting();
    }
    
    @Override
    public void updateTask() {
    	startExecuting();
    }
}

