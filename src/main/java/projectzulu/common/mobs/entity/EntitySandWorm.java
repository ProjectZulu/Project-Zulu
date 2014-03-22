package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIMoveTowardsRestriction;
import projectzulu.common.mobs.entityai.EntityAIMoveTowardsTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIStayStill;

public class EntitySandWorm extends EntityGenericAnimal implements IMob {

    public EntitySandWorm(World par1World) {
        super(par1World);
        setSize(1.5f, 1.0f);
        getNavigator().setAvoidsWater(false);
        tasks.addTask(2, new EntityAIStayStill(this, EntityStates.idle));

        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, true));
        tasks.addTask(4, new EntityAIMoveTowardsTarget(this, 1.0f, 32.0F));

        tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 1.0f));

        // targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
        targetTasks.addTask(5,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityVillager.class, 16.0F, 0, true));
    }
    
    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    protected boolean canDespawn() {
        return true;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        setAngerLevel(400);
        super.onCollideWithPlayer(par1EntityPlayer);
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        setAngerLevel(400);
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && ticksExisted % (10 * 20) == 0) {
            heal(1);
        }

        super.onLivingUpdate();

        if (getEntityState() == EntityStates.idle && worldObj.getClosestPlayerToEntity(this, 4D) != null) {
            setAngerLevel(100);
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "sandwormroar";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
        return null;
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void func_145780_a(int xCoord, int yCoord, int zCoord, Block stepBlock) {
        this.worldObj.playSoundAtEntity(this, "sand", 1.0F, 1.0F);
    }
}
