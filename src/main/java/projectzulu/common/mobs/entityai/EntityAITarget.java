package projectzulu.common.mobs.entityai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import projectzulu.common.mobs.entity.EntityGenericCreature;
import projectzulu.common.mobs.entity.EntityGenericTameable;

public abstract class EntityAITarget extends EntityAIBase {

    /** The entity that this task belongs to */
    protected EntityLiving taskOwner;
    protected float targetDistance;

    /**
     * If true, EntityAI targets must be able to be seen (cannot be blocked by walls) to be suitable targets.
     */
    protected boolean shouldCheckSight;
    /*
     * I believe This Toggles whether the final point in the path is evaluated to ensure the Entity can Reach Its
     * Destination : Can be Wonky on Larger Entities
     */
    private boolean field_75303_a;
    /* Controls if Path is Valid, 0:SearchForPath, 1:ValidPath, 2:InvalidPath */
    private int pathState; // 0 : Check if Path, 1 :
    private int pathCheckCooldown;
    private int field_75298_g;

    public EntityAITarget(EntityLiving par1EntityLiving, float par2, boolean par3) {
        this(par1EntityLiving, par2, par3, false);
    }

    public EntityAITarget(EntityLiving par1EntityLiving, float par2, boolean par3, boolean par4) {
        this.pathState = 0;
        this.pathCheckCooldown = 0;
        this.field_75298_g = 0;
        this.taskOwner = par1EntityLiving;
        this.targetDistance = par2;
        this.shouldCheckSight = par3;
        this.field_75303_a = par4;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        EntityLivingBase attackTarget = this.taskOwner.getAttackTarget();

        if (attackTarget == null) {
            return false;
        } else if (!attackTarget.isEntityAlive()) {
            return false;
        } else if (this.taskOwner.getDistanceSqToEntity(attackTarget) > (double) (this.targetDistance * this.targetDistance)) {
            return false;
        } else {
            double followDistance = getFollowDistance();
            if (taskOwner.getDistanceSqToEntity(attackTarget) > followDistance * followDistance) {
                return false;
            } else if (this.shouldCheckSight) {
                if (this.taskOwner.getEntitySenses().canSee(attackTarget)) {
                    this.field_75298_g = 0;
                } else if (++this.field_75298_g > 60) {
                    return false;
                }
            }
            return true;
        }
    }

    protected double getFollowDistance() {
        IAttributeInstance attributeinstance = this.taskOwner.getEntityAttribute(SharedMonsterAttributes.followRange);
        return attributeinstance == null ? 16.0D : attributeinstance.getAttributeValue();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.pathState = 0;
        this.pathCheckCooldown = 0;
        this.field_75298_g = 0;
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.taskOwner.setAttackTarget(null);
    }

    /**
     * A method used to see if an entity is a suitable target through a number of checks.
     */
    protected boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2) {
        if (par1EntityLiving == null) {
            return false;
        } else if (par1EntityLiving == this.taskOwner) {
            return false;
        } else if (!par1EntityLiving.isEntityAlive()) {
            return false;
        } else if (!this.taskOwner.canAttackClass(par1EntityLiving.getClass())) {
            return false;
        } else {
            if (this.taskOwner instanceof EntityTameable && ((EntityTameable) this.taskOwner).isTamed()) {
                if (par1EntityLiving instanceof EntityTameable && ((EntityTameable) par1EntityLiving).isTamed()) {
                    return false;
                }

                if (par1EntityLiving == ((EntityTameable) this.taskOwner).getOwner()) {
                    return false;
                }
            } else if (this.taskOwner instanceof EntityGenericTameable
                    && ((EntityGenericTameable) this.taskOwner).isTamed()) {
                if (par1EntityLiving instanceof EntityGenericTameable
                        && ((EntityGenericTameable) par1EntityLiving).isTamed()) {
                    return false;
                }

                if (par1EntityLiving == ((EntityGenericTameable) this.taskOwner).getOwner()) {
                    return false;
                }
            } else if (par1EntityLiving instanceof EntityPlayer && !par2
                    && ((EntityPlayer) par1EntityLiving).capabilities.disableDamage) {
                return false;
            }

            if (this.taskOwner instanceof EntityGenericCreature
                    && !((EntityGenericCreature) taskOwner).isWithinHomeDistance(
                            MathHelper.floor_double(par1EntityLiving.posX),
                            MathHelper.floor_double(par1EntityLiving.posY),
                            MathHelper.floor_double(par1EntityLiving.posZ))) {
                return false;
            } else if (this.taskOwner instanceof EntityCreature
                    && !((EntityCreature) taskOwner).isWithinHomeDistance(MathHelper.floor_double(par1EntityLiving.posX),
                            MathHelper.floor_double(par1EntityLiving.posY),
                            MathHelper.floor_double(par1EntityLiving.posZ))) {
                return false;
            } else if (this.shouldCheckSight && !this.taskOwner.getEntitySenses().canSee(par1EntityLiving)) {
                return false;
            } else {
                if (this.field_75303_a) {
                    if (--this.pathCheckCooldown <= 0) {
                        this.pathState = 0;
                    }

                    if (this.pathState == 0) {
                        this.pathState = this.func_75295_a(par1EntityLiving) ? 1 : 2;
                    }

                    if (this.pathState == 2) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    private boolean func_75295_a(EntityLivingBase par1EntityLiving) {
        this.pathCheckCooldown = 10 + this.taskOwner.getRNG().nextInt(5);
        PathEntity var2 = this.taskOwner.getNavigator().getPathToEntityLiving(par1EntityLiving);

        if (var2 == null) {
            return false;
        } else {
            PathPoint var3 = var2.getFinalPathPoint();

            if (var3 == null) {
                return false;
            } else {
                int var4 = var3.xCoord - MathHelper.floor_double(par1EntityLiving.posX);
                int var5 = var3.zCoord - MathHelper.floor_double(par1EntityLiving.posZ);
                return (double) (var4 * var4 + var5 * var5) <= 2.25D;
            }
        }
    }
}
