package projectzulu.common.mobs.entity;

import java.util.EnumSet;
import java.util.UUID;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityArmadillo extends EntityGenericAnimal implements IAnimals {

    public static final UUID field_110179_h = UUID.fromString("E199AD22-BA8B-4C53-8A13-1182D5C69D3A");
    public static final AttributeModifier CHARGING_BONUS = (new AttributeModifier(field_110179_h,
            "Fleeing speed bonus", -0.3D, 2)).setSaved(false);

    /* Model Assist Variables, Legacy: Code should be Reworked without them */
    public EntityModelRotation eWHOLE = new EntityModelRotation();
    public EntityModelRotation eHEADPIECE = new EntityModelRotation();
    public EntityModelRotation eREARRTR1 = new EntityModelRotation();
    public EntityModelRotation eREARRTR2 = new EntityModelRotation();
    public EntityModelRotation eREARRTR3 = new EntityModelRotation();
    public EntityModelRotation etail = new EntityModelRotation();
    public EntityModelRotation eleg3 = new EntityModelRotation();
    public EntityModelRotation eleg4 = new EntityModelRotation();

    /* General Ability Variabeles */
    int ticksToCheckAbilities = 3;

    /* In Cover Variables */
    int inCoverTimer = 0;
    static final int inCoverTimerMax = 20;

    /* Charge Variables */
    protected boolean isCharging = false;

    public boolean isCharging() {
        return isCharging;
    }

    protected float timeSinceLastCharge = 5f;
    protected float chargeTriggerThreshold = 10f * 20f + rand.nextInt(10 * 20);
    protected float chargeTime = 0.2f * chargeTriggerThreshold;
    protected int chargeSpeedModifier = 2;

    public EntityArmadillo(World par1World) {
        super(par1World);
        boundingBox.setBounds(-0.05, -0.05, -0.15, 0.05, 1.8, 0.15);

        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));

        tasks.addTask(2, new EntityAIStayStill(this, EntityStates.inCover));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false));
        // this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed, 10.0F, 2.0F));

        tasks.addTask(5, new EntityAIMate(this, 1.0f));
        tasks.addTask(6, new EntityAITempt(this, 1.2f, Items.spider_eye, false));
        tasks.addTask(7, new EntityAIFollowParent(this, 1.1f));
        tasks.addTask(9, new EntityAIWander(this, 1.0f, 120));

        // this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        // this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
        // this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, false,
        // true, IMob.mobSelector));

    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return super.isValidLocation(world, xCoord, yCoord, zCoord)
                && worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue() {
        if (getEntityState() == EntityStates.inCover) {
            return 30;
        } else {
            return 4;
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "armadilloliving";
    }

    @Override
    public void updateAIState() {
        if (inCoverTimer > 0) {
            entityState = EntityStates.inCover;
        } else {
            super.updateAIState();
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();

        if (ticksExisted % ticksToCheckAbilities == 0) {
            /*
             * Checks if There is a player that can see Armadillo and is using a bow. If so, Take Cover /* Also, if
             * there is no Target, then don't take cover
             */
            EntityPlayer tempE = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

            boolean canSee = false;
            boolean isFacing = false;
            if (tempE != null) {
                // Condition 1: Check if player is using an item, and if so is it a bow
                
                // Condition 2: Is the player facing the target. "boolean canSee"
                double angleEntToPlayer = Math.atan2(posX - tempE.posX, tempE.posZ - posZ) * (180.0 / Math.PI) + 180;
                double playerRotationYaw = tempE.rotationYaw;
                double difference = Math.abs(MathHelper.wrapAngleTo180_double(normalizeTo360(angleEntToPlayer)
                        - normalizeTo360(playerRotationYaw)));
                if (difference < 90) {
                    isFacing = true;
                }

                // Condition 3: Can the player see the target
                canSee = this.worldObj.rayTraceBlocks(
                        Vec3.createVectorHelper(tempE.posX, tempE.posY + tempE.getEyeHeight(),
                                tempE.posZ), Vec3.createVectorHelper(this.posX, this.posY, this.posZ)) == null;
            }
            /* If any of the conditions above failed, then Armadillo should not be in Cover */
            if (tempE == null || canSee == false || tempE.isUsingItem() == false || isFacing == false
                    || tempE.inventory.getCurrentItem().getItem() != Items.bow) {
                inCoverTimer = Math.max(inCoverTimer - ticksToCheckAbilities, 0);
            } else {
                /* Reminder: This only occurs when all the above are true. */
                inCoverTimer = inCoverTimerMax;
            }
        }

    }

    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && ticksExisted % (10 * 20) == 0) {
            heal(1);
        }

        if (ticksExisted % ticksToCheckAbilities == 0) {
            /* Check If Entity Should START Charging */
            if (this.timeSinceLastCharge > chargeTriggerThreshold && !isCharging) {
                this.isCharging = true;
                this.timeSinceLastCharge = 0;
                chargeTriggerThreshold = 10f * 20f + rand.nextInt(10 * 20);
            }

            /* Check If Entity Should STOP Charging */
            if (isCharging && this.timeSinceLastCharge > chargeTime) {
                this.isCharging = false;
            }

            /* Increase Time Since Last Charge */
            this.timeSinceLastCharge += ticksToCheckAbilities;
        }

        super.onLivingUpdate();
    }

    @Override
    public float getAIMoveSpeed() {
        float baseSpeed = super.getAIMoveSpeed();
        return isCharging ? baseSpeed * chargeSpeedModifier : baseSpeed;
    }

    private double normalizeTo360(double angle) {

        while (angle < 0 || angle > 360) {
            if (angle < 0.0) {
                angle += 360;
            }
            if (angle > 360.0) {
                angle -= 360;
            }
        }
        return angle;
    }

    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() == Items.spider_eye) {
            return true;
        } else {
            return super.isValidBreedingItem(itemStack);
        }
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId)) {
            if (BlockList.mobHeads.isPresent()) {
                entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 2), 1);
            }
        }
        super.dropRareDrop(par1);
    }
}
