package projectzulu.common.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCreeperBlossomPrimed extends Entity {
    /** How long the fuse is */
    public int fuse;

    public EntityCreeperBlossomPrimed(World par1World) {
        super(par1World);
        this.fuse = 40;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityCreeperBlossomPrimed(World par1World, double par2, double par4, double par6) {
        this(par1World);
        this.setPosition(par2, par4, par6);
        float var8 = (float) (Math.random() * Math.PI * 2.0D);
        this.motionX = (double) (-((float) Math.sin((double) var8)) * 0.02F);
        this.motionY = 0.20000000298023224D;
        this.motionZ = (double) (-((float) Math.cos((double) var8)) * 0.02F);
        this.fuse = 40;
        this.prevPosX = par2;
        this.prevPosY = par4;
        this.prevPosZ = par6;
    }

    @Override
    protected void entityInit() {
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        if (this.fuse-- <= 0) {
            this.setDead();

            if (!this.worldObj.isRemote) {
                this.explode();
            }
        } else {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode() {
        float var1 = 1.5F;
        this.worldObj.createExplosion((Entity) null, this.posX, this.posY, this.posZ, var1, this.worldObj
                .getGameRules().getGameRuleBooleanValue("mobGriefing"));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setByte("Fuse", (byte) this.fuse);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        this.fuse = par1NBTTagCompound.getByte("Fuse");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getShadowSize() {
        return 0.0F;
    }
}
