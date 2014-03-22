package projectzulu.common.mobs.entity;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFollowOwner;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIOwnerHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIOwnerHurtTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityMammoth extends EntityGenericAnimal implements IAnimals {

    Vec3 stampedeDirection = Vec3.createVectorHelper(0, 0, 0);
    public int timeLeftStampeding = 0;

    public EntityMammoth(World par1World) {
        super(par1World);
        setSize(4.5f, 5.4f);
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));

        tasks.addTask(2, new EntityAIStayStill(this, EntityStates.sitting));

        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false, 5.5f * 5.5f));
        tasks.addTask(4, new EntityAIFollowOwner(this, 1.0f, 10.0F, 2.0F));

        tasks.addTask(5, new EntityAIMate(this, 1.0f));
        tasks.addTask(6, new EntityAITempt(this, 1.2f, Item.getItemFromBlock(Blocks.leaves), false));
        tasks.addTask(7, new EntityAIFollowParent(this, 1.1f));
        tasks.addTask(9, new EntityAIWander(this, 1.0f, 120));

        targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
        // targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking,
        // EntityStates.looking), EntityLiving.class, 16.0F, 0, false, false, IMob.mobSelector));
    }

    public float getAttackDistance() {
        return isChild() ? 3.0f : 6.0f;
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    public int getTotalArmorValue() {
        return 6;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "mammothliving";
    }

    @Override
    public void knockBack(Entity par1Entity, float par2, double par3, double par5) {
        if (this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()) {
            this.isAirBorne = true;
            float var7 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
            float var8 = 0.4F;
            this.motionX /= 2.0D;
            this.motionY /= 2.0D;
            this.motionZ /= 2.0D;
            this.motionX -= par3 / var7 * var8 * 0.2;
            this.motionY += var8;
            this.motionZ -= par5 / var7 * var8 * 0.2;

            if (this.motionY > 0.2000000059604645D) {
                this.motionY = 0.2000000059604645D;
            }
        }
    }

    public void causeStampede(Vec3 locationOfCause) {
        // this.pathToEntity = null;
        timeLeftStampeding = 20 * 10;
        List<Vec3> centroids = new ArrayList<Vec3>();

        AxisAlignedBB var15 = this.boundingBox.copy();
        var15 = var15.expand(100, 60, 100);
        List nearbyEntities = this.worldObj.getEntitiesWithinAABB(EntityMammoth.class, var15);
        Vec3 herdCentroid = Vec3.createVectorHelper(0, 0, 0);
        int i = 0;
        if (nearbyEntities != null && !nearbyEntities.isEmpty()) {
            Iterator var10 = nearbyEntities.iterator();
            while (var10.hasNext()) {
                Entity var4 = (Entity) var10.next();

                if (var4 instanceof EntityMammoth) {
                    // Notify Mammoth that he should be stampeding, If the lazy bastard isn't
                    if (((EntityMammoth) var4).timeLeftStampeding == 0) {
                        ((EntityMammoth) var4).causeStampede(locationOfCause);
                    }
                    centroids.add(i, Vec3.createVectorHelper(var4.posX, var4.posY, var4.posZ));
                    herdCentroid = Vec3.createVectorHelper(herdCentroid.xCoord + var4.posX, herdCentroid.yCoord
                            + var4.posY, herdCentroid.zCoord + var4.posZ);
                    i++;
                }
            }
        }
        // herdCentroid = Vec3.createVectorHelper(herdCentroid.xCoord/i, herdCentroid.yCoord/i, herdCentroid.zCoord/i);
        herdCentroid = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);

        stampedeDirection = Vec3.createVectorHelper((herdCentroid.xCoord - locationOfCause.xCoord),
                (herdCentroid.yCoord - locationOfCause.yCoord), (herdCentroid.zCoord - locationOfCause.zCoord));
        if (Math.abs(stampedeDirection.xCoord) < 6 && Math.abs(stampedeDirection.zCoord) < 6) {
            stampedeDirection.xCoord = stampedeDirection.xCoord * 6;
            stampedeDirection.zCoord = stampedeDirection.zCoord * 6;
        }
        if (Math.abs(stampedeDirection.xCoord) > 32 || Math.abs(stampedeDirection.zCoord) > 32) {
            stampedeDirection.xCoord = stampedeDirection.xCoord / 2;
            stampedeDirection.zCoord = stampedeDirection.zCoord / 2;
        }

        Vec3 locToStamp = Vec3.createVectorHelper(this.posX + stampedeDirection.xCoord, worldObj
                .getTopSolidOrLiquidBlock((int) (this.posX + stampedeDirection.xCoord),
                        (int) (this.posY + stampedeDirection.zCoord)), this.posZ + stampedeDirection.zCoord);
        // this.pathToEntity = this.worldObj.getEntityPathToXYZ(this, (int)locToStamp.xCoord, (int)locToStamp.yCoord,
        // (int)locToStamp.zCoord, 32f, false, true, false, true);
    }

    protected void riderAttackNearby() {
        /* Search For Nearby Entities */
        /* Select ones To Attack */
        AxisAlignedBB var15 = this.boundingBox.copy();
        var15 = var15.expand(10, 10, 10);
        List nearbyEntities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, var15);

        int i = 0;
        if (nearbyEntities != null && !nearbyEntities.isEmpty()) {
            Iterator var10 = nearbyEntities.iterator();
            while (var10.hasNext()) {
                Entity var4 = (Entity) var10.next();
                if (var4 instanceof EntityPlayer
                        && ((EntityPlayer) riddenByEntity).getCommandSenderName().equals(((EntityPlayer) var4).getCommandSenderName())) {

                } else {
                    if (var4.getDistanceToEntity(this) < getAttackDistance()) {
                        attackEntityAsMob(var4);
                    }
                }
            }
        }
        this.attackTime = 20;
        worldObj.playSound(this.posX, this.posY, this.posZ, "sounds.mammothstomp", 1.0f, 1.0f, false);
    }

    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() == Item.getItemFromBlock(Blocks.leaves)) {
            return true;
        } else {
            return super.isValidBreedingItem(itemStack);
        }
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 11), 1);
        }
        super.dropRareDrop(par1);
    }

}