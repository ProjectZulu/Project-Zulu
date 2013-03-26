package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityMimic extends EntityGenericAnimal implements IMob {	
	
	protected int wakeUpTimer = 0;
	public int getWakeUpTimer(){
		return wakeUpTimer;
	}
	boolean shouldHover = false;

	public EntityMimic(World par1World){
		super(par1World);
		this.moveSpeed = 0.2f;
		setSize(1.0f, 1.5f);
		
		this.moveSpeed = 0.4f;
		this.getNavigator().setAvoidsWater(true);
		
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));
		this.tasks.addTask(2, new EntityAIStayStill(this, EntityStates.idle));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
		this.tasks.addTask(5, new EntityAIWander(this, this.moveSpeed, 120));

		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
	}

	public EntityMimic(World par1World, double parx, double pary, double parz, boolean shouldHover){
		this(par1World);
		setLocationAndAngles(parx, pary, parz, 1, 1);
		setPosition(parx, pary, parz);
		this.shouldHover = shouldHover;
	}

	@Override
	public String getTexture() {
		this.texture = DefaultProps.mobDiretory + "mimicchest.png";
		return super.getTexture();
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		boolean wasSuccesful = false;
		
		if (CustomEntityList.MIMIC.modData.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere() ) {
			wasSuccesful = true;
		}
		
		if(CustomEntityList.MIMIC.modData.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}

	@Override
	public int getMaxHealth(){
		return 20;
	}

	@Override
	public int getTotalArmorValue(){
		return 6;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
    protected String getLivingSound(){
		return null;
	}

	@Override
	public void onLivingUpdate() {
		if(shouldHover == true){
			this.motionY = 0;
			if(ticksExisted > (20*10) ){
				shouldHover = false;
			}
		}
		
		super.onLivingUpdate();

		/* If Player is Still Nearby after activation, do not become passive*/
		if(getEntityState() != EntityStates.idle && worldObj.getClosestPlayerToEntity(this, 5D) != null){
			setAngerLevel(400);
		}
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	@Override
	public boolean canBePushed(){
		if(getEntityState() == EntityStates.idle){
			return false;
		}else{
			return !this.isDead;
		}
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	@Override
    protected boolean canDespawn(){
		return false;
	}

	//	Called when player interacts with mob, eg get milk, saddle
	@Override
    public boolean interact(EntityPlayer par1EntityPlayer){
		entityAttackedReaction(par1EntityPlayer);
		return super.interact(par1EntityPlayer);
	}
}