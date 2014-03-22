package projectzulu.common.mobs.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAerial extends EntityLiving {
    
	protected ChunkCoordinates targetPosition;
	/** Tries to Assign targetPosition 
	 * Checks if Valid first using isTargetPositionValid 
	 * Returns False if invald 
	 */
	public boolean setTargetPosition(ChunkCoordinates targetPosition){
		if(isTargetPositionValid(targetPosition)){
			this.targetPosition = targetPosition;
			return true;
		}
		return false;
	}
	protected int maxFlightHeight = 20;
	public int getMaxFlightHeight(){ return maxFlightHeight; }
	public EntityAerial(World par1World) {
		super(par1World);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		
		/* Handle whether Entity is Flying or not*/
		if( defaultGrounded() ){
			this.dataWatcher.addObject(17, Byte.valueOf((byte)1));
		}else{
			this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
		}

	}
	
	public boolean defaultGrounded() {
		return true;
	}
	
	public boolean isEntityGrounded() {
		return this.dataWatcher.getWatchableObjectByte(17) != 0;
	}
	protected void setEntityGrounded(Boolean par1) {
		if(par1){
			this.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
		}else {
			this.dataWatcher.updateObject(17, Byte.valueOf((byte)0));
		}
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setByte("Is Grounded", this.dataWatcher.getWatchableObjectByte(17));
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readEntityFromNBT(par1NBTTagCompound);
		if(par1NBTTagCompound.hasKey("Is Grounded")){
			this.dataWatcher.updateObject(17, par1NBTTagCompound.getByte("Is Grounded"));
		}
	}
	
	@Override
	protected void updateAITasks() {
		if(!isEntityGrounded() && targetPosition != null){
			/* Get The Direction I want to travel in */
			double var1 = (double)this.targetPosition.posX + 0.5D - this.posX;
			double var3 = (double)this.targetPosition.posY + 0.1D - this.posY;
			double var5 = (double)this.targetPosition.posZ + 0.5D - this.posZ;

			/* Change Velocity */
			/* Normalize the Direction I want to travel in, then add and scale it to Motion */
			this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.10000000149011612D*0.3D;
			this.motionY += (Math.signum(var3) * 0.699999988079071D - this.motionY) * 0.10000000149011612D*0.3D;
			this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.10000000149011612D*0.3D;
			float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
			this.moveForward = 0.5F;
			this.rotationYaw += var8;
		}
		super.updateAITasks();
	}
	
	protected boolean shouldBeFlying() {
		return false;
	}
	
	public boolean isTargetPositionValid(){
		return isTargetPositionValid(this.targetPosition);
	}
	
	public boolean isTargetPositionValid(ChunkCoordinates targetPosition){
		/* Invalid if Water, is below height = 3 (superflat), and if its null */
		if (targetPosition != null && (!this.worldObj.isAirBlock(targetPosition.posX, targetPosition.posY, targetPosition.posZ) || targetPosition.posY < 3 
				|| this.worldObj.getBlock(targetPosition.posX, targetPosition.posY, targetPosition.posZ).getMaterial().equals(Material.water))){
//			targetPosition = null;
			return false;
		}
		return true;
	}
	
	/* Checks if Entity is at the target position, return true if TargetPosition is null*/
	public boolean atTargetPosition(){
		if(targetPosition != null){
		   return getDistance(targetPosition.posX, targetPosition.posY, targetPosition.posZ) < 2;
		}
		return true;
	}
	
	@Override
	public void moveEntityWithHeading(float par1, float par2) {
		if(isEntityGrounded()){
    		super.moveEntityWithHeading(par1, par2);
    		return;
    	}
		
        if (this.isInWater())
        {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.handleLavaMovement())
        {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else
        {
            float var3 = 0.91F;

            if (this.onGround)
            {
                var3 = 0.54600006F;
                Block block = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
                if (block != null)
                {
                    var3 = block.slipperiness * 0.91F;
                }
            }

            float var8 = 0.16277136F / (var3 * var3 * var3);
            this.moveFlying(par1, par2, this.onGround ? 0.1F * var8 : 0.02F);
            var3 = 0.91F;

            if (this.onGround)
            {
                var3 = 0.54600006F;
                Block block = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
                if (block != null)
                {
                    var3 = block.slipperiness * 0.91F;
                }
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)var3;
            this.motionY *= (double)var3;
            this.motionZ *= (double)var3;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double var10 = this.posX - this.prevPosX;
        double var9 = this.posZ - this.prevPosZ;
        float var7 = MathHelper.sqrt_double(var10 * var10 + var9 * var9) * 4.0F;

        if (var7 > 1.0F)
        {
            var7 = 1.0F;
        }

        this.limbSwingAmount += (var7 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwing;
	}
	
	
	/**
	 *  Method Designed for Flying Entities to adjust the Direction They are facing to the direction they are moving
	 */
	protected void adjustRotationToWaypoint() {		
		double var8 = targetPosition.posX - this.posX;
		double var10 = targetPosition.posZ - this.posZ;
		float var14 = (float)(Math.atan2(var10, var8) * 180.0D / Math.PI);// - 90.0F;
		float var15 = MathHelper.wrapAngleTo180_float(var14 - this.rotationYaw);

		if (var15 > 30.0F){
			var15 = 30.0F;
		}

		if (var15 < -30.0F){
			var15 = -30.0F;
		}
		this.renderYawOffset = this.rotationYaw += var15;      
	}
}
