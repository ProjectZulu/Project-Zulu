package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFlyingWander;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityPelican extends EntityGenericAnimal {

    public EntityPelican(World par1World) {
        super(par1World);
        this.setSize(1.5f, 1.4f);

        float moveSpeed = 0.22f;
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
        if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
            // TODO: Switch to this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()???
            moveSpeed = entityEntry.modData.get().entityProperties.moveSpeed;
        }

        this.maxFlightHeight = 15;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0f, false, 2f * 2f));
        this.tasks.addTask(6, new EntityAIFlyingWander(this, moveSpeed));
        this.tasks.addTask(9, new EntityAIWander(this, 1.0f, 120));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));
        this.targetTasks.addTask(2,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
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

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "pelicanliving";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "pelicanhurt";
    }

    @Override
    protected void updateAITasks() {

        /* Handle Landing */
        if (!isEntityGrounded() && worldObj.isAirBlock((int) this.posX, (int) this.posY - 1, (int) this.posZ)
                && rand.nextInt(200) == 0 && !worldObj.isRemote) {
            this.dataWatcher.updateObject(17, Byte.valueOf((byte) 1));
        }

        /* Handle Takeoff */
        if (isEntityGrounded() && rand.nextInt(800) == 0 && !worldObj.isRemote) {
            this.dataWatcher.updateObject(17, Byte.valueOf((byte) 0));
        }
        /* Take Off If Falling Off Cliff */
        if (isEntityGrounded() && !worldObj.isRemote
                && worldObj.isAirBlock((int) this.posX, (int) this.posY - 1, (int) this.posZ)
                && worldObj.isAirBlock((int) this.posX, (int) this.posY - 2, (int) this.posZ)
                && worldObj.isAirBlock((int) this.posX, (int) this.posY - 3, (int) this.posZ)) {
            this.dataWatcher.updateObject(17, Byte.valueOf((byte) 0));
        }
        super.updateAITasks();
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    @Override
    public int getMaxSpawnedInChunk() {
        return 3;
    }
}
