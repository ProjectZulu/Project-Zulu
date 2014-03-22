package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMoveTowardsTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityLizard extends EntityGenericAnimal implements IRangedAttackMob, IMob {

    public int counter = 0;
    public boolean prepareToSpit = false;
    public int timeTillSpit = 0;

    public EntityLizard(World par1World) {
        super(par1World);
        setSize(0.9f, 0.5f);

        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0f, 32.0F));
        // tasks.addTask(2, new EntityAIArrowAttack(this, moveSpeed, 3, 60));

        // tasks.addTask(3, new EntityAIMoveThroughVillage(this, moveSpeed, true));
        // tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, moveSpeed));
        tasks.addTask(6, new EntityAIWander(this, 1.0f, 120));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(8, new EntityAILookIdle(this));

        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
    }
    
    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    @Override
    protected boolean isValidLightLevel(World world, int xCoord, int yCoord, int zCoord) {
        int var1 = xCoord;
        int var2 = yCoord;
        int var3 = zCoord;
        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > this.rand.nextInt(32)) {
            return false;
        } else {
            int var4 = this.worldObj.getBlockLightValue(var1, var2, var3);

            if (this.worldObj.isThundering()) {
                int var5 = this.worldObj.skylightSubtracted;
                this.worldObj.skylightSubtracted = 10;
                var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
                this.worldObj.skylightSubtracted = var5;
            }
            return var4 <= this.rand.nextInt(8);
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && counter % (10 * 20) == 0) {
            heal(1);
        }
        float var3 = this.getBrightness(1.0F);
        if (var3 < 0.5) {
            angerLevel = 120;
        }
        super.onLivingUpdate();

        if (timeTillSpit == 20) {
            prepareToSpit = true;
        }

        // Check to see if Entity should Use Ability
        if (timeTillSpit == 0) {

            // Check if there is a player nearby
            // EntityPlayer tempTarget = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
            EntityLivingBase tempTarget = this.getAttackTarget();

            if (tempTarget != null && getDistanceToEntity(tempTarget) < 15) {

                double var11 = tempTarget.posX - this.posX;
                double var13 = tempTarget.boundingBox.minY + tempTarget.height / 2.0F
                        - (this.posY + this.height / 2.0F);
                double var15 = tempTarget.posZ - this.posZ;

                if (!worldObj.isRemote) {
                    EntityLizardSpit var17 = new EntityLizardSpit(this.worldObj, this, var11, var13, var15);
                    double var18 = 1.0D;
                    Vec3 var20 = this.getLook(1.0F);
                    var17.posX = this.posX + var20.xCoord * var18;
                    var17.posY = this.posY + this.height / 2.0F;
                    var17.posZ = this.posZ + var20.zCoord * var18;
                    this.worldObj.spawnEntityInWorld(var17);
                }
                timeTillSpit = 80;
                prepareToSpit = false;

            }

        } else if (timeTillSpit == 0) {
            timeTillSpit = 80;
            prepareToSpit = false;
        }

        counter++;
        // Reduce Cooldown on Spit Ability
        timeTillSpit = (int) Math.max(timeTillSpit - 1, 0.0);

    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "lizardhurt";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void func_145780_a(int xCoord, int yCoord, int zCoord, Block stepBlock) {
        this.worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 1.0F, 1.0F);
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 10), 1);
        }
        super.dropRareDrop(par1);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase entitylivingbase, float f) {

    }
}
