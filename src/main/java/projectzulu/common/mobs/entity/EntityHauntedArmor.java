package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityHauntedArmor extends EntityGenericAnimal implements IMob {

    protected int wakeUpTimer = 0;

    public int getWakeUpTimer() {
        return wakeUpTimer;
    }

    protected int randomDirection = 0;
    boolean shouldHover = false;

    public EntityHauntedArmor(World par1World) {
        super(par1World);
        randomDirection = rand.nextInt(16);

        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));

        tasks.addTask(2, new EntityAIStayStill(this, EntityStates.idle));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false));
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

    public EntityHauntedArmor(World par1World, double parx, double pary, double parz, boolean shouldHover) {
        this(par1World);
        setLocationAndAngles(parx, pary, parz, 1, 1);
        setPosition(parx, pary, parz);
        this.shouldHover = shouldHover;
    }

    public void setPersistenceRequired(boolean persistenceRequired) {
        try {
            ObfuscationHelper.setCatchableFieldUsingReflection("field_82179_bU", EntityLiving.class, this, true, false,
                    true);
        } catch (NoSuchFieldException e) {
            ObfuscationHelper.setFieldUsingReflection("persistenceRequired", EntityLiving.class, this, true, true);
        }
    }

    public void setRandomArmor(World world) {
        int number = world.rand.nextInt(2);
        switch (number) {
        case 0:
            setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
            setCurrentItemOrArmor(1, new ItemStack(Items.iron_helmet));
            setCurrentItemOrArmor(2, new ItemStack(Items.iron_chestplate));
            setCurrentItemOrArmor(3, new ItemStack(Items.iron_leggings));
            setCurrentItemOrArmor(4, new ItemStack(Items.iron_boots));
            break;
        case 1:
            setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
            setCurrentItemOrArmor(1, new ItemStack(Items.golden_helmet));
            setCurrentItemOrArmor(2, new ItemStack(Items.golden_chestplate));
            setCurrentItemOrArmor(3, new ItemStack(Items.golden_leggings));
            setCurrentItemOrArmor(4, new ItemStack(Items.golden_boots));
        }
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
        IEntityLivingData entityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        setRandomArmor(worldObj);
        return entityLivingData;
    }

    @Override
    protected String getHurtSound() {
        return "random.break";
    }

    @Override
    protected String getDeathSound() {
        return "random.break";
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue() {
        int var1 = super.getTotalArmorValue() + 2;
        if (var1 > 20) {
            var1 = 20;
        }
        return var1;
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void onLivingUpdate() {
        if (shouldHover == true) {
            this.motionY = 0;
            if (ticksExisted > (20 * 10)) {
                shouldHover = false;
            }
        }

        super.onLivingUpdate();

        if (getEntityState() == EntityStates.idle && worldObj.getClosestPlayerToEntity(this, 5D) != null) {
            angerLevel = 400;
            wakeUpTimer = 30;
        }

        wakeUpTimer = Math.max(wakeUpTimer - 1, 0);
    }

    @Override
    protected void dropEquipment(boolean par1, int par2) {
        if (worldObj.rand.nextInt(4) == 0) {
            super.dropEquipment(par1, par2);
        }
    }
}