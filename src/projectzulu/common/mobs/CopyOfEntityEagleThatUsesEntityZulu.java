package projectzulu.common.mobs;

import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;

public class CopyOfEntityEagleThatUsesEntityZulu extends EntityZulu{

	public CopyOfEntityEagleThatUsesEntityZulu(World par1World){
		super(par1World);
		this.setSize(1.0f, 1.4f);
		this.moveSpeed = 0.22f;
		this.maxFlightHeight = 30;
	}

	@Override
	public boolean defaultGrounded(){
		return false;
	}

	@Override
	protected boolean canDespawn(){
		return true;
	}
	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float par1){}

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
	 * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
	 */
	protected void updateFallState(double par1, boolean par3) {}


	@Override
	public String getTexture(){
		this.texture = "/mods/Eagle.png";
		return super.getTexture();
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere(){

		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		if(CustomEntityList.eagle.get().secondarySpawnRate - rand.nextInt(100) >= 0){
			return  worldObj.getClosestPlayerToEntity(this, 32) == null &&
					this.worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1 && 
					this.worldObj.canBlockSeeTheSky(var1, var2, var3) && 
					super.getCanSpawnHere();
		}else{
			return false;
		}

	}

	public int getMaxHealth()
	{
		return 20;
	}

	@Override
	public void onUpdate() {
		
		super.onUpdate();
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound(){
		return "sounds.eagleliving";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound(){
		return "sounds.eaglehurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound(){
		return null;	
	}

	@Override
	public float getAttackDistance() {
		return 2.0f;
	}
	
	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2){
		int var3 = this.rand.nextInt(2) + this.rand.nextInt(1 + par2);

		for (int var4 = 0; var4 < var3; ++var4)
		{
			this.dropItem(Item.feather.itemID, 1);
		}

		if (this.isBurning())
		{
			this.dropItem(Item.chickenCooked.itemID, 1);
		}
		else
		{
			this.dropItem(Item.chickenRaw.itemID, 1);
		}

	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	public int getMaxSpawnedInChunk(){
		return 3;
	}
	
}
