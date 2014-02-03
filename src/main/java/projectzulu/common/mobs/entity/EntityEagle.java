package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFlyingWander;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;

public class EntityEagle extends EntityGenericAnimal {

    public EntityEagle(World par1World) {
        super(par1World);
        this.setSize(1.0f, 1.4f);

        float moveSpeed = 0.22f;
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
        if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
            // TODO: Switch to this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()???
            moveSpeed = entityEntry.modData.get().entityProperties.moveSpeed;
        }

        this.maxFlightHeight = 30;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0f, false));
        this.tasks.addTask(6, new EntityAIFlyingWander(this, moveSpeed));

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
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "eagleliving";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "eaglehurt";
    }

    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        return false;
    }
}
