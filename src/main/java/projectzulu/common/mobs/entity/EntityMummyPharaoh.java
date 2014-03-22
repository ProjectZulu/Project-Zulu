package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityMummyPharaoh extends EntityGenericAnimal implements IMob {

    Vec3 startingPosition;
    int stage = 1;

    boolean spawnMummy = false;
    // Time To Wait after spawning to spawn another
    int spawnCooldown = 4 * 20;
    // Counter. When 0 its okay to spawn another
    int spawnTimer = 60;

    boolean shootFireball = false;
    int shootCooldown = 6 * 20;
    int shootTimer = 30;
    private static final ItemStack defaultHeldItem = ItemList.ankh.isPresent() ? new ItemStack(ItemList.ankh.get())
            : new ItemStack(Items.iron_sword);

    public EntityMummyPharaoh(World par1World) {
        super(par1World);
        setSize(0.6F, 1.8F);

        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0f, true));

        tasks.addTask(7, new EntityAIWander(this, 1.0f, 120));
        tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(9, new EntityAILookIdle(this));

        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.allOf(EntityStates.class),
                EntityPlayer.class, 16.0F, 0, true));
    }

    public EntityMummyPharaoh(World par1World, double parx, double pary, double parz) {
        this(par1World);
        setLocationAndAngles(parx, pary, parz, 1, 1);
        setPosition(parx, pary, parz);
        yOffset = 0.0f;
    }

    @Override
    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    @Override
    public int getTotalArmorValue() {
        switch (stage) {
        case 1:
            return 4;
        case 2:
            return 6;
        case 3:
            return 8;
        case 4:
            return 10;
        default:
            return 2;
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.coreKey + ":" + DefaultProps.entitySounds + "mummyshortroar";
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    // TODO: Some of THis could be moved Server Side Only
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (startingPosition == null) {
            startingPosition = Vec3.createVectorHelper(posX, posY, posZ);
        }

        switch (stage) {
        case 1:
            /* Stage One Update: If Condition Valid Change Stage */
            if (getHealth() < 0.9 * getMaxHealth()) {
                teleportTo(startingPosition.xCoord, startingPosition.yCoord + 2, startingPosition.zCoord);
                stage++;
            }

            break;
        case 2:
            /* Stage Two Ability */
            if (shootFireball == true && !worldObj.isRemote) {
                shootFireballAtTarget();
                shootFireball = false;
            }

            /* Stage Two Update: If Condition Valid Change Stage */
            if (getHealth() < 0.7 * getMaxHealth()) {
                teleportTo(startingPosition.xCoord, startingPosition.yCoord + 2, startingPosition.zCoord);
                stage++;
            }
            break;
        case 3:
            /* Stage Three Ability */
            if (spawnMummy == true && !worldObj.isRemote) {
                spawnMummy();
                spawnMummy = false;
            }

            /* Stage Three Update: If Condition Valid Change Stage */
            if (getHealth() < 0.45 * getMaxHealth()) {
                teleportTo(startingPosition.xCoord, startingPosition.yCoord + 2, startingPosition.zCoord);
                stage++;
            }
            break;

        case 4:
            /* Stage Three Ability */
            if (spawnMummy == true && !worldObj.isRemote) {
                spawnMummy();
                spawnMummy = false;
            }

            /* Stage Three Ability */
            if (shootFireball == true && !worldObj.isRemote) {
                shootFireballAtTarget();
                shootFireball = false;
            }
            break;
        default:
            break;
        }

        /* If Spawn Timer is 0, tell Entity its Allowed to Summon a Mummy */
        if (spawnTimer == 0) {
            spawnMummy = true;
            spawnTimer = spawnCooldown;
        }
        spawnTimer = Math.max(spawnTimer - 1, 0);

        /* If Shoot Timer is 0, tell Entity its Allowed to Shoot a Fireball */
        if (shootTimer == 0) {
            shootFireball = true;
            shootTimer = shootCooldown;
        }
        shootTimer = Math.max(shootTimer - 1, 0);
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getEntity() instanceof EntityPlayer) {
            EntityPlayer tempPlayer = (EntityPlayer) par1DamageSource.getEntity();
            double distance = tempPlayer.getDistanceSqToEntity(this);

            if (distance > 10) {
                int holdRand = rand.nextInt(2);
                if (holdRand == 1) {
                    teleportTo(tempPlayer.posX + 1,
                            worldObj.getHeightValue((int) tempPlayer.posX + 1, (int) tempPlayer.posZ + 1),
                            tempPlayer.posZ + 1);
                }
            } else {
                int holdRand = rand.nextInt(5);
                if (holdRand == 4) {
                    teleportRandomly();
                }
            }
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    private void spawnMummy() {
        // Get a Random Position Around Entity
        double desX;
        double desZ;

        int Min_Distance = 5;
        int Max_Distance = 9;

        // These "hold" are here because putting Rand.nextint without assigning to variable first
        // caused minecraft to get angry at me (/shrug)
        int hold = rand.nextInt(2);
        int hold2 = rand.nextInt(Max_Distance - Min_Distance) + Min_Distance;
        ;
        if (hold != 0) {
            desX = Math.floor(this.posX) + hold2;
        } else {
            desX = Math.floor(this.posX) - hold2;
        }

        hold = rand.nextInt(2);
        hold2 = rand.nextInt(Max_Distance - Min_Distance) + Min_Distance;
        ;
        if (hold != 0) {
            desZ = Math.floor(this.posZ) + hold2;
        } else {
            desZ = Math.floor(this.posZ) - hold2;
        }

        // Note this is not final Y height, this is just for ground level
        int desY = this.worldObj.getHeightValue((int) desX, (int) desZ);
        // If the block is not air
        if (worldObj.isAirBlock((int) desX, desY - 2, (int) desZ)) {
            worldObj.setBlock((int) desX, desY - 0, (int) desZ, Blocks.air);
            worldObj.setBlock((int) desX, desY - 1, (int) desZ, Blocks.air);
        }
        // This sets where the monster will spawn on Y relative to Ground Level
        desY -= 1;

        // Adjust X and Z so that they are at Center of Block and Not Edge
        desX += 0.5;
        desZ += 0.5;

        EntityMummy var17 = new EntityMummy(this.worldObj, desX, desY, desZ);
        this.worldObj.spawnEntityInWorld(var17);
    }

    private void shootFireballAtTarget() {
        EntityPlayer targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 32.0D);
        if (targetedEntity != null) {
            int holdRand = rand.nextInt(10) - 5;
            double sourcePositionX = this.posX + holdRand;
            double sourcePositionY = this.posY + 20;
            holdRand = rand.nextInt(10) - 5;
            double sourcePositionZ = this.posZ + holdRand;

            double var11 = targetedEntity.posX - sourcePositionX;
            double var13 = targetedEntity.boundingBox.minY + targetedEntity.height / 2.0F
                    - (sourcePositionY + this.height / 2.0F);
            double var15 = targetedEntity.posZ - sourcePositionZ;
            this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(var11, var15)) * 180.0F / (float) Math.PI;

            this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1008, (int) this.posX, (int) this.posY,
                    (int) this.posZ, 0);
            EntityFireball var17 = new EntityLargeFireball(this.worldObj, this, var11, var13, var15);
            double var18 = 1.0D;
            Vec3 var20 = this.getLook(1.0F);
            var17.posX = sourcePositionX + var20.xCoord * var18;
            var17.posY = sourcePositionY + this.height / 2.0F + 0.5D;
            var17.posZ = sourcePositionZ + var20.zCoord * var18;
            this.worldObj.spawnEntityInWorld(var17);
        }
    }

    private void shootFireballAroundTarget() {
        EntityPlayer targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 32.0D);
        if (targetedEntity != null) {
            int holdRand = rand.nextInt(10) - 5;
            double sourcePositionX = this.posX + holdRand;
            double sourcePositionY = this.posY + 20;
            holdRand = rand.nextInt(10) - 5;
            double sourcePositionZ = this.posZ + holdRand;

            double desX = targetedEntity.posX + rand.nextInt(10) - 5;
            double desZ = targetedEntity.posZ + rand.nextInt(10) - 5;
            int desY = this.worldObj.getHeightValue((int) desX, (int) desZ);

            double var11 = desX - sourcePositionX;
            double var13 = targetedEntity.boundingBox.minY + targetedEntity.height / 2.0F
                    - (sourcePositionY + this.height / 2.0F);
            double var15 = desZ - sourcePositionZ;
            this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(var11, var15)) * 180.0F / (float) Math.PI;

            this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1008, (int) desX, desY, (int) desZ, 0);
            EntityFireball var17 = new EntityLargeFireball(this.worldObj, this, var11, var13, var15);
            double var18 = 4.0D;
            Vec3 var20 = this.getLook(1.0F);
            var17.posX = sourcePositionX + var20.xCoord * var18;
            var17.posY = sourcePositionY + this.height / 2.0F + 0.5D;
            var17.posZ = sourcePositionZ + var20.zCoord * var18;
            this.worldObj.spawnEntityInWorld(var17);
        }
    }

    /**
     * Teleport the Pharoah to a random nearby position
     */
    private boolean teleportRandomly() {
        double var1 = this.posX + (this.rand.nextDouble() - 0.5D) * 24.0D;
        double var5 = this.posZ + (this.rand.nextDouble() - 0.5D) * 24.0D;
        double var3 = worldObj.getHeightValue((int) var1, (int) var5);
        return this.teleportTo(var1, var3, var5);
    }

    /**
     * Teleport the Pharoah
     */
    private boolean teleportTo(double par1, double par3, double par5) {
        double var7 = this.posX;
        double var9 = this.posY;
        double var11 = this.posZ;
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        boolean var13 = false;
        int var14 = MathHelper.floor_double(this.posX);
        int var15 = MathHelper.floor_double(this.posY);
        int var16 = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(var14, var15, var16)) {
            boolean var17 = false;
            Block var18;
            while (!var17 && var15 > 0) {
                var18 = this.worldObj.getBlock(var14, var15 - 1, var16);

                if (var18 != null && var18.getMaterial().blocksMovement()) {
                    var17 = true;
                } else {
                    --this.posY;
                    --var15;
                }
            }

            if (var17) {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()
                        && !this.worldObj.isAnyLiquid(this.boundingBox)) {
                    var13 = true;
                }
            }
        }

        if (!var13) {
            this.setPosition(var7, var9, var11);
            return false;
        } else {
            short var30 = 128;
            int var18;
            for (var18 = 0; var18 < var30; ++var18) {
                double var19 = var18 / (var30 - 1.0D);
                float var21 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float var22 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float var23 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double var24 = var7 + (this.posX - var7) * var19 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
                double var26 = var9 + (this.posY - var9) * var19 + this.rand.nextDouble() * this.height;
                double var28 = var11 + (this.posZ - var11) * var19 + (this.rand.nextDouble() - 0.5D) * this.width
                        * 2.0D;
                this.worldObj.spawnParticle("portal", var24, var26, var28, var21, var22, var23);
            }

            this.worldObj.playSoundEffect(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
            this.worldObj.playSoundAtEntity(this, "mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void func_145780_a(int xCoord, int yCoord, int zCoord, Block stepBlock) {
        this.worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 1.0F, 1.0F);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && ItemList.ankh.isPresent()) {
            ItemStack var2 = new ItemStack(ItemList.ankh.get());
            this.entityDropItem(var2, 5.0F);
        }
    }
}
