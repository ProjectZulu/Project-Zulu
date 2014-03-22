package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIControlledByPlayer;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityHorseBase extends EntityGenericAnimal {

    private final EntityAIControlledByPlayer aiControlledByPlayer;

    public EntityHorseBase(World par1World) {
        super(par1World);
        setSize(1.5f, 2.0f);

        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));

        tasks.addTask(1, aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.34F));

        tasks.addTask(2, new EntityAIPanic(this, 1.25f));
        tasks.addTask(3, new EntityAIMate(this, 1.0f));
        tasks.addTask(4, new EntityAITempt(this, 1.2f, Items.apple, false));
        tasks.addTask(5, new EntityAIFollowParent(this, 1.1f));

        tasks.addTask(6, new EntityAIAttackOnCollide(this, 1.0f, false));
        tasks.addTask(8, new EntityAIWander(this, 1.0f, 20));

        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
    }
    
    @Override
    public boolean isRideable() {
        return true;
    }

    @Override
    public int getTotalArmorValue() {
        return 0;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "horse";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "horsehurt";
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.riddenByEntity != null
                && (par1DamageSource.getEntity() != null && par1DamageSource.getEntity().equals(this.riddenByEntity))
                || (par1DamageSource.getSourceOfDamage() != null && par1DamageSource.getSourceOfDamage().equals(
                        this.riddenByEntity))) {
            return false;
        } else {
            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }

    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        if (itemStack != null
                && (itemStack.getItem() == Items.apple || itemStack.getItem() == Items.carrot)) {
            return true;
        }
        return false;
    }
}