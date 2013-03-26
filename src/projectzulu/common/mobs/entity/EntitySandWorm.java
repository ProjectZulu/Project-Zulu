package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIMoveTowardsRestriction;
import projectzulu.common.mobs.entityai.EntityAIMoveTowardsTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIStayStill;


public class EntitySandWorm extends EntityGenericAnimal implements IMob{
	
	public EntitySandWorm(World par1World) {
		super(par1World);
		setSize(1.5f, 1.0f);
		
		this.moveSpeed = 0.22f;
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(2, new EntityAIStayStill(this, EntityStates.idle));

		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
		this.tasks.addTask(4, new EntityAIMoveTowardsTarget(this, this.moveSpeed, 32.0F));
		
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, this.moveSpeed));
		
//		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityVillager.class, 16.0F, 0, true));
	}
	
	/** As if 1.4.2 Represents what was Attack Strength in 1.3.2 */
	@Override
	public int getAttackStrength(World world) {
		switch (world.difficultySetting) {
		case 0:
			return 3; 
		case 1:
			return 3; 
		case 2:
			return 4; 
		case 3:
			return 6; 
		default:
			return 3;
		}
    }

	@Override
	public String getTexture() {
		if (getEntityState() == EntityStates.idle) {
			this.texture = DefaultProps.mobDiretory + "sandworm_hidden.png";
		}else{
			this.texture = DefaultProps.mobDiretory + "sandworm.png";
		}
		return super.getTexture();
	}

	@Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

	@Override
    public int getMaxHealth(){
		return 20;
	}
	
	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		setAngerLevel(400);
		super.onCollideWithPlayer(par1EntityPlayer);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		setAngerLevel(400);
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	@Override
    public void onLivingUpdate(){
		if(this.worldObj.isDaytime() && !this.worldObj.isRemote && ticksExisted % (10*20) == 0){
			heal(1);
		}
				
		super.onLivingUpdate();

		if(getEntityState() == EntityStates.idle && worldObj.getClosestPlayerToEntity(this, 4D) != null){
			setAngerLevel(100);
		}
	}
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
    protected String getLivingSound(){
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
    protected String getHurtSound(){
		return null;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
    protected String getDeathSound(){
		return null;
	}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	@Override
    protected void playStepSound(int par1, int par2, int par3, int par4){
		this.worldObj.playSoundAtEntity(this, "sand", 1.0F, 1.0F);
	}
}
