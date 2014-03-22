package projectzulu.common.mobs.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.PZPacket;
import projectzulu.common.mobs.packets.PacketFollowerMasterData;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

/**
 * Reminder: This is technically a 'Flying' Entity
 */
public class EntityFollower extends EntityLiving {

    EntityMaster masterEntity;
    int followerIndex = -1;

    public int getFollowerIndex() {
        return followerIndex;
    }

    private Vec3 targetPosition = Vec3.createVectorHelper(0, 0, 0);

    public void setTargetPosition(Vec3 targetPosition) {
        this.targetPosition = targetPosition;
    }

    float targetRotation;

    public void setTargetRotation(float targetRotation) {
        this.targetRotation = wrapAngleTo360(targetRotation);
    }

    boolean shouldBeDying = false;

    /* Client Side Variable that Controls if it should Sync its Contents with Client Master */
    boolean isClientSetup = false;

    public EntityFollower(World par1World) {
        super(par1World);
        noClip = true;
        setSize(0.65f, 0.5f);
    }

    public EntityFollower(World par1World, double parx, double pary, double parz, EntityMaster masterEntity,
            int followerIndex) {
        this(par1World);
        this.masterEntity = masterEntity;
        targetPosition = Vec3.createVectorHelper(masterEntity.posX, masterEntity.posY, masterEntity.posZ);
        setLocationAndAngles(parx, pary, parz, 1, 1);
        setPosition(parx, pary, parz);
        this.followerIndex = followerIndex;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0);
    }

    @Override
    public int getTotalArmorValue() {
        return 2;
    }

    /**
     * Returns False so that Entity doesn't Suffocate while in a wall
     */
    @Override
    public boolean isEntityInsideOpaqueBlock() {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void updateAITick() {
        super.updateAITick();

        /*
         * Note that the Client Side Entity will exists for several ticks before the masterEntity is Synced from server,
         * So we only Check if should SetDead on Server
         * 
         * Also, onReload, the Server will re-create its Children, this the old Children must dye.
         */
        if (masterEntity == null && ticksExisted > 0) {
            this.kill();
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        /* If Master Head is dead, Entity should be dead */
        if (masterEntity != null && (masterEntity.isDead || masterEntity.deathTime > 0 || shouldBeDying)) {
            onDeathUpdate();
        }

        /*
         * Send Packet Server -> Client with EntityData to Connect Child+Master : Packet calls linkMasterWithFollower
         * from Child
         */
        if (ticksExisted % 40 == 0 && !worldObj.isRemote && masterEntity != null) {
            PZPacket packet = new PacketFollowerMasterData().setPacketData(getEntityId(), masterEntity.getEntityId(), followerIndex);
            ProjectZulu_Core.getPipeline().sendToAllAround(packet, new TargetPoint(dimension, posX, posY, posZ, 60));
        }

        moveToTargetPosition(0.5f);
        adjustRotationToTarget(10.0f);
    }

    /* Helper Method Responsible for Moving Follower to its Target Position */
    public void moveToTargetPosition(float velocity) {
        moveEntity(targetPosition.xCoord - posX, targetPosition.yCoord - posY, targetPosition.zCoord - posZ);
    }

    /* Helper Method Responsible for rotating Follower to its Target Rotation */
    public void adjustRotationToTarget(float angularVelocity) {
        setRotation(targetRotation, rotationPitch);
        rotationYawHead = rotationYaw;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (masterEntity != null) {
            if (masterEntity.attackEntityFromChild(this, par1DamageSource, par2)) {
                return true;
            }
        }
        return false;
    }

    public boolean hurtChildFromMaster(DamageSource par1DamageSource, float par2) {
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    /* Gets Master Entity from World and sets it in the masterEntity container */
    public void linkMasterWithFollower(int masterEntityID, int followerIndex) {

        Entity master = worldObj.getEntityByID(masterEntityID);
        if (master != null && master instanceof EntityMaster && isClientSetup == false) {
            EntityMaster masterEntity = (EntityMaster) master;
            this.followerIndex = followerIndex;
            this.masterEntity = masterEntity;
            targetPosition = Vec3.createVectorHelper(masterEntity.posX, masterEntity.posY, masterEntity.posZ);
            masterEntity.linkFollowerWithMaster(this, followerIndex);
            isClientSetup = true;
        }
    }

    @Override
    protected void fall(float par1) {
    }

    @Override
    public void moveEntityWithHeading(float par1, float par2) {
        if (this.isInWater()) {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        } else if (this.handleLavaMovement()) {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        } else {
            float var3 = 0.91F;

            if (this.onGround) {
                var3 = 0.54600006F;
                Block var4 = this.worldObj.getBlock(MathHelper.floor_double(this.posX),
                        MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

                if (var4 != null) {
                    var3 = var4.slipperiness * 0.91F;
                }
            }

            float var8 = 0.16277136F / (var3 * var3 * var3);
            this.moveFlying(par1, par2, this.onGround ? 0.1F * var8 : 0.02F);
            var3 = 0.91F;

            if (this.onGround) {
                var3 = 0.54600006F;
                Block var5 = this.worldObj.getBlock(MathHelper.floor_double(this.posX),
                        MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

                if (var5 != null) {
                    var3 = var5.slipperiness * 0.91F;
                }
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double) var3;
            this.motionY *= (double) var3;
            this.motionZ *= (double) var3;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double var10 = this.posX - this.prevPosX;
        double var9 = this.posZ - this.prevPosZ;
        float var7 = MathHelper.sqrt_double(var10 * var10 + var9 * var9) * 4.0F;

        if (var7 > 1.0F) {
            var7 = 1.0F;
        }

        this.limbSwingAmount += (var7 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    private float wrapAngleTo360(float angle) {
        while (angle > 360) {
            angle -= 360;
        }
        while (angle < 0) {
            angle += 360;
        }
        return angle;
    }

}
