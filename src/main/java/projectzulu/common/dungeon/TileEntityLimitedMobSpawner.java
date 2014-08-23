package projectzulu.common.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.PZPacket;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.packets.PacketPlaySound;
import projectzulu.common.dungeon.packets.PacketMobSpawner;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettingsSpawning;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityLimitedMobSpawner extends TileEntity {
    /** The stored delay before a new spawn. */
    public int delay = -1;

    private NBTTagCompound debugSavedSetup = new NBTTagCompound();

    public boolean isDebugEnabled() {
        return !(debugSavedSetup == null || debugSavedSetup.hasNoTags());
    }

    public void setDebugMode(NBTTagCompound debugSavedSetup) {
        this.debugSavedSetup = debugSavedSetup;
    }

    public void loadDebugNBT() {
        readFromNBT(debugSavedSetup);
    }

    /**
     * The string ID of the mobs being spawned from this spawner. Defaults to pig, apparently.
     */
    private String mobID = "Chicken";
    /* List of TileEntityLimitedMobSpawnData */
    private List<TileEntityLimitedMobSpawnData> spawnList = null;

    public List<TileEntityLimitedMobSpawnData> getSpawnList() {
        return spawnList;
    }

    public void setSpawnList(List<TileEntityLimitedMobSpawnData> spawnList) {
        this.spawnList = spawnList;
    }

    /** The extra NBT data to add to spawned entities */
    private TileEntityLimitedMobSpawnData spawnerTags = null;
    public double yaw;
    public double yaw2 = 0.0D;
    private int minSpawnDelay = 200;
    private int maxSpawnDelay = 800;

    public int getMinSpawnDelay() {
        return minSpawnDelay;
    }

    public int getMaxSpawnDelay() {
        return maxSpawnDelay;
    }

    public void setMinMaxSpawnDelay(int minSpawnDelay, int maxSpawnDelay) {
        if (maxSpawnDelay < minSpawnDelay) {
            this.maxSpawnDelay = minSpawnDelay;
            this.minSpawnDelay = minSpawnDelay;
        } else {
            this.maxSpawnDelay = maxSpawnDelay;
            this.minSpawnDelay = minSpawnDelay;
        }
    }

    private int spawnCount = 4;
    private Entity displayEntity;

    /** Toggles whether the GUI can edit this TileEntities Property */
    private boolean isEditable = true;

    /** Maximum Entities to Spawn Before MobSpawner should Stop Spawning */
    private int maxSpawnableEntities = 5;

    public int getMaxSpawnableEntities() {
        return maxSpawnableEntities;
    }

    public void setMaxSpawnableEntities(int maxSpawnableEntities) {
        this.maxSpawnableEntities = maxSpawnableEntities;
    }

    private int spawnedEntities = 0;

    /** Maximum number of entities for limiting mob spawning */
    private int maxNearbyEntities = 6;

    public int getMaxNearbyEntities() {
        return maxNearbyEntities;
    }

    public void setMaxNearbyEntities(int maxNearbyEntities) {
        this.maxNearbyEntities = maxNearbyEntities;
    }

    /** Required player range for mob spawning to occur */
    private int requiredPlayerRange = 16;

    public int getRequriedPLayerRange() {
        return requiredPlayerRange;
    }

    public void setRequiredPlayerRange(int requiredPlayerRange) {
        this.requiredPlayerRange = requiredPlayerRange;
    }

    /** Range for spawning new entities with mob spawners */
    private int spawnRangeHorizontal = 4;
    private int spawnRangeVertical = 1;

    public int spawnRangeOffsetX = 0;
    public int spawnRangeOffsetY = 0;
    public int spawnRangeOffsetZ = 0;

    public int getSpawnRangeHorizontal() {
        return spawnRangeHorizontal;
    }

    public int getSpawnRangeVertial() {
        return spawnRangeVertical;
    }

    public void setSpawnRangeHorizontal(int horizontal) {
        if (horizontal < 0) {
            spawnRangeHorizontal = 0;
        } else {
            spawnRangeHorizontal = horizontal;
        }
    }

    public void setSpawnRangeVertical(int vertical) {
        if (vertical < 0) {
            spawnRangeVertical = 0;
        } else {
            spawnRangeVertical = vertical;
        }
    }

    public TileEntityLimitedMobSpawner() {
        this.delay = 20;
    }

    public String getEntityName() {
        return this.spawnerTags == null ? this.mobID : this.spawnerTags.type;
    }

    public void setMobID(String par1Str) {
        this.mobID = par1Str;
    }

    /**
     * Returns true if there is a player in range (using World.getClosestPlayer)
     */
    public boolean anyPlayerInRange() {
        if (isDebugEnabled()) {
            return this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D,
                    this.requiredPlayerRange) != null;
        } else {
            return this.worldObj.getClosestVulnerablePlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D,
                    this.requiredPlayerRange) != null;
        }
    }

    public boolean isEditable() {
        return this.isEditable;
    }

    @SideOnly(Side.CLIENT)
    public void playSpawnSound(String spawnSound) {
        worldObj.playSound(xCoord, yCoord, zCoord, spawnSound, 1.0f, 1.0f, true);
    }

    /**
     * Sets the sign's isEditable flag to the specified parameter.
     */
    @SideOnly(Side.CLIENT)
    public void setEditable(boolean par1) {
        this.isEditable = par1;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    @Override
    public void updateEntity() {
        if (maxSpawnableEntities > 0 && spawnedEntities >= maxSpawnableEntities) {
            worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
        }

        if (this.anyPlayerInRange()) {
            double var5;

            if (this.worldObj.isRemote) {
                double var1 = this.xCoord + this.worldObj.rand.nextFloat();
                double var3 = this.yCoord + this.worldObj.rand.nextFloat();
                var5 = this.zCoord + this.worldObj.rand.nextFloat();
                this.worldObj.spawnParticle("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
                this.worldObj.spawnParticle("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);

                if (this.delay > 0) {
                    --this.delay;
                }

                this.yaw2 = this.yaw;
                this.yaw = (this.yaw + 1000.0F / (this.delay + 200.0F)) % 360.0D;
            } else {
                if (this.delay == -1) {
                    this.updateDelay();
                }

                if (this.delay > 0) {
                    --this.delay;
                    return;
                }

                boolean var12 = false;

                for (int var2 = 0; var2 < this.spawnCount; ++var2) {
                    Entity var13 = EntityList.createEntityByName(this.getEntityName(), this.worldObj);

                    if (var13 == null) {
                        return;
                    }

                    int var4 = this.worldObj.getEntitiesWithinAABB(
                            var13.getClass(),
                            AxisAlignedBB
                                    .getBoundingBox(xCoord + spawnRangeOffsetX, yCoord + spawnRangeOffsetY,
                                            zCoord + spawnRangeOffsetZ, xCoord + spawnRangeOffsetX + 1,
                                            yCoord + spawnRangeOffsetY + 1, zCoord + spawnRangeOffsetZ + 1)
                                    .expand(spawnRangeHorizontal * 2, spawnRangeVertical * 2 + 2,
                                            spawnRangeHorizontal * 2)).size();

                    if (var4 >= this.maxNearbyEntities) {
                        this.updateDelay();
                        return;
                    }

                    if (var13 != null) {
                        var5 = xCoord + spawnRangeOffsetX + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble())
                                * spawnRangeHorizontal;
                        double var7 = yCoord + spawnRangeOffsetY + worldObj.rand.nextInt(spawnRangeVertical * 2 + 1)
                                - spawnRangeVertical / 2f;
                        double var9 = zCoord + spawnRangeOffsetZ
                                + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * spawnRangeHorizontal;
                        EntityLiving var11 = var13 instanceof EntityLiving ? (EntityLiving) var13 : null;
                        this.writeNBTTagsTo(var13);
                        var13.setLocationAndAngles(var5, var7, var9, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);

                        boolean canSpawnHere = false;
                        if (spawnerTags != null && spawnerTags.optionalSpawning.isOptionalEnabled()) {
                            canSpawnHere = optionalCanSpawnHere(var11, spawnerTags.optionalSpawning);
                        } else {
                            canSpawnHere = var11.getCanSpawnHere();
                        }

                        if (var11 == null || canSpawnHere) {
                            this.worldObj.spawnEntityInWorld(var13);
                            this.worldObj.playAuxSFX(2004, this.xCoord, this.yCoord, this.zCoord, 0);
                            if (!isDebugEnabled()) {
                                spawnedEntities++;
                            }
                            if (spawnerTags != null) {
                                PZPacket packet = new PacketPlaySound().setPacketData(xCoord, yCoord, zCoord,
                                        spawnerTags.spawnSound);
                                ProjectZulu_Core.getPipeline().sendToAllAround(packet,
                                        new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64));
                            }
                            if (var11 != null) {
                                var11.spawnExplosionParticle();
                            }

                            var12 = true;
                        }
                    }
                }

                if (var12) {
                    this.updateDelay();
                }
            }

            super.updateEntity();
        }
    }

    private boolean optionalCanSpawnHere(EntityLiving entity, OptionalSettingsSpawning optionalSpawning) {
        boolean canSpawn = optionalSpawning.isOptionalEnabled() ? !optionalSpawning.isInverted() : false;
        int xCoord = MathHelper.floor_double(entity.posX);
        int yCoord = MathHelper.floor_double(entity.boundingBox.minY);
        int zCoord = MathHelper.floor_double(entity.posZ);
        if (!optionalSpawning.isValidLocation(entity.worldObj, entity, xCoord, yCoord, zCoord)) {
            canSpawn = optionalSpawning.isInverted();
        }
        return canSpawn && entity.worldObj.checkNoEntityCollision(entity.boundingBox)
                && entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty();
    }

    public void writeNBTTagsTo(Entity par1Entity) {
        if (this.spawnerTags != null) {
            NBTTagCompound var2 = new NBTTagCompound();
            par1Entity.writeToNBTOptional(var2);
            Iterator keyIterator = spawnerTags.properties.func_150296_c().iterator();
            while (keyIterator.hasNext()) {
                String key = (String) keyIterator.next();
                NBTBase nbt = spawnerTags.properties.getTag(key);
                var2.setTag(key, nbt.copy());
            }

            try {
                par1Entity.readFromNBT(var2);
            } catch (Exception e) {
                if (!debugSavedSetup.hasNoTags()) {
                    ProjectZuluLog.warning("Attempting to Recover From Debug Mode Backup due to Error %s",
                            e.getMessage());
                    try {
                        loadDebugNBT();
                    } catch (Exception e2) {
                        ProjectZuluLog
                                .severe("Exception Occured when Writing DebugNBT to %s. Entity may not work as expected. Recreate NBT on entity can repair.",
                                        EntityList.getEntityString(par1Entity));
                        e2.printStackTrace();
                    }
                } else {
                    ProjectZuluLog
                            .severe("Exception occured when writing NBT to Entity %s. Entity may not work as expected. Recreate NBT on entity can repair.",
                                    EntityList.getEntityString(par1Entity));
                    e.printStackTrace();
                }
            }
        } else if (par1Entity instanceof EntityLiving && par1Entity.worldObj != null) {
            IEntityLivingData livingData = null;
            // livingData = ((EntityLiving) par1Entity).onSpawnWithEgg(livingData);
        }
    }

    /**
     * Sets the delay before a new spawn (base delay of 200 + random number up to 600).
     */
    private void updateDelay() {
        if (this.maxSpawnDelay <= this.minSpawnDelay) {
            this.delay = this.minSpawnDelay;
        } else {
            this.delay = this.minSpawnDelay + this.worldObj.rand.nextInt(this.maxSpawnDelay - this.minSpawnDelay);
        }

        if (this.spawnList != null && this.spawnList.size() > 0) {
            this.spawnerTags = (TileEntityLimitedMobSpawnData) WeightedRandom.getRandomItem(this.worldObj.rand,
                    this.spawnList);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }

        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, 0);
    }

    /**
     * Used to Force TileEntity to Repick its Spawnable Creature after being Synced to remove Old Data
     */
    public void forceUpdate() {
        updateDelay();
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.mobID = par1NBTTagCompound.getString("EntityId");
        this.delay = par1NBTTagCompound.getShort("Delay");
        if (par1NBTTagCompound.hasKey("SpawnPotentials")) {
            this.spawnList = new ArrayList<TileEntityLimitedMobSpawnData>();
            NBTTagList var2 = par1NBTTagCompound.getTagList("SpawnPotentials", 10);

            for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
                TileEntityLimitedMobSpawnData spawnData = new TileEntityLimitedMobSpawnData(this,
                        var2.getCompoundTagAt(var3));
                if (EntityList.stringToClassMapping.containsKey(spawnData.type)) {
                    this.spawnList.add(spawnData);
                } else {
                    ProjectZuluLog
                            .severe("Failed to load Limited Spawner entity %s at (%s, %s, %s). Entity does not appear to be declared.",
                                    spawnData.type, xCoord, yCoord, zCoord);
                }
            }
        } else {
            this.spawnList = null;
        }
        if (par1NBTTagCompound.hasKey("SpawnData")) {
            this.spawnerTags = new TileEntityLimitedMobSpawnData(this, par1NBTTagCompound.getCompoundTag("SpawnData"),
                    this.mobID, "", "");
            if (!EntityList.classToStringMapping.containsKey(spawnerTags.type)) {
                ProjectZuluLog
                        .severe("Failed to load Limited Spawner entity %s at (%s, %s, %s). Entity does not appear to be declared.",
                                spawnerTags.type, xCoord, yCoord, zCoord);
            }
            spawnerTags = null;
        } else {
            this.spawnerTags = null;
        }
        if (par1NBTTagCompound.hasKey("MinSpawnDelay")) {
            this.minSpawnDelay = par1NBTTagCompound.getShort("MinSpawnDelay");
            this.maxSpawnDelay = par1NBTTagCompound.getShort("MaxSpawnDelay");
            this.spawnCount = par1NBTTagCompound.getShort("SpawnCount");
            this.maxSpawnableEntities = par1NBTTagCompound.getShort("MaxSpawnableEntities");
        }
        if (par1NBTTagCompound.hasKey("MaxNearbyEntities")) {
            this.maxNearbyEntities = par1NBTTagCompound.getShort("MaxNearbyEntities");
            this.requiredPlayerRange = par1NBTTagCompound.getShort("RequiredPlayerRange");
        }

        if (par1NBTTagCompound.hasKey("SpawnRangeHori")) {
            this.spawnRangeHorizontal = par1NBTTagCompound.getShort("SpawnRangeHori");
        } else if (par1NBTTagCompound.hasKey("SpawnRange")) {
            this.spawnRangeHorizontal = par1NBTTagCompound.getShort("SpawnRange");
        }

        if (par1NBTTagCompound.hasKey("SpawnRangeVert")) {
            this.spawnRangeVertical = par1NBTTagCompound.getShort("SpawnRangeVert");
        }

        if (par1NBTTagCompound.hasKey("DebugSavedSetup")) {
            debugSavedSetup = par1NBTTagCompound.getCompoundTag("DebugSavedSetup");
        }

        if (par1NBTTagCompound.hasKey("OffsetX")) {
            spawnRangeOffsetX = par1NBTTagCompound.getShort("OffsetX");
        }

        if (par1NBTTagCompound.hasKey("OffsetY")) {
            spawnRangeOffsetY = par1NBTTagCompound.getShort("OffsetY");
        }

        if (par1NBTTagCompound.hasKey("OffsetZ")) {
            spawnRangeOffsetZ = par1NBTTagCompound.getShort("OffsetZ");
        }

        if (this.worldObj != null && this.worldObj.isRemote) {
            this.displayEntity = null;
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setTag("DebugSavedSetup", debugSavedSetup);
        par1NBTTagCompound.setString("EntityId", this.getEntityName());
        par1NBTTagCompound.setShort("Delay", (short) this.delay);
        par1NBTTagCompound.setShort("MinSpawnDelay", (short) this.minSpawnDelay);
        par1NBTTagCompound.setShort("MaxSpawnDelay", (short) this.maxSpawnDelay);
        par1NBTTagCompound.setShort("SpawnCount", (short) this.spawnCount);
        par1NBTTagCompound.setShort("MaxNearbyEntities", (short) this.maxNearbyEntities);
        par1NBTTagCompound.setShort("RequiredPlayerRange", (short) this.requiredPlayerRange);
        par1NBTTagCompound.setShort("SpawnRangeHori", (short) this.spawnRangeHorizontal);
        par1NBTTagCompound.setShort("SpawnRangeVert", (short) this.spawnRangeVertical);
        par1NBTTagCompound.setShort("MaxSpawnableEntities", (short) this.maxSpawnableEntities);
        par1NBTTagCompound.setShort("OffsetX", (short) this.spawnRangeOffsetX);
        par1NBTTagCompound.setShort("OffsetY", (short) this.spawnRangeOffsetY);
        par1NBTTagCompound.setShort("OffsetZ", (short) this.spawnRangeOffsetZ);

        if (this.spawnerTags != null) {
            par1NBTTagCompound.setTag("SpawnData", (NBTTagCompound) this.spawnerTags.properties.copy());
        }

        if (this.spawnerTags != null || this.spawnList != null && this.spawnList.size() > 0) {
            NBTTagList var2 = new NBTTagList();

            if (this.spawnList != null && this.spawnList.size() > 0) {
                Iterator var3 = this.spawnList.iterator();

                while (var3.hasNext()) {
                    TileEntityLimitedMobSpawnData var4 = (TileEntityLimitedMobSpawnData) var3.next();
                    var2.appendTag(var4.getNBT());
                }
            } else {
                var2.appendTag(this.spawnerTags.getNBT());
            }

            par1NBTTagCompound.setTag("SpawnPotentials", var2);
        }
    }

    /**
     * will create the entity from the internalID the first time it is accessed
     */
    @SideOnly(Side.CLIENT)
    public Entity getMobEntity() {
        if (this.displayEntity == null) {
            Entity var1 = EntityList.createEntityByName(this.getEntityName(), Minecraft.getMinecraft().theWorld);
            this.writeNBTTagsTo(var1);
            this.displayEntity = var1;
        }
        return this.displayEntity;
    }

    /**
     * Overriden in a sign to provide the text.
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, var1);

    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        NBTTagCompound tag = pkt.func_148857_g();
        readFromNBT(tag);
    }

    public void syncToServer() {
        NBTTagCompound tileEntityData = new NBTTagCompound();
        writeToNBT(tileEntityData);
        PZPacket packet = new PacketMobSpawner().setPacketData(xCoord, yCoord, zCoord, tileEntityData);
        ProjectZulu_Core.getPipeline().sendToServer(packet);
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1 && this.worldObj.isRemote) {
            this.delay = this.minSpawnDelay;
            return true;
        } else {
            return super.receiveClientEvent(par1, par2);
        }
    }
}
