package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMoveTowardsTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityLizard extends EntityGenericAnimal implements IRangedAttackMob, IMob{
	
	public int counter = 0;
	public boolean prepareToSpit = false;
	public int timeTillSpit = 0;
	
	public EntityLizard(World par1World) {
		super(par1World);

//		boundingBox.setBB(boundingBox.getBoundingBox(0.00,-0.5,-7,  0.05,0.5,1.0));
		this.setSize(0.9f, 0.5f);
		//boundingBox.setBounds(-20,-20,-5.0,0.05,0.5,2.5);		
		//this.setSize(1.4F, 2.9F);
		this.moveSpeed = 0.4f;
		this.texture = DefaultProps.mobDiretory + "Lizard.png";

		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, this.moveSpeed, 32.0F));
//		this.tasks.addTask(2, new EntityAIArrowAttack(this, this.moveSpeed, 3, 60));

//		this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, this.moveSpeed, true));
//		this.tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAIWander(this, this.moveSpeed, 120));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
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
		
		if (CustomEntityList.LIZARD.modData.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere() 
				&& worldObj.getClosestPlayerToEntity(this, 32) == null && this.worldObj.canBlockSeeTheSky(var1, var2, var3)){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.LIZARD.modData.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}
	
    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
	@Override
    protected boolean isValidLightLevel(World world, int xCoord, int yCoord, int zCoord) {
        int var1 = xCoord;
        int var2 = yCoord;
        int var3 = zCoord;
        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > this.rand.nextInt(32)) {
            return false;
        }
        else{
            int var4 = this.worldObj.getBlockLightValue(var1, var2, var3);

            if (this.worldObj.isThundering()) {
                int var5 = this.worldObj.skylightSubtracted;
                this.worldObj.skylightSubtracted = 10;
                var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
                this.worldObj.skylightSubtracted = var5;
            }

            return var4 <= this.rand.nextInt(8);
        }
    }
	
	public int getMaxHealth() {
		return 20;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate() {
		if(this.worldObj.isDaytime() && !this.worldObj.isRemote && counter % (10*20) == 0){
			heal(1);
		}
		float var3 = this.getBrightness(1.0F);
		if(var3 < 0.5){
			angerLevel = 120;
		}
		super.onLivingUpdate();

		if(timeTillSpit == 20){
			prepareToSpit = true;
		}

		//Check to see if Entity should Use Ability
		if (timeTillSpit == 0) {

			//Check if there is a player nearby
//			EntityPlayer tempTarget = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
			EntityLiving tempTarget = this.getAttackTarget();

			if(tempTarget != null && getDistanceToEntity(tempTarget) < 15){

				double var11 = tempTarget.posX - this.posX;
				double var13 = tempTarget.boundingBox.minY + (double)(tempTarget.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
				double var15 = tempTarget.posZ - this.posZ;

				if(!worldObj.isRemote){
					EntityLizardSpit var17 = new EntityLizardSpit(this.worldObj, this, var11, var13, var15);
					double var18 = 1.0D;
					Vec3 var20 = this.getLook(1.0F);
					var17.posX = this.posX + var20.xCoord * var18;
					var17.posY = this.posY + (double)(this.height / 2.0F);
					var17.posZ = this.posZ + var20.zCoord * var18;
					this.worldObj.spawnEntityInWorld(var17);
				}
				timeTillSpit = 80;
				prepareToSpit = false;

			}

		}else if(timeTillSpit == 0){
			timeTillSpit = 80;
			prepareToSpit = false;
		}

		counter++;
		//Reduce Cooldown on Spit Ability
		timeTillSpit = (int)Math.max(timeTillSpit-1, 0.0);

	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() { return "sounds.lizardhurt"; }

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		this.worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 1.0F, 1.0F);
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2) {
		int var3 = rand.nextInt(2 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.LIZARD.modData.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}

	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID,1,10), 1);
		}
		super.dropRareDrop(par1);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLiving var1) {}

}
