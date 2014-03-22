package projectzulu.common.mobs.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.Properties;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.PZPacket;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.packets.PacketAnimTime;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

/**
 * Universal AI Structure for Project Zulu Entities
 * 
 * Entities are assumed to be functioning using the "new AI", some legacy AI is working but is not guaranteed functional
 */
public class EntityGenericAnimal extends EntityGenericTameable {

    /* Fixed Variables */
    public int maxAnimTime = 20;
    public boolean forceDespawn = false;
    /* Chance Entity will Flee when attacked */
    private float flightChance;

    public EntityGenericAnimal(World par1World) {
        super(par1World);
        experienceValue = 3;
    }

    /**
     * This looks to be used for Setting the default values for certain Attributes.
     * 
     * See {@link#EntityDragon} which sets health to 200
     */
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
        if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
            // Register Damage Attribute
            this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
            
            // Set Base values of attributes
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(
                    entityEntry.modData.get().entityProperties.maxHealth);
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(
                    entityEntry.modData.get().entityProperties.moveSpeed);
            this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(
                    entityEntry.modData.get().entityProperties.followRange);
            this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(
                    entityEntry.modData.get().entityProperties.knockbackResistance);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(
                    entityEntry.modData.get().entityProperties.attackDamage);
            flightChance = entityEntry.modData.get().entityProperties.flightChance;
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        /* Entity State */
        this.dataWatcher.addObject(20, Short.valueOf((short) 0));
    }

    protected void updateDWEntityState(EntityStates entityState) {
        this.dataWatcher.updateObject(20, (short) (entityState.index));
    }

    public int getDWEntityState() {
        return this.dataWatcher.getWatchableObjectShort(20);
    }

    @Override
    protected void updateAITasks() {
        updateAIState();
        super.updateAITasks();
        if (shouldAttack) {
            this.setAngerLevel(3);
        }
        updateDWEntityState(entityState);
    }

    public void updateAIState() {
        if (worldObj.difficultySetting == EnumDifficulty.PEACEFUL && Properties.despawnInPeaceful && !isTamed()) {
            this.setDead();
        }
        /* AI Updates are done Before UpdateTasks such that some states can be manually triggered */
        if (fleeingTick > 0) {
            entityState = EntityStates.fleeing;
        } else if (loveTimer > 0) {
            entityState = EntityStates.inLove;
        } else if (angerLevel > 0 && (getAttackTarget() != null || getAITarget() != null)) {
            entityState = EntityStates.attacking;
        } else if (angerLevel > 0) {
            entityState = EntityStates.looking;
        } else if (isSitting()) {
            entityState = EntityStates.sitting; // TODO add Check that if Player doesn't Exists, it should Sit. Should I
                                                // do this? I'm Favoring Not.
        } else if (shouldFollow) {
            entityState = EntityStates.following;
        } else {
            entityState = EntityStates.idle;
        }
    }

    @Override
    public void onLivingUpdate() {
        // rotationYaw = rotationYawHead;
        super.onLivingUpdate();
        animTime = Math.max(animTime - 1, 0);
        entityState = EntityStates.getEntityByIndex(getDWEntityState());
    }

    @Override
    protected boolean canDespawn() {
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
        if (entityEntry != null && !isTamed()) {
            return forceDespawn || entityEntry.modData.get().shouldDespawn;
        } else {
            return super.canDespawn();
        }
    }

    @Override
    public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
        if (forSpawnCount && entityEntry != null) {
            return entityEntry.modData.get().spawnType != null ? entityEntry.modData.get().spawnType.equals(type)
                    : false;
        } else if (entityEntry != null) {
            return entityEntry.modData.get().creatureType != null ? entityEntry.modData.get().creatureType.equals(type)
                    : super.isCreatureType(type, forSpawnCount);
        } else {
            return super.isCreatureType(type, forSpawnCount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity targetEntity) {
        if (targetEntity.boundingBox.maxY > this.boundingBox.minY
                && targetEntity.boundingBox.minY < this.boundingBox.maxY) {
            animTime = maxAnimTime;
            if (!worldObj.isRemote) {
                PZPacket packet = new PacketAnimTime().setPacketData(getEntityId(), animTime);
                ProjectZulu_Core.getPipeline()
                        .sendToAllAround(packet, new TargetPoint(dimension, posX, posY, posZ, 30));
            }

            float damage = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
            int knockbackScale = 0;

            if (targetEntity instanceof EntityLivingBase) {
                damage += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) targetEntity);
                knockbackScale += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) targetEntity);
            }

            boolean attackedSucceded = targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

            if (attackedSucceded) {
                if (knockbackScale > 0) {
                    targetEntity.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F)
                            * (float) knockbackScale * 0.5F), 0.1D,
                            (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F)
                                    * (float) knockbackScale * 0.5F));
                    this.motionX *= 0.6D;
                    this.motionZ *= 0.6D;
                }

                int fireScale = EnchantmentHelper.getFireAspectModifier(this);

                if (fireScale > 0) {
                    targetEntity.setFire(fireScale * 4);
                }

                if (targetEntity instanceof EntityLivingBase) {
                    EnchantmentHelper.func_151384_a((EntityLivingBase)targetEntity, this);
                }
                EnchantmentHelper.func_151385_b(this, targetEntity);

            }
            return attackedSucceded && super.attackEntityAsMob(targetEntity);
        }
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (super.attackEntityFrom(par1DamageSource, par2)) {
            if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer) {
                EntityPlayer attackingPlayer = (EntityPlayer) par1DamageSource.getEntity();

                if (shouldNotifySimilar(attackingPlayer)) {
                    List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
                            this.boundingBox.expand(20.0D, 20.0D, 20.0D));
                    Iterator nearbyEntityIterator = var4.iterator();

                    while (nearbyEntityIterator.hasNext()) {
                        Entity nearbyEntity = (Entity) nearbyEntityIterator.next();
                        if (nearbyEntity.getClass().equals(this.getClass())) {
                            EntityGenericAnimal nearbyAlly = (EntityGenericAnimal) nearbyEntity;
                            nearbyAlly.entityAttackedReaction(attackingPlayer);
                        }
                    }
                }
                entityAttackedReaction(attackingPlayer);
                return true;
            }
        }
        return false;
    }

    protected void entityAttackedReaction(EntityPlayer attackingPlayer) {
        if (this.isTamed()) {

        } else {
            if (shouldPanic()) {
                setFleeTick(80);
            } else {
                setAngerLevel(400);
            }
        }
    }

    public boolean shouldNotifySimilar(EntityPlayer attackingPlayer) {
        return false;
    }

    /**
     * Function that determines if the Fight/Flight reaction of the animal on attack
     */
    protected boolean shouldPanic() {
        return !(worldObj.rand.nextFloat() * 100 >= flightChance);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
        if (isTamed()) {
            return 0;
        } else {
            if (this instanceof IMob || getEntityState() == EntityStates.attacking
                    || getEntityState() == EntityStates.looking) {
                return 5;
            } else {
                return rand.nextInt(2) + 1;
            }
        }
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere() {
        int xCoord = MathHelper.floor_double(this.posX);
        int yCoord = MathHelper.floor_double(this.boundingBox.minY);
        int zCoord = MathHelper.floor_double(this.posZ);
        boolean wasSuccesful = false;
        CustomEntityList customEntity = CustomEntityList.getByEntity(this);
        if (customEntity == null) {
            ProjectZuluLog
                    .severe("Entity %s is Trying to Spawn but does not exist in the CustomEntityList. It will not spawn, please report this to Modder.",
                            this.getClass().toString());
            return false;
        }

        if (customEntity.modData.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
                && isValidLightLevel(worldObj, xCoord, yCoord, zCoord)
                && isValidLocation(worldObj, xCoord, yCoord, zCoord)) {
            wasSuccesful = true;
        }

        if (customEntity.modData.get().reportSpawningInLog) {
            if (wasSuccesful) {
                ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s", getCommandSenderName(), xCoord, yCoord,
                        zCoord, worldObj.getBiomeGenForCoords(xCoord, zCoord));
            } else {
                ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",
                        getCommandSenderName(), xCoord, yCoord, zCoord, worldObj.getBiomeGenForCoords(xCoord, zCoord));
            }
        }
        return wasSuccesful;
    }

    protected boolean isValidLightLevel(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.getSavedLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord) < 1;
    }

    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return true;
    }

    /**
     * Drop 0-2 items of this living's type
     */
    @Override
    protected void dropFewItems(boolean par1, int par2) {
        CustomEntityList customEntity = CustomEntityList.getByEntity(this);
        if (customEntity != null) {
            Collection<ItemStack> loot = customEntity.modData.get().getLoot(rand, par2);
            Iterator<ItemStack> lootIterator = loot.iterator();
            while (lootIterator.hasNext()) {
                ItemStack itemStack = lootIterator.next();
                if (itemStack != null) {
                    entityDropItem(itemStack, 1);
                }
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeEntityToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setBoolean("ForceDespawn", forceDespawn);

    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readEntityFromNBT(par1nbtTagCompound);
        forceDespawn = par1nbtTagCompound.getBoolean("ForceDespawn");
    }
}
