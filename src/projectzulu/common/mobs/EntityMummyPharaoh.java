package projectzulu.common.mobs;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemBlockList;
import cpw.mods.fml.common.Loader;



public class EntityMummyPharaoh extends EntityMob
{	
	Vec3 startingPosition;
	
	int stage = 1;
	boolean transition = false;

	public EntityModelRotation ebipedHead = new EntityModelRotation();
	public EntityModelRotation ebipedHeadwear = new EntityModelRotation();
	public EntityModelRotation ebipedBody = new EntityModelRotation();
	public EntityModelRotation ebipedRightArm = new EntityModelRotation();
	public EntityModelRotation ebipedLeftArm = new EntityModelRotation();
	public EntityModelRotation ebipedRightLeg = new EntityModelRotation();
	public EntityModelRotation ebipedLeftLeg = new EntityModelRotation();
	public EntityModelRotation ebipedEars = new EntityModelRotation();
	
	boolean firstUpdate = true;
	
	boolean spawnMummy = false;
	//Time To Wait after spawning to spawn another
	int spawnCooldown = 4*20;
	//Counter. When 0 its okay to spawn another
	int spawnTimer = 60;
	
	boolean shootFireball = false;
	int shootCooldown = 6*20;
	int shootTimer = 30;
	private static final ItemStack defaultHeldItem = ItemBlockList.ankh.isPresent() ? new ItemStack(ItemBlockList.ankh.get()) : new ItemStack(Item.swordSteel);

	//Debug Variables
	boolean printMessage = false;
	public boolean doNothing = false;
	int counter = 0;
	
	public EntityMummyPharaoh(World par1World) {
		super(par1World);
		//boundingBox.setBB(boundingBox.getBoundingBox(0.00,-0.5,-7,  0.05,0.5,1.0));
		//boundingBox.setBounds(-20,-20,-5.0,0.05,0.5,2.5);		
		this.setSize(0.6F, 1.4F);
		this.moveSpeed = 0.35f;

		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		//this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, this.moveSpeed, 32.0F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
		//this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, this.moveSpeed, 32.0F));
		//this.tasks.addTask(2, new EntityAIRangedAttack(this, this.moveSpeed, 3, 60));
		//this.tasks.addTask(2, new EntityAIKeepDistanceFromTarget(this, this.moveSpeed, 32.0F,worldObj));
		this.tasks.addTask(6, new EntityAIMoveTwardsRestriction(this, this.moveSpeed));
		this.tasks.addTask(7, new EntityAIWander(this, this.moveSpeed));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		//this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 32.0F, 0, true));
	}
	
    public EntityMummyPharaoh(World par1World, double parx, double pary, double parz){
		super(par1World);
    	this.setSize(0.6F, 1.4F);
		this.moveSpeed = 0.4f;
		this.texture = "/mods/Mummy_Pharaoh.png";
		
		setLocationAndAngles(parx, pary, parz, 1, 1);
		setPosition(parx, pary, parz);
		yOffset = 0.0f;
		
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
		//this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, this.moveSpeed, 32.0F));
		//this.tasks.addTask(2, new EntityAIRangedAttack(this, this.moveSpeed, 3, 60));
		//this.tasks.addTask(2, new EntityAIKeepDistanceFromTarget(this, this.moveSpeed, 32.0F,worldObj));
		this.tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAIWander(this, this.moveSpeed));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		//this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 32.0F, 0, true));

		

    }
    
    public ItemStack getHeldItem()
    {
    	return defaultHeldItem;
    	//return null;
    }
    
	public int getTotalArmorValue()
	{
		switch (stage) {
		case 1:
			return 4;
		case 2:

			return 6;
		case 3:

			return 8;
		case 4:

			return 10;

		default:
			return 2;
		}
	}
	
    public String getTexture(){
    	
		switch (stage) {
		case 1:
			return	"/mods/Mummy_Pharaoh.png";
		case 2:

			return	"/mods/Mummy_Pharaoh.png";
		case 3:

			return	"/mods/Mummy_Pharaoh.png";
		case 4:

			return	"/mods/Mummy_Pharaoh.png";

		default:
			return	"/mods/Mummy_Pharaoh.png";
		}

    }



	protected void entityInit()
	{
				
		super.entityInit();
	}



	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick()
	{

		super.updateAITick();
	}

	public int getMaxHealth()
	{
		return 200;
	}
	
	public int getCurrentHealth()
	{
		return health;
	}



	/**
	 * Decrements the entity's air supply when underwater
	 */
	protected int decreaseAirSupply(int par1)
	{
		return par1;
	}

	public boolean interact(EntityPlayer par1EntityPlayer){
			spawnMummy = !spawnMummy;

		return super.interact(par1EntityPlayer);
	}
	
	// When Entity Collides with player if it is Hidden, mark it as not
	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		//isHidden = false;
		printMessage = true;
		super.onCollideWithPlayer(par1EntityPlayer);
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		
		if(!worldObj.isRemote){
			BossHealthDisplayTicker.registerEntityMummyPharaoh(this);
			firstUpdate = false;
		}		
		
//		if(firstUpdate && !worldObj.isRemote && BossHealthDisplayTicker.isEntityNull()){
		
//		if(!worldObj.isRemote && BossHealthDisplayTicker.isEntityNull()){
//			BossHealthDisplayTicker.registerEntityMummyPharaoh(this);
//			firstUpdate = false;
//		}
		
		
//		if(!worldObj.isRemote){
//			AloeVeraTicker.healthToDisplay(this.health);
//		}


		if (startingPosition == null) {
			startingPosition = Vec3.createVectorHelper(posX,posY,posZ);
		}

		switch (stage) {
		case 1:
			//Stage One Update
			//Deault AI, just attacks like a zombie

			
			//Check Stage Ends
			if ( health<0.9*getMaxHealth()) {
//				ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Feel my Wrath!");
				teleportTo(startingPosition.xCoord, startingPosition.yCoord+2, startingPosition.zCoord);
				stage++;
			}
			
			break;
		case 2:
			//Stage Two Update
			
			if(shootFireball == true&& !worldObj.isRemote){
				shootFireballAtTarget();
				shootFireball = false;
			}
			
			//Check Stage Ends
			if ( health<0.7*getMaxHealth()) {
				teleportTo(startingPosition.xCoord, startingPosition.yCoord+2, startingPosition.zCoord);
//				ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Raise my followers!");
				stage++;
			}
			break;
		case 3:
			//Stage Four

			if(spawnMummy == true && !worldObj.isRemote){
				spawnMummy();
				spawnMummy = false;
			}
			
			if ( health<0.45*getMaxHealth()) {
				teleportTo(startingPosition.xCoord, startingPosition.yCoord+2, startingPosition.zCoord);
//				ModLoader.getMinecraftInstance().thePlayer.addChatMessage("How is this possible!");
				stage++;
			}
			break;
			
		case 4:
			//Stage Five
			//1 In 5 Chance it teleports Randomly when attacked

			if(spawnMummy == true && !worldObj.isRemote){
				spawnMummy();
				spawnMummy = false;
			}
			if(shootFireball == true&& !worldObj.isRemote){
				shootFireballAtTarget();
				shootFireball = false;
			}
			break;
		default:
			break;
		}
		
		//If Spawn Timer is 0, tell Entity its Allowed to Summon a mummy
		if(spawnTimer == 0){
			spawnMummy = true;
			spawnTimer = spawnCooldown;
		}
		
		if(shootTimer == 0){
			shootFireball = true;
			shootTimer = shootCooldown;
		}

		shootTimer = Math.max(shootTimer-1, 0);
		spawnTimer = Math.max(spawnTimer-1, 0);
		counter++; 
	} 

/**
 * Called when the entity is attacked.
 */
public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
{
	if (par1DamageSource.getEntity() instanceof EntityPlayer) {
		EntityPlayer tempPlayer = (EntityPlayer)par1DamageSource.getEntity();
		double distance = tempPlayer.getDistanceSqToEntity(this);
		
		if (distance > 10) {
			int holdRand = rand.nextInt(2);
			if (holdRand == 1) {
			teleportTo(tempPlayer.posX+1, worldObj.getHeightValue((int)tempPlayer.posX+1, (int)tempPlayer.posZ+1), tempPlayer.posZ+1);
			}
		}else{
			int holdRand = rand.nextInt(5);
			if (holdRand == 4) {
				teleportRandomly();
			}
		}
	}
	return super.attackEntityFrom(par1DamageSource, par2);
}


	public void spawnMummy(){
		//Get a Random Position Around Entity
		double desX;
		double desZ;

		int Min_Distance = 5;
		int Max_Distance = 9;

		//These "hold" are here because putting Rand.nextint without assigning to variable first
		//caused minecraft to get angry at me (/shrug)
		int hold = rand.nextInt(2);
		int hold2 = rand.nextInt(Max_Distance-Min_Distance)+Min_Distance;;
		if (hold != 0) {
			desX = Math.floor(this.posX) + hold2;
		}else{
			desX = Math.floor(this.posX) - hold2;
		}

		hold = rand.nextInt(2);
		hold2 = rand.nextInt(Max_Distance-Min_Distance)+Min_Distance;;
		if (hold != 0) {
			desZ = Math.floor(this.posZ) + hold2;
		}else{
			desZ = Math.floor(this.posZ) - hold2;
		}

		//Note this is not final Y height, this is just for ground level
		int desY = this.worldObj.getHeightValue((int)desX,(int)desZ);
		//If the block is not air
		if(worldObj.getBlockId((int)desX, (int)desY-2, (int)desZ) != 0){
			worldObj.setBlockWithNotify((int)desX, desY-0, (int)desZ, 0);
			worldObj.setBlockWithNotify((int)desX, desY-1, (int)desZ, 0);
		}
		//This sets where the monster will spawn on Y relative to Ground Level
		desY -= 1;

		//Adjust X and Z so that they are at Center of Block and Not Edge
		desX+=0.5;
		desZ+=0.5;

		EntityMummy var17 = new EntityMummy(this.worldObj,desX,desY,desZ);
		this.worldObj.spawnEntityInWorld(var17);
	}
	
	public void shootFireballAtTarget(){
		EntityPlayer targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 32.0D);
		if (targetedEntity != null) {
			int holdRand = rand.nextInt(10)-5;
			double sourcePositionX = this.posX+holdRand;
			double sourcePositionY = this.posY+20;
			holdRand = rand.nextInt(10)-5;
			double sourcePositionZ = this.posZ+holdRand;
			
	        double var11 = targetedEntity.posX - sourcePositionX;
	        double var13 = targetedEntity.boundingBox.minY + (double)(targetedEntity.height / 2.0F) - (sourcePositionY + (double)(this.height / 2.0F));
	        double var15 = targetedEntity.posZ - sourcePositionZ;
	        this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(var11, var15)) * 180.0F / (float)Math.PI;

	        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
	        EntityFireball var17 = new EntityLargeFireball(this.worldObj, this, var11, var13, var15);
	        double var18 = 1.0D;
	        Vec3 var20 = this.getLook(1.0F);
	        var17.posX = sourcePositionX + var20.xCoord * var18;
	        var17.posY = sourcePositionY + (double)(this.height / 2.0F) + 0.5D;
	        var17.posZ = sourcePositionZ + var20.zCoord * var18;
	        this.worldObj.spawnEntityInWorld(var17);			
		}
	}
	public void shootFireballAroundTarget(){
		EntityPlayer targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 32.0D);
		if (targetedEntity != null) {
			int holdRand = rand.nextInt(10)-5;
			double sourcePositionX = this.posX+holdRand;
			double sourcePositionY = this.posY+20;
			holdRand = rand.nextInt(10)-5;
			double sourcePositionZ = this.posZ+holdRand;
			
			double desX = targetedEntity.posX + rand.nextInt(10)-5;
			double desZ = targetedEntity.posZ + rand.nextInt(10)-5;
			int desY = this.worldObj.getHeightValue((int)desX,(int)desZ);

			double var11 = desX - sourcePositionX;
			double var13 = targetedEntity.boundingBox.minY + (double)(targetedEntity.height / 2.0F) - (sourcePositionY + (double)(this.height / 2.0F));
			double var15 = desZ - sourcePositionZ;
			this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(var11, var15)) * 180.0F / (float)Math.PI;

			this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)desX, (int)desY, (int)desZ, 0);
			EntityFireball var17 = new EntityLargeFireball(this.worldObj, this, var11, var13, var15);
			double var18 = 4.0D;
			Vec3 var20 = this.getLook(1.0F);
			var17.posX = sourcePositionX + var20.xCoord * var18;
			var17.posY = sourcePositionY + (double)(this.height / 2.0F) + 0.5D;
			var17.posZ = sourcePositionZ + var20.zCoord * var18;
			this.worldObj.spawnEntityInWorld(var17);
		}
	}
	
    /**
     * Teleport the Pharoah to a random nearby position
     */
    protected boolean teleportRandomly()
    {
        double var1 = this.posX + (this.rand.nextDouble() - 0.5D) * 24.0D;
        double var5 = this.posZ + (this.rand.nextDouble() - 0.5D) * 24.0D;
        double var3 = worldObj.getHeightValue((int)var1, (int)var5);
        return this.teleportTo(var1, var3, var5);
    }

    /**
     * Teleport the Pharoah
     */
    protected boolean teleportTo(double par1, double par3, double par5)
    {
        double var7 = this.posX;
        double var9 = this.posY;
        double var11 = this.posZ;
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        boolean var13 = false;
        int var14 = MathHelper.floor_double(this.posX);
        int var15 = MathHelper.floor_double(this.posY);
        int var16 = MathHelper.floor_double(this.posZ);
        int var18;

        if (this.worldObj.blockExists(var14, var15, var16))
        {
            boolean var17 = false;

            while (!var17 && var15 > 0)
            {
                var18 = this.worldObj.getBlockId(var14, var15 - 1, var16);

                if (var18 != 0 && Block.blocksList[var18].blockMaterial.blocksMovement())
                {
                    var17 = true;
                }
                else
                {
                    --this.posY;
                    --var15;
                }
            }

            if (var17)
            {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
                {
                    var13 = true;
                }
            }
        }

        if (!var13)
        {
            this.setPosition(var7, var9, var11);
            return false;
        }
        else
        {
            short var30 = 128;

            for (var18 = 0; var18 < var30; ++var18)
            {
                double var19 = (double)var18 / ((double)var30 - 1.0D);
                float var21 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float var22 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float var23 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double var24 = var7 + (this.posX - var7) * var19 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                double var26 = var9 + (this.posY - var9) * var19 + this.rand.nextDouble() * (double)this.height;
                double var28 = var11 + (this.posZ - var11) * var19 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                this.worldObj.spawnParticle("portal", var24, var26, var28, (double)var21, (double)var22, (double)var23);
            }

            this.worldObj.playSoundEffect(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
            this.worldObj.playSoundAtEntity(this, "mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }
    


	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "none";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "mob.irongolem.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "mob.irongolem.death";
	}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 1.0F, 1.0F);
	}

	public void onDeath(DamageSource damageSource){
		super.onDeath(damageSource);
	}
	
	/**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = this.rand.nextInt(3);
		int var4;

		if(Loader.isModLoaded(DefaultProps.BlocksModId) && ItemBlockList.jasper.isPresent()){
			this.dropItem(ItemBlockList.jasper.get().blockID, 1);			
		}

		var4 = 3 + this.rand.nextInt(3);

		for (int var5 = 0; var5 < var4; ++var5)
		{
			this.dropItem(Item.ingotIron.shiftedIndex, 1);
		}
	}
	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && ItemBlockList.ankh.isPresent()){
	        ItemStack var2 = new ItemStack(ItemBlockList.ankh.get());
	        this.entityDropItem(var2, 5.0F);
		}
	}
}
