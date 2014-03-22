package projectzulu.common.mobs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.mobs.entityai.EntityAIMoveTowardsRestriction;

public abstract class EntityGenericCreature extends EntityAerial {
    private PathEntity pathToEntity;

    public boolean hasPath() {
        return this.pathToEntity != null;
    }

    /** The Entity this EntityCreature is set to attack. */
    protected Entity entityToAttack;

    public Entity getEntityToAttack() {
        return this.entityToAttack;
    }

    public void setTarget(Entity par1Entity) {
        this.entityToAttack = par1Entity;
    }

    private float maximumHomeDistance = -1.0F;
    private ChunkCoordinates homePosition = new ChunkCoordinates(0, 0, 0);
    private boolean field_110180_bt;
    // Cooldown instituted on the entity when it attempts to find a path but fails.
    private int leashPathCooldown = 0;
    private EntityAIBase field_110178_bs = new EntityAIMoveTowardsRestriction(this, 1.0f);

    /**
     * returns true if a creature has attacked recently only used for creepers and skeletons
     */
    /* Do I need this? */
    // protected boolean hasAttacked = false;

    /** Used to make a creature speed up and wander away when hit. */
    protected int fleeingTick = 0;

    public int getFleeTick() {
        return fleeingTick;
    }

    public void setFleeTick(int fleeingTick) {
        this.fleeingTick = fleeingTick;
    }

    /* Entity State Variables */
    protected int animTime = 0;

    public int getAnimTime() {
        return animTime;
    }

    public void setAnimTime(int animTime) {
        this.animTime = animTime;
    }

    protected int angerLevel = 0;

    public int getAngerLevel() {
        return angerLevel;
    }

    public void setAngerLevel(int angerLevel) {
        this.angerLevel = angerLevel;
    }

    protected EntityStates entityState = EntityStates.idle;

    public EntityStates getEntityState() {
        return entityState;
    }

    public EntityGenericCreature(World par1World) {
        super(par1World);
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        attackTime = Math.max(attackTime - 1, 0);
        fleeingTick = Math.max(fleeingTick - 1, 0);
        angerLevel = Math.max(angerLevel - 1, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        leashPathCooldown = Math.max(leashPathCooldown - 1, 0);
    }

    @Override
    protected void updateLeashedState() {
        super.updateLeashedState();

        if (this.getLeashed() && this.getLeashedToEntity() != null
                && this.getLeashedToEntity().worldObj == this.worldObj) {
            Entity entity = this.getLeashedToEntity();
            this.setHomeArea((int) entity.posX, (int) entity.posY, (int) entity.posZ, 5);
            float f = this.getDistanceToEntity(entity);

            if (this instanceof EntityGenericTameable && ((EntityGenericTameable) this).isSitting()) {
                if (f > 10.0F) {
                    this.clearLeashed(true, true);
                }

                return;
            }

            if (!this.field_110180_bt) {
                this.tasks.addTask(2, this.field_110178_bs);
                this.getNavigator().setAvoidsWater(false);
                this.field_110180_bt = true;
            }

            this.func_142017_o(f);
            if (leashPathCooldown == 0 && f > 4.0F) {
                boolean foundPath = this.getNavigator().tryMoveToEntityLiving(entity, 1.0D);
                if (!foundPath) {
                    leashPathCooldown = 20;
                }
            }

            if (f > 6.0F) {
                double d0 = (entity.posX - this.posX) / (double) f;
                double d1 = (entity.posY - this.posY) / (double) f;
                double d2 = (entity.posZ - this.posZ) / (double) f;
                this.motionX += d0 * Math.abs(d0) * 0.4D;
                this.motionY += d1 * Math.abs(d1) * 0.4D;
                this.motionZ += d2 * Math.abs(d2) * 0.4D;
            }

            if (f > 10.0F) {
                this.clearLeashed(true, true);
            }
        } else if (!this.getLeashed() && this.field_110180_bt) {
            this.field_110180_bt = false;
            this.tasks.removeTask(this.field_110178_bs);
            this.getNavigator().setAvoidsWater(true);
            this.detachHome();
        }
    }

    protected void func_142017_o(float par1) {
    }

    @Override
    public int getMaxSpawnedInChunk() {
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
        if (entityEntry != null) {
            return entityEntry.modData.get().maxSpawnInChunk;
        } else {
            return super.getMaxSpawnedInChunk();
        }
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    public float getBlockPathWeight(int par1, int par2, int par3) {
        return 0.0F;
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity par1Entity, float par2) {
    }

    /**
     * Returns true if entity is within home distance from current position
     */
    public boolean isWithinHomeDistanceCurrentPosition() {
        return this.isWithinHomeDistance(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY),
                MathHelper.floor_double(this.posZ));
    }

    public boolean isWithinHomeDistance(int par1, int par2, int par3) {
        return maximumHomeDistance == -1.0F ? true
                : homePosition.getDistanceSquared(par1, par2, par3) < maximumHomeDistance * maximumHomeDistance;
    }

    public void setHomeArea(int par1, int par2, int par3, int par4) {
        this.homePosition.set(par1, par2, par3);
        this.maximumHomeDistance = (float) par4;
    }

    public ChunkCoordinates getHomePosition() {
        return this.homePosition;
    }

    public float getMaximumHomeDistance() {
        return this.maximumHomeDistance;
    }

    public void detachHome() {
        this.maximumHomeDistance = -1.0F;
    }

    public boolean hasHome() {
        return this.maximumHomeDistance != -1.0F;
    }
}
