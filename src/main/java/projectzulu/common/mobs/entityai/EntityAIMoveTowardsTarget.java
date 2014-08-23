package projectzulu.common.mobs.entityai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;
import projectzulu.common.mobs.entity.EntityGenericAnimal;

public class EntityAIMoveTowardsTarget extends EntityAIBase {
    private EntityGenericAnimal theEntity;
    private EntityLivingBase targetEntity;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private float field_75425_f;
    private float field_75426_g;

    public EntityAIMoveTowardsTarget(EntityGenericAnimal par1EntityCreature, float par2, float par3) {
        this.theEntity = par1EntityCreature;
        this.field_75425_f = par2;
        this.field_75426_g = par3;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        this.targetEntity = this.theEntity.getAttackTarget();

        if (this.targetEntity == null) {
            return false;
        }
        else if (this.targetEntity.getDistanceSqToEntity(this.theEntity) > (double)(this.field_75426_g * this.field_75426_g)) {
            return false;
        }
        else {
            Vec3 var1 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 16, 7, Vec3.createVectorHelper(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ));

            if (var1 == null) {
                return false;
            }
            else {
                this.movePosX = var1.xCoord;
                this.movePosY = var1.yCoord;
                this.movePosZ = var1.zCoord;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        return !this.theEntity.getNavigator().noPath() && this.targetEntity.isEntityAlive() && this.targetEntity.getDistanceSqToEntity(this.theEntity) < (double)(this.field_75426_g * this.field_75426_g);
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.targetEntity = null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.field_75425_f);
    }
}
