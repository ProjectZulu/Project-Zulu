package projectzulu.common.mobs;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.API.CustomEntityList;
import projectzulu.common.API.ItemBlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFollowOwner;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIOwnerHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIOwnerHurtTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityMammoth extends EntityGenericAnimal implements IAnimals{	
	public EntityModelRotation eWHOLE = new EntityModelRotation();
	public EntityModelRotation eHEADPIECE = new EntityModelRotation();
	public EntityModelRotation eREARRTR1 = new EntityModelRotation();
	public EntityModelRotation eREARRTR2 = new EntityModelRotation();
	public EntityModelRotation eREARRTR3 = new EntityModelRotation();
	public EntityModelRotation etail = new EntityModelRotation();
	public EntityModelRotation eleg3 = new EntityModelRotation();
	public EntityModelRotation eleg4 = new EntityModelRotation();

	//	public int stampedeTime = 0;
	Vec3 stampedeDirection = Vec3.createVectorHelper(0, 0, 0);
	public int timeLeftStampeding = 0;

	public EntityMammoth(World par1World) {
		super(par1World);
		setSize(4.5f, 5.4f);
		this.moveSpeed = 0.3f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));

		this.tasks.addTask(2, new EntityAIStayStill(this, EntityStates.sitting));

		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false, 5.5f*5.5f));
		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));
		
		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.egg.shiftedIndex, false));
		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 120));

		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityLiving.class, 16.0F, 0, false, false, IMob.mobSelector));
	}
	
	public float getAttackDistance() {
		return isChild() ? 3.0f : 6.0f;
	}
	
	@Override
	protected int getAttackStrength(World par1World) {
		switch (par1World.difficultySetting) {
		case 0:
			return 5; 
		case 1:
			return 6; 
		case 2:
			return 8; 
		case 3:
			return 9; 
		default:
			return 5;
		}
	}

	@Override
	public String getTexture() {
		if(getSaddled()){
			if(worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ) == BiomeGenBase.taiga 
					|| worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ) == BiomeGenBase.taigaHills){
				this.texture = "/mods/Mammoth_Snow_Saddle.png";
			}else{
				this.texture = "/mods/Mammoth_Saddle.png";
			}

		}else{
			if(worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ) == BiomeGenBase.taiga 
					|| worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ) == BiomeGenBase.taigaHills){
				this.texture = "/mods/Mammoth_Snow.png";
			}else{
				this.texture = "/mods/Mammoth.png";
			}
		}
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
		
		if (CustomEntityList.mammoth.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
				&& worldObj.canBlockSeeTheSky(var1, var2, var3))  {
			wasSuccesful = true;
		}
		
		if(CustomEntityList.mammoth.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}
	
	@Override
	public int getMaxHealth(){return 30;}

	@Override
	public int getTotalArmorValue(){return 6;}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound(){return "mods.sounds.mammothliving";}

	@Override
	public void knockBack(Entity par1Entity, int par2, double par3, double par5) {
		this.isAirBorne = true;
		float var7 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
		float var8 = 0.4F;
		this.motionX /= 2.0D;
		this.motionY /= 2.0D;
		this.motionZ /= 2.0D;
		this.motionX -= par3 / (double)var7 * (double)var8*0.2;
		this.motionY += (double)var8;
		this.motionZ -= par5 / (double)var7 * (double)var8*0.2;

		if (this.motionY > 0.1000000059604645D)
		{
			this.motionY = 0.1000000059604645D;
		}
	}
	
	
	
//	@Override
//	protected void UpdateTargetBasedOnState() {
//		super.UpdateTargetBasedOnState();
//		/* If Targetting Ridding Player, set target to null*/
//		if( riddenByEntity!= null && targetedEntity!= null && targetedEntity instanceof EntityPlayer 
//				&& ((EntityPlayer)riddenByEntity).username.equals( ((EntityPlayer)targetedEntity).username) ){
//			targetedEntity = null;
//		}
//	};

	public void causeStampede(Vec3 locationOfCause){
//		this.pathToEntity = null;
		timeLeftStampeding = 20*10;
		List<Vec3> centroids = new ArrayList<Vec3>();

		AxisAlignedBB var15 = this.boundingBox.copy();
		var15 = var15.expand(100, 60, 100);
		List nearbyEntities = this.worldObj.getEntitiesWithinAABB(EntityMammoth.class, var15);
		Vec3 herdCentroid = Vec3.createVectorHelper(0, 0, 0);
		int i = 0;
		if(nearbyEntities != null && !nearbyEntities.isEmpty()){
			Iterator var10 = nearbyEntities.iterator();
			while (var10.hasNext()){
				Entity var4 = (Entity)var10.next();

				if (var4 instanceof EntityMammoth){
					//Notify Mammoth that he should be stampeding, If the lazy bastard isn't
					if( ((EntityMammoth) var4).timeLeftStampeding == 0 ){
						((EntityMammoth) var4).causeStampede(locationOfCause);
					}
					centroids.add(i, Vec3.createVectorHelper(var4.posX, var4.posY, var4.posZ) );
					herdCentroid = Vec3.createVectorHelper(herdCentroid.xCoord + var4.posX, herdCentroid.yCoord + var4.posY, herdCentroid.zCoord + var4.posZ);
					i++;
				}
			}
		}
		//		herdCentroid = Vec3.createVectorHelper(herdCentroid.xCoord/i, herdCentroid.yCoord/i, herdCentroid.zCoord/i);
		herdCentroid = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);

		stampedeDirection = Vec3.createVectorHelper( 
				(herdCentroid.xCoord - locationOfCause.xCoord), 
				(herdCentroid.yCoord - locationOfCause.yCoord),
				(herdCentroid.zCoord - locationOfCause.zCoord) 
				);
		if( Math.abs(stampedeDirection.xCoord) < 6 && Math.abs(stampedeDirection.zCoord) < 6 ) {
			stampedeDirection.xCoord = stampedeDirection.xCoord*6;
			stampedeDirection.zCoord = stampedeDirection.zCoord*6;
		}
		if( Math.abs(stampedeDirection.xCoord) > 32 || Math.abs(stampedeDirection.zCoord) > 32 ) {
			stampedeDirection.xCoord = stampedeDirection.xCoord/2;
			stampedeDirection.zCoord = stampedeDirection.zCoord/2;
		}

		Vec3 locToStamp = Vec3.createVectorHelper(
				this.posX + stampedeDirection.xCoord, 
				worldObj.getTopSolidOrLiquidBlock( (int)(this.posX + stampedeDirection.xCoord), (int)(this.posY + stampedeDirection.zCoord)), 
				this.posZ + stampedeDirection.zCoord);
//		this.pathToEntity = this.worldObj.getEntityPathToXYZ(this, (int)locToStamp.xCoord, (int)locToStamp.yCoord, (int)locToStamp.zCoord, 32f, false, true, false, true);
	}
	
	protected void riderAttackNearby(){
		/* Search For Nearby Entities */
		/* Select ones To Attack */
		AxisAlignedBB var15 = this.boundingBox.copy();
		var15 = var15.expand(10, 10, 10);
		List nearbyEntities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, var15);

		int i = 0;
		if(nearbyEntities != null && !nearbyEntities.isEmpty()){
			Iterator var10 = nearbyEntities.iterator();
			while (var10.hasNext()){
				Entity var4 = (Entity)var10.next();
				if (var4 instanceof EntityPlayer && ((EntityPlayer)riddenByEntity).username.equals( ((EntityPlayer)var4).username )   ){
					
				}else{
					if( var4.getDistanceToEntity(this) < getAttackDistance() ){
						attackEntityAsMob(var4);
					}
				}
			}
		}
		this.attackTime = 20;
		worldObj.playSound(this.posX, this.posY, this.posZ, "mods.sounds.mammothstomp", 1.0f, 1.0f, false);
	}
	
	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		if(itemStack != null && itemStack.getItem().shiftedIndex == Block.leaves.blockID){
			return true;
		}else{
			return super.isValidBreedingItem(itemStack);
		}
	}
	
	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(4 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.mammoth.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}

	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && ItemBlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(ItemBlockList.mobHeads.get().blockID,1,11), 1);
		}
		super.dropRareDrop(par1);
	}

}