package projectzulu.common.mobs.entity;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFlyingWander;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIVultureFollow;
import cpw.mods.fml.common.Loader;

public class EntityVulture extends EntityGenericAnimal {
    public EntityModelRotation eFEETROT = new EntityModelRotation();
    public EntityModelRotation eBODYROT = new EntityModelRotation();

    public EntityModelRotation eLEFTWING = new EntityModelRotation();
    public EntityModelRotation ewingleft4 = new EntityModelRotation();
    public EntityModelRotation ewingleft3 = new EntityModelRotation();
    public EntityModelRotation ewingleft2 = new EntityModelRotation();
    public EntityModelRotation ewingleft1 = new EntityModelRotation();

    public EntityModelRotation eRIGTHWING = new EntityModelRotation();
    public EntityModelRotation ewingrig4 = new EntityModelRotation();
    public EntityModelRotation ewingrig3 = new EntityModelRotation();
    public EntityModelRotation ewingrig2 = new EntityModelRotation();
    public EntityModelRotation ewingrig1 = new EntityModelRotation();

    public EntityModelRotation eNECKROT1 = new EntityModelRotation();
    public EntityModelRotation eNECKROT2 = new EntityModelRotation();
    public EntityModelRotation eNECKROT3 = new EntityModelRotation();
    public EntityModelRotation eHEADROT = new EntityModelRotation();
    public EntityModelRotation eTAILROT = new EntityModelRotation();

    boolean manyVultures = false;
    private int maxTargetHealthToAttack = (Integer) CustomEntityList.getByEntity(this).modData.get().customData
            .get("maxTargetHealth");
    float curiosity = 0;
    int ticksToCheckAbilities = 3;

    public EntityVulture(World par1World) {
        super(par1World);
        setSize(1.0f, 1.4f);

        float moveSpeed = 0.18f;
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
        if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
            // TODO: Switch to this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()???
            moveSpeed = entityEntry.modData.get().entityProperties.moveSpeed;
        }

        maxFlightHeight = 20;
        getNavigator().setAvoidsWater(true);

        tasks.addTask(2,
                new EntityAIVultureFollow(this, moveSpeed, false).setValidStates(EnumSet.of(EntityStates.following)));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, moveSpeed, false));
        tasks.addTask(6, new EntityAIFlyingWander(this, moveSpeed));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(
                2,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking,
                        EntityStates.following), EntityPlayer.class, 16.0F, 0, true));
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
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "vulturehurt";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "vulturehurt";
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();

        if (ticksExisted % ticksToCheckAbilities == 0) {

            /* Check if their is a nearby Player to Follow */
            EntityPlayer nearbyPlayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);
            if (nearbyPlayer != null) {
                int distToTargetXZ = (int) Math.sqrt(Math.pow(nearbyPlayer.posX - this.posX, 2)
                        + Math.pow(nearbyPlayer.posZ - this.posZ, 2));
                if (distToTargetXZ < 16) {
                    curiosity = 140;
                }
            }
            shouldFollow = curiosity > 0 ? true : false;
            curiosity = Math.max(curiosity - ticksToCheckAbilities, 0);

            /*
             * Assuming we're following a Player, check if We Should Attack by Comparing number of Nearby Vultures to
             * the Health of our Target
             */
            Entity targetedEntity = nearbyPlayer;
            if (curiosity > 0 && targetedEntity != null) {
                int nearbyVultures = 0;
                AxisAlignedBB var15 = this.boundingBox.copy();
                var15 = var15.expand(10, 10, 10);
                List nearbyEntities = this.worldObj.getEntitiesWithinAABB(EntityVulture.class, var15);
                if (nearbyEntities != null && !nearbyEntities.isEmpty()) {
                    Iterator var10 = nearbyEntities.iterator();

                    while (var10.hasNext()) {
                        Entity var4 = (Entity) var10.next();
                        if (var4 instanceof EntityVulture
                                && (((EntityVulture) var4).getEntityState() == EntityStates.following || ((EntityVulture) var4)
                                        .getEntityState() == EntityStates.attacking)) {
                            nearbyVultures += 1;
                        }
                    }
                }
                if (((EntityLivingBase) targetedEntity).getHealth() < maxTargetHealthToAttack
                        && ((EntityLivingBase) targetedEntity).getHealth() < nearbyVultures * 4) {
                    setAngerLevel(400);
                }
            }
        }
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 16), 1);
        }
        super.dropRareDrop(par1);
    }
}
