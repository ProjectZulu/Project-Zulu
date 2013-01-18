package projectzulu.common.mobs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import projectzulu.common.api.CustomEntityList;

public class CopyOfEntityHorseBaseThatUsesZulu extends EntityRideableZulu{	
	
	private float speedForward = 0;
	private float ridingRotation = 0;
	public CopyOfEntityHorseBaseThatUsesZulu(World par1World)
	{
		super(par1World);

		this.myEntitySize = EnumEntitySize.SIZE_6;
		setSize(1.5f, 2.0f);
	}

	@Override
	protected void attackStrength(World par1World) {
		switch (par1World.difficultySetting) {
		case 0:
			this.attackStrength = 0; 
			break;
		case 1:
			this.attackStrength = 3; 
			break;
		case 2:
			this.attackStrength = 4; 
			break;
		case 3:
			this.attackStrength = 5; 
			break;
		default:
			this.attackStrength = 5;
			break;
		}
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere()
	{
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);

		if(CustomEntityList.mammoth.get().secondarySpawnRate - rand.nextInt(100) >= 0){
			return super.getCanSpawnHere();
		}else{
			return false;
		}
	}


//	@Override
//	protected void entityInit(){
//		super.entityInit();
//		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
//	}
	
	@Override
	public int getMaxHealth(){return 20;}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	@Override
	public int getTotalArmorValue(){return 0;}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound(){ return "sounds.horse"; }
	
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound(){ return "sounds.horsehurt"; }
	
	/**
     * Plays step sound at given x, y, z for the entity
     */
//	@Override
//	protected void playStepSound(int par1, int par2, int par3, int par4){ this.worldObj.playSoundAtEntity(this, "sounds.horseplop", 0.15F, 1.0F); }
		
	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound()
	{
		//return "mob.creeperdeath";
		return null;
	}

//	@Override
//	public void knockBack(Entity par1Entity, int par2, double par3, double par5)
//	{
//		this.isAirBorne = true;
//		float var7 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
//		float var8 = 0.4F;
//		this.motionX /= 2.0D;
//		this.motionY /= 2.0D;
//		this.motionZ /= 2.0D;
//		this.motionX -= par3 / (double)var7 * (double)var8*0.2;
//		this.motionY += (double)var8;
//		this.motionZ -= par5 / (double)var7 * (double)var8*0.2;
//
//		if (this.motionY > 0.1000000059604645D)
//		{
//			this.motionY = 0.1000000059604645D;
//		}
//	}


//	/**
//	 * (abstract) Protected helper method to write subclass entity data to NBT.
//	 */
//	@Override
//	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound){
//		super.writeEntityToNBT(par1NBTTagCompound);
//		par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
//	}
//
//	/**
//	 * (abstract) Protected helper method to read subclass entity data from NBT.
//	 */
//	@Override
//	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound){
//		super.readEntityFromNBT(par1NBTTagCompound);
//		this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
//	}

	@Override
	public void onUpdate(){
		super.onUpdate();
		
		/* Riding Controls */
		if(this.riddenByEntity != null && riddenByEntity instanceof EntityPlayer && !worldObj.isRemote){
			EntityPlayer riddingPlayer = (EntityPlayer)this.riddenByEntity;
			//				System.out.println(riddingPlayer.isRiding());
			riddingPlayer.posY += 1;
			float maxSpeedForward = 5.0f;
			
			/* If Holding Apprpriate Item, Turn Horse*/
			if(riddingPlayer.inventory.getCurrentItem() != null){
				
				/* Only Perform Turning and Speeding When Player has Riding Item in Hand */
				if(riddingPlayer.inventory.getCurrentItem().getItem().itemID == Item.stick.itemID){
					/* Turn Entity To Face Rider Direction */
					float var3 = MathHelper.wrapAngleTo180_float(riddingPlayer.rotationYaw - this.ridingRotation) * 0.5F;
					if (var3 > 5.0F){
						var3 = 5.0F;
					}
					if (var3 < -5.0F){
						var3 = -5.0F;
					}
					this.ridingRotation = MathHelper.wrapAngleTo180_float(this.ridingRotation + var3);
					
					/* Speed Up */
					if(speedForward < maxSpeedForward){
						speedForward += (maxSpeedForward - speedForward) * 0.005F;
					}
					if(speedForward > maxSpeedForward){
						speedForward = maxSpeedForward;
					}
				}
				
				/* Move Forward with Speed */
				moveEntityWithHeading(0.0F, speedForward);
			}
			
			

			this.rotationYaw = this.ridingRotation;
			this.rotationPitch = 0;
		}else if(speedForward > 0){
			speedForward = 0;
		}
		if(this.riddenByEntity != null && riddenByEntity instanceof EntityPlayer){
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				isJumping = true;
			}
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
//		return false;
		if( this.riddenByEntity != null && 
				(par1DamageSource.getEntity() != null && par1DamageSource.getEntity().equals(this.riddenByEntity)) ||
				(par1DamageSource.getSourceOfDamage() != null && par1DamageSource.getSourceOfDamage().equals(this.riddenByEntity)) ){
			return false;
		}else{
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}
	
	/**
	 * Determine whether the Entity Should Call For Help
	 */
	@Override
	public boolean shouldCallForHelp(Entity attackingEntity){
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	protected void UpdateTargetBasedOnState() {

		super.UpdateTargetBasedOnState();

		/* If Targetting Ridding Player, set target to null*/
		if( riddenByEntity!= null && targetedEntity!= null && targetedEntity instanceof EntityPlayer 
				&& ((EntityPlayer)riddenByEntity).username.equals( ((EntityPlayer)targetedEntity).username) ){
			targetedEntity = null;
		}
	};

	@Override
	public void moveEntity(double par1, double par3, double par5) {
		if(riddenByEntity != null) {
			if(isCollidedHorizontally){
				isJumping = true;
			}else{
				isJumping = false;
			}
			
			if(this.isInWater()) {
				this.motionY += 0.005;
			}
			
			super.moveEntity(motionX, motionY, motionZ);
		}else {
			super.moveEntity(par1, par3, par5);
		}
	}

	/**
	 * Time remaining during which the Animal is sped up and flees.
	 */
	protected void updateWanderPath()
	{
		this.worldObj.theProfiler.startSection("stroll");
		boolean var1 = false;
		int var2 = -1;
		int var3 = -1;
		int var4 = -1;
		float var5 = -99999.0F;

		for (int var6 = 0; var6 < 10; ++var6)
		{
			int var7 = MathHelper.floor_double(this.posX + (double)this.rand.nextInt(17) - 8.0D);
			int var8 = MathHelper.floor_double(this.posY + (double)this.rand.nextInt(7) - 3.0D);
			int var9 = MathHelper.floor_double(this.posZ + (double)this.rand.nextInt(17) - 8.0D);
			float var10 = this.getBlockPathWeight(var7, var8, var9);

			if (var10 > var5)
			{
				var5 = var10;
				var2 = var7;
				var3 = var8;
				var4 = var9;
				var1 = true;
			}
		}

		if (var1)
		{
			this.pathToEntity = this.worldObj.getEntityPathToXYZ(this, var2, var3, var4, 10.0F, true, false, false, true);
		}

		this.worldObj.theProfiler.endSection();
	}

	/**
	 * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
	 * Args: x, y, z
	 */
	public float getBlockPathWeight(int par1, int par2, int par3)
	{
		/*Calculate Centroid Of Nearby Mammoths, as Well as the Herd*/

		List<Vec3> centroids = new ArrayList<Vec3>();

		AxisAlignedBB var15 = this.boundingBox.copy();
		var15 = var15.expand(100, 60, 100);
		List nearbyEntities = this.worldObj.getEntitiesWithinAABB(CopyOfEntityHorseBaseThatUsesZulu.class, var15);
		Vec3 herdCentroid = Vec3.createVectorHelper(0, 0, 0);
		int i = 0;
		if (nearbyEntities != null && !nearbyEntities.isEmpty()){
			Iterator var10 = nearbyEntities.iterator();
			while (var10.hasNext()){
				Entity var4 = (Entity)var10.next();

				if (var4 instanceof CopyOfEntityHorseBaseThatUsesZulu){
					centroids.add(i, Vec3.createVectorHelper(var4.posX, var4.posY, var4.posZ) );
					herdCentroid = Vec3.createVectorHelper(herdCentroid.xCoord + var4.posX, herdCentroid.yCoord + var4.posY, herdCentroid.zCoord + var4.posZ);
					i++;
				}
			}
		}
		herdCentroid = Vec3.createVectorHelper(herdCentroid.xCoord/i, herdCentroid.yCoord/i, herdCentroid.zCoord/i);
		return 0.0F;
	}

	@Override
	public float getAttackDistance() {
		return isChild() ? 0.5f : 2.0f;
	}

	//	Called when player interacts with mob, eg get milk, saddle
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer){

		if(getSaddled() && this.riddenByEntity != null && this.riddenByEntity.equals(par1EntityPlayer) && 
				par1EntityPlayer.inventory.getCurrentItem() != null && (par1EntityPlayer.inventory.getCurrentItem().getItem() instanceof ItemBow) ){
			return true;
		}
		
		if (super.interact(par1EntityPlayer))
		{
			return true;
		}
		else if ( getSaddled() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer)){
			par1EntityPlayer.mountEntity(this);
			this.pathToEntity = null;
			return true;
		}else if(!getSaddled() && par1EntityPlayer.inventory.getCurrentItem() != null 
				&& par1EntityPlayer.inventory.getCurrentItem().itemID == Item.saddle.itemID  ){
			setSaddled(true);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Returns true if the pig is saddled.
	 */
//	public boolean getSaddled()
//	{
//		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
//	}

//	/**
//	 * Set or remove the saddle of the pig.
//	 */
//	@Override
//	public void setSaddled(boolean par1)
//	{
//		if (par1)
//		{
//			this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
//		}
//		else
//		{
//			this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
//		}
//	}

	@Override
	public boolean isValidFollowItem(ItemStack itemStack) {
		if(itemStack != null && (itemStack.getItem().itemID == Item.appleRed.itemID 
				|| itemStack.getItem().itemID == Item.carrot.itemID )){
			return true;
		}else{
			return super.isValidFollowItem(itemStack);
		}
	}

	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		return isValidFollowItem(itemStack);
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */	
	protected int getDropItemId()
	{
		if(rand.nextBoolean()){
			return Item.rottenFlesh.itemID;
		}else{
			return Item.beefRaw.itemID;
		}
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = this.rand.nextInt(3 + par2) + 1;
		int var4;

//		if(Loader.isModLoaded(DefaultProps.BlocksModId)){
//			this.dropItem(mod_ProjectZulu.furPeltID+256, 1);
//			entityDropItem(new ItemStack(mod_ProjectZulu.genericCraftingItems1ID+256,1,1), 2);
//		}

		for (var4 = 0; var4 < var3; ++var4)
		{
			if(var4 == 2){
				this.dropItem(Item.leather.itemID,1);
			}else{
				this.dropItem(Item.beefRaw.itemID,1);
			}
		}
	}

	@Override
	protected void dropRareDrop(int par1) {
		super.dropRareDrop(par1);
	}

}