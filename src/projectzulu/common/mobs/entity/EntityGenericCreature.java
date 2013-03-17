package projectzulu.common.mobs.entity;

import projectzulu.common.api.CustomEntityList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public abstract class EntityGenericCreature extends EntityAerial
{
    private PathEntity pathToEntity;
	public boolean hasPath(){ return this.pathToEntity != null; }
    
    /** The Entity this EntityCreature is set to attack. */
    protected Entity entityToAttack;
    public Entity getEntityToAttack(){ return this.entityToAttack; }
    public void setTarget(Entity par1Entity){ this.entityToAttack = par1Entity; }
    
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
    protected void attackEntity(Entity par1Entity, float par2) {}
}
