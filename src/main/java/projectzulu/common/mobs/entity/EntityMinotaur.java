package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityMinotaur extends EntityGenericAnimal implements IMob {

    /* General Ability Variables */
    int ticksToCheckAbilities = 3;

    /* Charge Variables */
    protected boolean isCharging = false;
    protected float timeSinceLastCharge = 5f;
    protected float chargeTriggerThreshold = 10f * 20f + rand.nextInt(10 * 20);
    protected float chargeTime = 0.2f * chargeTriggerThreshold;
    protected int chargeSpeedModifier = 2;

    public EntityMinotaur(World par1World) {
        super(par1World);
        myEntitySize = EnumEntitySize.SIZE_6;
        setSize(1.0f, 2.4f);
        experienceValue = 7;

        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));

        tasks.addTask(2, new EntityAIStayStill(this, EntityStates.idle));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false, 2.3f * 2.3f));
        // tasks.addTask(4, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));

        // tasks.addTask(5, new EntityAIMate(this, moveSpeed));
        // tasks.addTask(6, new EntityAITempt(this, moveSpeed, Blocks.tallgrass, false));
        // tasks.addTask(7, new EntityAIFollowParent(this, moveSpeed));
        tasks.addTask(9, new EntityAIWander(this, 1.0f, 120));

        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
        // targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking,
        // EntityStates.looking), EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
    }
    
    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue() {
        return 6;
    }

    @Override
    protected String getLivingSound() {
        return "mob.cow.say";
    }

    @Override
    public int getTalkInterval() {
        return 160;
    }
    
    @Override
    protected void func_145780_a(int xCoord, int yCoord, int zCoord, Block stepBlock) {
        this.worldObj.playSoundAtEntity(this, "mob.cow.step", 0.15F, 1.0F);
    }

    @Override
    protected void updateAITick() {
        this.angerLevel = 100;
        super.updateAITick();
    }

    @Override
    public void onLivingUpdate() {
        this.isInWeb = false;

        if (ticksExisted % ticksToCheckAbilities == 0) {
            /* Check If Entity Should START Charging */
            if (this.timeSinceLastCharge > chargeTriggerThreshold && !isCharging) {// && targetedEntity != null &&
                                                                                   // this.getDistanceToEntity(targetedEntity)
                                                                                   // < 20.0f){
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
}