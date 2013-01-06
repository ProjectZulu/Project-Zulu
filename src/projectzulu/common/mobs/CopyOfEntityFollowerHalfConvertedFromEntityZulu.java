//package projectzulu.common.mobs;
//
//import net.minecraft.block.Block;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.MathHelper;
//import net.minecraft.util.Vec3;
//import net.minecraft.world.World;
//import projectzulu.common.core.DefaultProps;
//
//public class CopyOfEntityFollowerHalfConvertedFromEntityZulu extends EntityLiving{
//
//	EntityCentipede entityFollowing;
//	int followerIndex = -1;
//	
//	private Vec3 targetPosition = Vec3.createVectorHelper(0, 0, 0);
//	public void setTargetPosition(Vec3 targetPosition){	this.targetPosition = targetPosition;}
//
//	float targetRotation;
//	public void setTargetRotation(float targetRotation){ this.targetRotation = wrapAngleTo360(targetRotation); }
//	
//	boolean shouldBeDying = false;
//	
//	/*Client Side Variable that Controls if it should Sync its Contents with Master*/
//	boolean isClientSetup = false;
//	
//	public CopyOfEntityFollowerHalfConvertedFromEntityZulu(World par1World){
//		super(par1World);
//		moveSpeed = 0.0f;
//		noClip = true;
//		setSize(0.65f, 0.5f);
//	}
//	public CopyOfEntityFollowerHalfConvertedFromEntityZulu(World par1World, EntityCentipede entityFollowing) {
//		this(par1World);
//		this.entityFollowing = entityFollowing;
//		targetPosition = Vec3.createVectorHelper(entityFollowing.posX, entityFollowing.posY, entityFollowing.posZ);
//	}
//
//	public CopyOfEntityFollowerHalfConvertedFromEntityZulu(World par1World, double parx, double pary, double parz, EntityCentipede entityFollowing, int followerIndex) {
//		this(par1World, entityFollowing);
//		setLocationAndAngles(parx, pary, parz, 1, 1);
//		setPosition(parx, pary, parz);
//		this.followerIndex = followerIndex;
//	}
//	
//	@Override
//	protected void updateAITick() {
//		super.updateAITick();
//
//		/* ticksExisted > some large number, 
//		 * In theory entityFollowing should search for this Entity and add a reference of itself,
//		 * so it should Stay in the world for a little while to allow time to search
//		 * 
//		 * I don't think I want to take this approach, as more than one serpent may exist. 
//		 * Master Entity Creates new FOllowers on spawning/reloading
//		 * 
//		 * Note that the Client Side Entity will exists for several ticks before the masterEntity is Synced from server,
//		 * So we only Check if should SetDead on Server
//		 */
//		if(!worldObj.isRemote && entityFollowing == null && ticksExisted > 0){
//			this.setDead();
//		}
//		moveToTargetPosition(0.5f);
//		adjustRotationToTarget(10.0f);
//	}
//	
//	@Override
//	public void onLivingUpdate() {
//		super.onLivingUpdate();
//		
//		/* If Master Head is dead, Entity should be dead */
//		if(entityFollowing != null && (entityFollowing.isDead || entityFollowing.deathTime > 0 || shouldBeDying)){
//			onDeathUpdate();
//		}
//		
////		/* Sync Server Entities List with Client Entity List */
////		if(!worldObj.isRemote && entityFollowing != null && counter % 40 == 0){
////            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
////            DataOutputStream data = new DataOutputStream(bytes);            
////            /* Write PacketID into Packet */
////            try {
////            	data.writeInt(3);
////            } catch (Exception e) {
////            	e.printStackTrace();
////            }
////            /* Write Temperature Into Packet*/
////            try {
////            	data.writeInt(this.entityId);
////    			data.writeInt(entityFollowing.entityId);
////    			data.writeInt(followerIndex);
////            } catch (Exception e) {
////            	e.printStackTrace();
////            }
////            
////			Packet250CustomPayload packet = new Packet250CustomPayload();
////			packet.channel = "Channel_Zulu"; // CHANNEL MAX 16 CHARS
////            packet.data = bytes.toByteArray();
////            packet.length = packet.data.length;
////			PacketDispatcher.sendPacketToAllPlayers(packet);
////		}
//	}
//	
//	public void adjustRotationToTarget(float angularVelocity){
//		
//		setRotation(targetRotation, rotationPitch);
//		rotationYawHead = rotationYaw;		
//	}
//	
//	public void moveToTargetPosition(float velocity){
//		moveEntity(targetPosition.xCoord - posX, targetPosition.yCoord - posY, targetPosition.zCoord - posZ);
//	}
//	
//	@Override
//	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
//		if(entityFollowing != null){
//			if(entityFollowing.attackEntityFromChild(this, par1DamageSource, par2)){
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public boolean hurtFromMaster(DamageSource par1DamageSource, int par2){
//		return super.attackEntityFrom(par1DamageSource, par2);
//	}
//		
//	@Override
//	protected boolean isAIEnabled() {
//		return true;
//	}
//	
//	/**
//	 * Returns False so that Entity doesn't Suffocate while in a wall
//	 */
//	@Override
//	public boolean isEntityInsideOpaqueBlock() {
//		return false;
//	}
//	
//	@Override
//	public boolean canBePushed() {
//		return false;
//	}
//
//	@Override
//	public String getTexture() {
//		texture = DefaultProps.mobDiretory + "serpent.png";
//		return this.texture;
//	}
//
//	@Override
//	public int getMaxHealth() {
//		return 20;
//	}
//	@Override
//	public int getTotalArmorValue() {
//		return 2;
//	}
//
//	@Override
//	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
//		super.readEntityFromNBT(par1nbtTagCompound);
//
//	}
//	
//	@Override
//	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
//		super.writeEntityToNBT(par1nbtTagCompound);
//	}
//	
//	/* Gets Master Entity from World and sets it in the entityFollowing container */
//	public void linkMasterWithFollower(int masterEntityID, int followerIndex){
//		Entity master = worldObj.getEntityByID(masterEntityID);
//		if(master != null && master instanceof EntityCentipede && isClientSetup == false){
//			EntityCentipede masterSerpent = (EntityCentipede)master;
//			this.followerIndex = followerIndex;
//			this.entityFollowing = masterSerpent;
//			targetPosition = Vec3.createVectorHelper(masterSerpent.posX, masterSerpent.posY, masterSerpent.posZ);
//			masterSerpent.linkFollowerWithMaster(this, followerIndex);
//			isClientSetup = true;
//		}
//	}
//
//	@Override
//	protected void fall(float par1) {}
//
////	@Override
////	public void moveEntity(double par1, double par3, double par5) {}
//	
//	@Override
//	public void moveEntityWithHeading(float par1, float par2){
//		if (this.isInWater())
//		{
//			this.moveFlying(par1, par2, 0.02F);
//			this.moveEntity(this.motionX, this.motionY, this.motionZ);
//			this.motionX *= 0.800000011920929D;
//			this.motionY *= 0.800000011920929D;
//			this.motionZ *= 0.800000011920929D;
//		}
//		else if (this.handleLavaMovement())
//		{
//			this.moveFlying(par1, par2, 0.02F);
//			this.moveEntity(this.motionX, this.motionY, this.motionZ);
//			this.motionX *= 0.5D;
//			this.motionY *= 0.5D;
//			this.motionZ *= 0.5D;
//		}
//		else
//		{
//			float var3 = 0.91F;
//
//			if (this.onGround)
//			{
//				var3 = 0.54600006F;
//				int var4 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
//
//				if (var4 > 0)
//				{
//					var3 = Block.blocksList[var4].slipperiness * 0.91F;
//				}
//			}
//
//			float var8 = 0.16277136F / (var3 * var3 * var3);
//			this.moveFlying(par1, par2, this.onGround ? 0.1F * var8 : 0.02F);
//			var3 = 0.91F;
//
//			if (this.onGround)
//			{
//				var3 = 0.54600006F;
//				int var5 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
//
//				if (var5 > 0)
//				{
//					var3 = Block.blocksList[var5].slipperiness * 0.91F;
//				}
//			}
//
//			this.moveEntity(this.motionX, this.motionY, this.motionZ);
//			this.motionX *= (double)var3;
//			this.motionY *= (double)var3;
//			this.motionZ *= (double)var3;
//		}
//
//		this.prevLegYaw = this.legYaw;
//		double var10 = this.posX - this.prevPosX;
//		double var9 = this.posZ - this.prevPosZ;
//		float var7 = MathHelper.sqrt_double(var10 * var10 + var9 * var9) * 4.0F;
//
//		if (var7 > 1.0F)
//		{
//			var7 = 1.0F;
//		}
//
//		this.legYaw += (var7 - this.legYaw) * 0.4F;
//		this.legSwing += this.legYaw;
//	}
//	
//	private float wrapAngleTo360(float angle){
//		while(angle > 360){
//			angle -= 360;
//		}
//		while(angle < 0){
//			angle += 360;
//		}
//		return angle;
//	}
//
//}
