package projectzulu.common.mobs.entity;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFlyingWander;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIVultureFollow;
import cpw.mods.fml.common.Loader;

public class EntityVulture extends EntityGenericAnimal {
	public EntityModelRotation eFEETROT = new EntityModelRotation();
	public EntityModelRotation eBODYROT = new EntityModelRotation();

	public EntityModelRotation eLEFTWING = new EntityModelRotation();
	public EntityModelRotation ewingleft4 = new EntityModelRotation();
	public EntityModelRotation ewingleft3 = new EntityModelRotation();
	public EntityModelRotation ewingleft2 = new EntityModelRotation();
	public EntityModelRotation ewingleft1 = new EntityModelRotation();

	public EntityModelRotation eRIGTHWING = new EntityModelRotation();
	public EntityModelRotation ewingrig4 = new EntityModelRotation();
	public EntityModelRotation ewingrig3 = new EntityModelRotation();
	public EntityModelRotation ewingrig2 = new EntityModelRotation();
	public EntityModelRotation ewingrig1 = new EntityModelRotation();

	public EntityModelRotation eNECKROT1 = new EntityModelRotation();
	public EntityModelRotation eNECKROT2 = new EntityModelRotation();
	public EntityModelRotation eNECKROT3 = new EntityModelRotation();
	public EntityModelRotation eHEADROT = new EntityModelRotation();
	public EntityModelRotation eTAILROT = new EntityModelRotation();
	
	boolean manyVultures = false;
	private int maxTargetHealthToAttack = 25; //15
	float curiosity = 0;
	int ticksToCheckAbilities = 3;
	
	public EntityVulture(World par1World) {
		super(par1World);
		//noClip = true;
		this.setSize(1.0f, 1.4f);
		this.moveSpeed = 0.18f;
		
		this.maxFlightHeight = 20;
		this.getNavigator().setAvoidsWater(true);	
		
        this.tasks.addTask(2, new EntityAIVultureFollow(this, this.moveSpeed, false).setValidStates(EnumSet.of(EntityStates.following)) );
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
		this.tasks.addTask(6, new EntityAIFlyingWander(this, this.moveSpeed));
		
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));		
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking, EntityStates.following), EntityPlayer.class, 16.0F, 0, true));
	}

	@Override
	public boolean defaultGrounded(){
		return false;
	}
	
	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	@Override
    protected void fall(float par1){}

	@Override
	public String getTexture(){
		this.texture = DefaultProps.mobDiretory + "vulture.png";
		return super.getTexture();
	}
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
    protected String getLivingSound(){ return "sounds.vulturehurt"; }

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
    protected String getHurtSound(){ return "sounds.vulturehurt"; }
	
	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		boolean wasSuccesful = false;
		
		if (CustomEntityList.VULTURE.modData.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere() 
				&& worldObj.getClosestPlayerToEntity(this, 32) == null && this.worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1
				&& worldObj.canBlockSeeTheSky(var1, var2, var3) ){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.VULTURE.modData.get().reportSpawningInLog){
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
		return 14;
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();		
		
		if(ticksExisted % ticksToCheckAbilities == 0){
			
			/* Check if their is a nearby Player to Follow */
			EntityPlayer nearbyPlayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);
			if(nearbyPlayer != null){
				int distToTargetXZ = (int) Math.sqrt( Math.pow(nearbyPlayer.posX-this.posX, 2) + Math.pow(nearbyPlayer.posZ-this.posZ, 2) ); 
				if(distToTargetXZ < 16){
					curiosity  = 140;
				}
			}
			shouldFollow = curiosity > 0 ? true : false;
			curiosity = Math.max(curiosity - ticksToCheckAbilities, 0);
			
			/* Assuming we're following a Player, check if We Should Attack by Comparing number of Nearby Vultures to the Health of our Target */
			Entity targetedEntity = nearbyPlayer;
			if(curiosity > 0 && targetedEntity != null){
				int nearbyVultures = 0;
				AxisAlignedBB var15 = this.boundingBox.copy();
				var15 = var15.expand(10, 10, 10);
				List nearbyEntities = this.worldObj.getEntitiesWithinAABB(EntityVulture.class, var15);
				if (nearbyEntities != null && !nearbyEntities.isEmpty()){
					Iterator var10 = nearbyEntities.iterator();

					while (var10.hasNext()){
						Entity var4 = (Entity)var10.next();
						if (var4 instanceof EntityVulture && 
								( ((EntityVulture)var4).getEntityState() == EntityStates.following  
								|| ((EntityVulture)var4).getEntityState() == EntityStates.attacking ) ){
							nearbyVultures += 1;
						}
					}
				}
				if(((EntityLiving)targetedEntity).getHealth() < maxTargetHealthToAttack && ((EntityLiving)targetedEntity).getHealth() < nearbyVultures*4  ){
					setAngerLevel(400);
				}
			}
		}
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(2 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.VULTURE.modData.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}


	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID,1,16), 1);
		}
		super.dropRareDrop(par1);
	}

}
