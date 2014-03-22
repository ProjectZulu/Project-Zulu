package projectzulu.common.mobs.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIFlyingWander;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import cpw.mods.fml.common.Loader;

public class EntityFinch extends EntityGenericAnimal implements IAnimals {
    int stayStillTimer = 0;

    public EntityFinch(World par1World) {
        super(par1World);
        // noClip = true;
        this.setSize(0.5f, 0.5f);

        float moveSpeed = 0.22f;
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
        if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
            // TODO: Switch to this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()???
            moveSpeed = entityEntry.modData.get().entityProperties.moveSpeed;
        }

        this.maxFlightHeight = 5;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(2, new EntityAIStayStill(this, EntityStates.posture));

        // this.tasks.addTask(2, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
        this.tasks.addTask(6, new EntityAIFlyingWander(this, (float) moveSpeed));
        // this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));
        // this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking,
        // EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
    }

    @Override
    public boolean defaultGrounded() {
        return false;
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    @Override
    protected void fall(float par1) {
    }

    /**
     * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
     * and deal fall damage if landing on the ground. Args: distanceFallenThisTick, onGround
     */
    @Override
    protected void updateFallState(double par1, boolean par3) {
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "birdhurt";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "bird";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void func_145780_a(int xCoord, int yCoord, int zCoord, Block stepBlock) {
        this.worldObj.playSoundAtEntity(this, "sounds.birdplop", 1.0F, 1.0F);
    }

    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        return false;
    }

    @Override
    protected boolean shouldPanic() {
        return true;
    }

    @Override
    public void updateAIState() {
        if (stayStillTimer > 0) {
            entityState = EntityStates.posture;
        } else {
            super.updateAIState();
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();

        /* If We are above Solid Ground, have a small Chance at Landing */
        if (this.rand.nextInt(100) == 0
                && this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int) this.posY - 1,
                        MathHelper.floor_double(this.posZ)).isNormalCube()) {
            stayStillTimer = 400;
        }

        /* If Posing, Check if We should Stay Flee */
        if (getEntityState() == EntityStates.posture) {
            /* If we are not on a Normal Block, Fly Free */
            if (!this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int) this.posY - 1,
                    MathHelper.floor_double(this.posZ)).isNormalCube()) {
                stayStillTimer = 0;
            } else {
                /* Chance to Chance Direction We are Facing ?--Do We Care?--? */
                if (this.rand.nextInt(200) == 0) {
                    this.rotationYawHead = this.rand.nextInt(360);
                }

                /* If Player is nearby, Entity Should Fly away, think Pigeons */
                if (this.worldObj.getClosestPlayerToEntity(this, 4.0D) != null || this.rand.nextInt(400) == 0) {
                    stayStillTimer = 0;
                }
            }
            /*
             * Deincrement stayStillTimer, if it hits zero Entity Should Fly : Note that timer is likely being set to
             * max again by the initial shouldSit check
             */
            stayStillTimer = Math.max(stayStillTimer - 1, 0);
        }
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected boolean isValidLightLevel(World world, int xCoord, int yCoord, int zCoord) {
        return this.worldObj.getSavedLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord) < 1;
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 0), 1);
        }
        super.dropRareDrop(par1);
    }
}
