package projectzulu.common.mobs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeInstance;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;

public abstract class EntityGenericCreature extends EntityAerial
{
    private PathEntity pathToEntity;
	public boolean hasPath(){ return this.pathToEntity != null; }
    
    /** The Entity this EntityCreature is set to attack. */
    protected Entity entityToAttack;
    public Entity getEntityToAttack(){ return this.entityToAttack; }
    public void setTarget(Entity par1Entity){ this.entityToAttack = par1Entity; }
    
    private float maximumHomeDistance = -1.0F;
    private ChunkCoordinates homePosition = new ChunkCoordinates(0, 0, 0);

    /**
     * returns true if a creature has attacked recently only used for creepers and skeletons
     */
    /* Do I need this? */
//    protected boolean hasAttacked = false;

    /** Used to make a creature speed up and wander away when hit. */
    protected int fleeingTick = 0;
	public int getFleeTick() { return fleeingTick; }	
	public void setFleeTick(int fleeingTick) {	this.fleeingTick = fleeingTick; }

	/* Entity State Variables */
    protected int animTime = 0;
    public int getAnimTime(){ return animTime;  }
    public void setAnimTime(int animTime){	this.animTime = animTime;   }
    
	protected int angerLevel = 0;
	public int getAngerLevel() { return angerLevel; }	
	public void setAngerLevel(int angerLevel) {	this.angerLevel = angerLevel; }

	
    protected EntityStates entityState = EntityStates.idle;   
    public EntityStates getEntityState(){ return entityState; }
    
    public EntityGenericCreature(World par1World){
        super(par1World);
    }
        
	@Override
	protected boolean isAIEnabled() {
		return true;
	}
	
	@Override
	protected void updateAITasks() {
		super.updateAITasks();
    	attackTime = Math.max(attackTime-1, 0);
    	fleeingTick = Math.max(fleeingTick-1, 0);
    	angerLevel = Math.max(angerLevel-1, 0);
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
    	if(entityEntry != null){
    		return entityEntry.modData.get().maxSpawnInChunk;
    	}else{
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
