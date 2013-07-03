package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFlyingWander;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;

public class EntityHornBill extends EntityGenericAnimal{

	public EntityHornBill(World par1World){
		super(par1World);
		this.setSize(0.8f, 1.2f);
		this.moveSpeed = 0.22f;
		this.maxFlightHeight = 20;
		this.getNavigator().setAvoidsWater(true);		
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
		this.tasks.addTask(6, new EntityAIFlyingWander(this, this.moveSpeed));
		
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));		
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
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

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
	 * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
	 */
	@Override
    protected void updateFallState(double par1, boolean par3) {}
	
	@Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

	@Override
    public int getMaxHealth() {
		return 20;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
    protected String getLivingSound(){
		return "sounds.hornbillliving";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
    protected String getHurtSound(){
		return "sounds.hornbillhurt";
	}
}
