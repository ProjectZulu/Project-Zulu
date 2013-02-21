package projectzulu.common.mobs.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.PacketIDs;
import projectzulu.common.mobs.packets.PacketManagerAnimTime;
import cpw.mods.fml.common.network.PacketDispatcher;

public class EntityGenericAnimal extends EntityGenericTameable {
	
    /* Fixed Variables */
    public int maxAnimTime = 20;
    public boolean forceDespawn = false;
	public EntityGenericAnimal(World par1World) {
		super(par1World);
		experienceValue = 3;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		/* Entity State */
		this.dataWatcher.addObject(20, Short.valueOf((short) 0));
	}
	
	protected void updateDWEntityState(EntityStates entityState) {
		this.dataWatcher.updateObject(20, (short)(entityState.index) );
	}
	public int getDWEntityState() {
		return this.dataWatcher.getWatchableObjectShort(20);
	}
	
    @Override
    protected void updateAITasks() {
    	updateAIState();    	
    	super.updateAITasks();
    	if(shouldAttack){
    		this.setAngerLevel(3);
    	}
    	updateDWEntityState(entityState);
    }
    
    public void updateAIState() {
    	if(worldObj.difficultySetting == 0 && ProjectZulu_Core.despawnInPeaceful && !isTamed()){
    		this.setDead();
    	}
    	/*AI Updates are done Before UpdateTasks such that some states can be manually triggered */
    	if(fleeingTick > 0){
    		entityState = EntityStates.fleeing;
    	}else if(loveTimer > 0){
    		entityState = EntityStates.inLove;
    	}else if(angerLevel > 0 && (getAttackTarget() != null || getAITarget() != null)){
    		entityState = EntityStates.attacking;
    	}else if(angerLevel > 0){
    		entityState = EntityStates.looking;
    	}else if(isSitting()){
    		entityState = EntityStates.sitting; //TODO add Check that if Player doesn't Exists, it should Sit. Should I do this? I'm Favoring Not.
    	}else if(shouldFollow){
    		entityState = EntityStates.following;
    	}else{
    		entityState = EntityStates.idle;
    	}
    }
    
    @Override
    public void onLivingUpdate() {
		rotationYaw = rotationYawHead;
    	super.onLivingUpdate();
    	animTime = Math.max(animTime - 1, 0);
    	entityState = EntityStates.getEntityByIndex(getDWEntityState());
    }
    
    @Override
    protected boolean canDespawn() {
    	CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
    	if(entityEntry != null){
    		return forceDespawn || entityEntry.modData.get().shouldDespawn;
    	}
    	return true;
    }
    
    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
    	if (par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY) {
        	animTime = maxAnimTime;
        	
        	if(!worldObj.isRemote){
        		PacketManagerAnimTime packetAnimTime = (PacketManagerAnimTime) PacketIDs.animTime.createPacketManager();
            	packetAnimTime.setPacketData(entityId, animTime);
        		PacketDispatcher.sendPacketToAllAround(posX, posY, posZ, 30, this.dimension, packetAnimTime.createPacket());		
        	}
        	
	        int var2 = getAttackStrength(par1Entity.worldObj);
	        if (this.isPotionActive(Potion.damageBoost)) {
	            var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
	        }
	        if (this.isPotionActive(Potion.weakness)) {
	            var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
	        }

	        par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
	        return super.attackEntityAsMob(par1Entity);
		}
    	return false;
    }
    
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (super.attackEntityFrom(par1DamageSource, par2)) {
			if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
				EntityPlayer attackingPlayer = (EntityPlayer)par1DamageSource.getEntity();
				
				if (shouldNotifySimilar(attackingPlayer)){
					List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(20.0D, 20.0D, 20.0D));
					Iterator nearbyEntityIterator = var4.iterator();
					
					while (nearbyEntityIterator.hasNext()){
						Entity nearbyEntity = (Entity)nearbyEntityIterator.next();
						if (nearbyEntity.getClass().equals(this.getClass()) ){
							EntityGenericAnimal nearbyAlly = (EntityGenericAnimal)nearbyEntity;
							nearbyAlly.entityAttackedReaction(attackingPlayer);
						}
					}
				}
				entityAttackedReaction(attackingPlayer);
				return true;
			}
		}
		return false;
	}
	
	protected void entityAttackedReaction(EntityPlayer attackingPlayer){
		if(this.isTamed()){
			
		}else{
			if (shouldPanic()) {
				setFleeTick(80);
			}else{
				setAngerLevel(400);
			}
		}
	}
	
	public boolean shouldNotifySimilar(EntityPlayer attackingPlayer){
		return false;
	}
	
	/**
	 * Function that determines if the Fight/Flight reaction of the animal on attack
	 */
	protected boolean shouldPanic(){
		return false;
	}
	
	/** 
	 * Set Entity Attack Strength
	 * This is overriden by each Entity if deviations from default are desired
	 **/
	protected int getAttackStrength(World par1World){
		switch (par1World.difficultySetting) {
		case 0:
			return 3; 
		case 1:
			return 4; 
		case 2:
			return 5; 
		case 3:
			return 6; 
		default:
			return 3;
		}
	}
	
    /**
     * This method returns a value to be applied directly to entity speed, this factor is less than 1 when a slowdown
     * potion effect is applied, more than 1 when a haste potion effect is applied and 2 for fleeing entities.
     */
    public float getSpeedModifier() {
        float var1 = super.getSpeedModifier();
        
        if (this.fleeingTick > 0 && !this.isAIEnabled()) {
            var1 *= 2.0F;
        }
        return var1;
    }
    
    @Override
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
    	if(isTamed()){
    		return 0;
    	}else{
    		if(this instanceof IMob || getEntityState() == EntityStates.attacking || getEntityState() == EntityStates.looking){
    			return 5;
    		}else{
    			return rand.nextInt(2)+1;
    		}
    	}
    }
    
	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere(){
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		
		return isValidLightLevel(worldObj, var1, var2, var3) //&& this.worldObj.canBlockSeeTheSky(var1, var2, var3)
				&& 	super.getCanSpawnHere();
	}
	
	protected boolean isValidLightLevel(World world, int xCoord, int yCoord, int zCoord){
		return worldObj.getSavedLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord) < 1;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setBoolean("ForceDespawn", forceDespawn);
		
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		forceDespawn = par1nbtTagCompound.getBoolean("ForceDespawn");
	}
}
