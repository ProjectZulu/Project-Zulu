package projectzulu.common.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import projectzulu.common.core.PacketIDs;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.packets.PacketManagerPlaySound;
import projectzulu.common.dungeon.packets.PacketManagerMobSpawner;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityLimitedMobSpawner extends TileEntity{
    /** The stored delay before a new spawn. */
    public int delay = -1;
    private int ticksExisted = 0;
    
    private NBTTagCompound debugSavedSetup = new NBTTagCompound();
    
    public boolean isDebugEnabled(){
    	return !(debugSavedSetup == null || debugSavedSetup.hasNoTags());
    }
    
    public void setDebugMode(NBTTagCompound debugSavedSetup){
    	this.debugSavedSetup = debugSavedSetup;
    }
    
    public void loadDebugNBT(){
    	readFromNBT(debugSavedSetup);
    }
    
    /**
     * The string ID of the mobs being spawned from this spawner. Defaults to pig, apparently.
     */
    private String mobID = "Chicken";
    /* List of TileEntityLimitedMobSpawnData*/
    private List<TileEntityLimitedMobSpawnData> spawnList = null;
    public List<TileEntityLimitedMobSpawnData> getSpawnList(){
    	return spawnList;
    }
    public void setSpawnList(List<TileEntityLimitedMobSpawnData> spawnList){
    	this.spawnList = spawnList;
    }
    
    /** The extra NBT data to add to spawned entities */
    private TileEntityLimitedMobSpawnData spawnerTags = null;
    public double yaw;
    public double yaw2 = 0.0D;
    private int minSpawnDelay = 200;
    private int maxSpawnDelay = 800;
    public int getMinSpawnDelay(){
    	return minSpawnDelay;
    }
    public int getMaxSpawnDelay(){
    	return maxSpawnDelay;
    }
    public void setMinMaxSpawnDelay(int minSpawnDelay, int maxSpawnDelay){
    	if(maxSpawnDelay < minSpawnDelay){
    		this.maxSpawnDelay = minSpawnDelay;
    		this.minSpawnDelay = minSpawnDelay;
    	}else{
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
    public int getMaxSpawnableEntities(){
    	return maxSpawnableEntities;
    }
    public void setMaxSpawnableEntities(int maxSpawnableEntities){
    	this.maxSpawnableEntities = maxSpawnableEntities;
    }
    private int spawnedEntities = 0;
    
    /** Maximum number of entities for limiting mob spawning */
    private int maxNearbyEntities = 6;
    public int getMaxNearbyEntities(){
    	return maxNearbyEntities;
    }
    public void setMaxNearbyEntities(int maxNearbyEntities){
    	this.maxNearbyEntities = maxNearbyEntities;
    }
    
    /** Required player range for mob spawning to occur */
    private int requiredPlayerRange = 16;
    public int getRequriedPLayerRange(){
    	return requiredPlayerRange;
    }
    public void setRequiredPlayerRange(int requiredPlayerRange){
    	this.requiredPlayerRange = requiredPlayerRange;
    }
    /** Range for spawning new entities with mob spawners */
    private int spawnRange = 4;

    public TileEntityLimitedMobSpawner(){
        this.delay = 20;
    }

    public String getEntityName(){
        return this.spawnerTags == null ? this.mobID : this.spawnerTags.type;
    }

    public void setMobID(String par1Str){
        this.mobID = par1Str;
    }

    /**
     * Returns true if there is a player in range (using World.getClosestPlayer)
     */
    public boolean anyPlayerInRange(){
    	if(isDebugEnabled()){
            return this.worldObj.getClosestPlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, (double)this.requiredPlayerRange) != null;
    	}else{
        	return this.worldObj.getClosestVulnerablePlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, (double)this.requiredPlayerRange) != null;
    	}
    }

    public boolean isEditable(){
        return this.isEditable;
    }
    
    @SideOnly(Side.CLIENT)
    public void playSpawnSound(String spawnSound){
    	worldObj.playSound(xCoord, yCoord, zCoord, spawnSound, 1.0f, 1.0f, true);
    }
    /**
     * Sets the sign's isEditable flag to the specified parameter.
     */
    @SideOnly(Side.CLIENT)
    public void setEditable(boolean par1){
        this.isEditable = par1;
    }
    
    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity(){    	
    	if(maxSpawnableEntities > 0 && spawnedEntities >= maxSpawnableEntities){
    		worldObj.setBlock(xCoord, yCoord, zCoord, 0);
    	}
    	
        if (this.anyPlayerInRange()){
            double var5;

            if (this.worldObj.isRemote){
                double var1 = (double)((float)this.xCoord + this.worldObj.rand.nextFloat());
                double var3 = (double)((float)this.yCoord + this.worldObj.rand.nextFloat());
                var5 = (double)((float)this.zCoord + this.worldObj.rand.nextFloat());
                this.worldObj.spawnParticle("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
                this.worldObj.spawnParticle("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);

                if (this.delay > 0)
                {
                    --this.delay;
                }

                this.yaw2 = this.yaw;
                this.yaw = (this.yaw + (double)(1000.0F / ((float)this.delay + 200.0F))) % 360.0D;
            }
            else{
                if (this.delay == -1){
                    this.updateDelay();
                }

                if (this.delay > 0){
                    --this.delay;
                    return;
                }

                boolean var12 = false;

                for (int var2 = 0; var2 < this.spawnCount; ++var2){
                    Entity var13 = EntityList.createEntityByName(this.getEntityName(), this.worldObj);

                    if (var13 == null){
                        return;
                    }

                    int var4 = this.worldObj.getEntitiesWithinAABB(var13.getClass(), AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand((double)(this.spawnRange * 2), 4.0D, (double)(this.spawnRange * 2))).size();

                    if (var4 >= this.maxNearbyEntities){
                        this.updateDelay();
                        return;
                    }

                    if (var13 != null){
                        var5 = (double)this.xCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * (double)this.spawnRange;
                        double var7 = (double)(this.yCoord + this.worldObj.rand.nextInt(3) - 1);
                        double var9 = (double)this.zCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * (double)this.spawnRange;
                        EntityLiving var11 = var13 instanceof EntityLiving ? (EntityLiving)var13 : null;
                        this.writeNBTTagsTo(var13);
                        var13.setLocationAndAngles(var5, var7, var9, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
                        if (var11 == null || var11.getCanSpawnHere()){

                            this.worldObj.spawnEntityInWorld(var13);
                            this.worldObj.playAuxSFX(2004, this.xCoord, this.yCoord, this.zCoord, 0);
                        	if(!isDebugEnabled()){
                                spawnedEntities++;
                            }
                            if(spawnerTags != null){
                                PacketManagerPlaySound packetManager = (PacketManagerPlaySound) PacketIDs.playSound.createPacketManager();
                                packetManager.setPacketData(xCoord, yCoord, zCoord, spawnerTags.spawnSound);
                                PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 64, worldObj.getWorldInfo().getDimension(),  packetManager.createPacket());
                            }
                            if (var11 != null){
                                var11.spawnExplosionParticle();
                            }

                            var12 = true;
                        }
                    }
                }

                if (var12){
                    this.updateDelay();
                }
            }

            super.updateEntity();
        }
    }

    public void writeNBTTagsTo(Entity par1Entity){
    	if (this.spawnerTags != null){
    		NBTTagCompound var2 = new NBTTagCompound();
    		par1Entity.addEntityID(var2);
    		Iterator var3 = this.spawnerTags.properties.getTags().iterator();

    		while (var3.hasNext()){
    			NBTBase var4 = (NBTBase)var3.next();
    			var2.setTag(var4.getName(), var4.copy());
    		}

    		try{
    			par1Entity.readFromNBT(var2);
    		}catch(Exception e){
    			if(!debugSavedSetup.hasNoTags()){
    				ProjectZuluLog.info("Attempting to Recover From Debug Mode Backup due to Error %s", e.getMessage());
    				try {
    					loadDebugNBT();
    				} catch (Exception e2) {
    					ProjectZuluLog.info("Exception Occured when Writing DebugNBT. Entity may not work as expected. Use Recreate NBT on entity to repair.");
    					e2.printStackTrace();
    				}
    			}else{
    				ProjectZuluLog.info("Exception occured when writing NBT to Entity. Entity may not work as expected. Use Recreate NBT on entity to repair.");
    				e.printStackTrace();
    			}
    		}
    	}
    	else if (par1Entity instanceof EntityLiving && par1Entity.worldObj != null){
    		((EntityLiving)par1Entity).initCreature();
    	}
    }

    /**
     * Sets the delay before a new spawn (base delay of 200 + random number up to 600).
     */
    private void updateDelay(){
        if (this.maxSpawnDelay <= this.minSpawnDelay){
            this.delay = this.minSpawnDelay;
        }
        else{
            this.delay = this.minSpawnDelay + this.worldObj.rand.nextInt(this.maxSpawnDelay - this.minSpawnDelay);
        }

        if (this.spawnList != null && this.spawnList.size() > 0){
            this.spawnerTags = (TileEntityLimitedMobSpawnData)WeightedRandom.getRandomItem(this.worldObj.rand, this.spawnList);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }

        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, 0);
    }
    
    /**
     * Used to Force TileEntity to Repick its Spawnable Creature after being Synced to remove Old Data
     */
    public void forceUpdate(){
    	updateDelay();
    }
    
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound){
    	super.readFromNBT(par1NBTTagCompound);
    	this.mobID = par1NBTTagCompound.getString("EntityId");
    	this.delay = par1NBTTagCompound.getShort("Delay");
    	if(par1NBTTagCompound.hasKey("SpawnPotentials")){
    		this.spawnList = new ArrayList();
    		NBTTagList var2 = par1NBTTagCompound.getTagList("SpawnPotentials");

    		for (int var3 = 0; var3 < var2.tagCount(); ++var3){
    			this.spawnList.add(new TileEntityLimitedMobSpawnData(this, (NBTTagCompound)var2.tagAt(var3)));
    		}
    	}else{
    		this.spawnList = null;
    	}
    	if(par1NBTTagCompound.hasKey("SpawnData")){
    		this.spawnerTags = new TileEntityLimitedMobSpawnData(this, par1NBTTagCompound.getCompoundTag("SpawnData"), this.mobID, "");
    	}else{
    		this.spawnerTags = null;
    	}
    	if(par1NBTTagCompound.hasKey("MinSpawnDelay")){
    		this.minSpawnDelay = par1NBTTagCompound.getShort("MinSpawnDelay");
    		this.maxSpawnDelay = par1NBTTagCompound.getShort("MaxSpawnDelay");
    		this.spawnCount = par1NBTTagCompound.getShort("SpawnCount");
    		this.maxSpawnableEntities = par1NBTTagCompound.getShort("MaxSpawnableEntities");
    	}
    	if(par1NBTTagCompound.hasKey("MaxNearbyEntities")){
    		this.maxNearbyEntities = par1NBTTagCompound.getShort("MaxNearbyEntities");
    		this.requiredPlayerRange = par1NBTTagCompound.getShort("RequiredPlayerRange");
    	}
    	if(par1NBTTagCompound.hasKey("SpawnRange")){
    		this.spawnRange = par1NBTTagCompound.getShort("SpawnRange");
    	}
    	if(par1NBTTagCompound.hasKey("DebugSavedSetup")){
    		debugSavedSetup = par1NBTTagCompound.getCompoundTag("DebugSavedSetup");
    	}            
    	if(this.worldObj != null && this.worldObj.isRemote){
    		this.displayEntity = null;
    	}
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound){
            super.writeToNBT(par1NBTTagCompound);
            par1NBTTagCompound.setCompoundTag("DebugSavedSetup", debugSavedSetup);
            par1NBTTagCompound.setString("EntityId", this.getEntityName());
            par1NBTTagCompound.setShort("Delay", (short)this.delay);
            par1NBTTagCompound.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
            par1NBTTagCompound.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
            par1NBTTagCompound.setShort("SpawnCount", (short)this.spawnCount);
            par1NBTTagCompound.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
            par1NBTTagCompound.setShort("RequiredPlayerRange", (short)this.requiredPlayerRange);
            par1NBTTagCompound.setShort("SpawnRange", (short)this.spawnRange);
            par1NBTTagCompound.setShort("MaxSpawnableEntities", (short)this.maxSpawnableEntities);
            
            if (this.spawnerTags != null){
                par1NBTTagCompound.setCompoundTag("SpawnData", (NBTTagCompound)this.spawnerTags.properties.copy());
            }

            if (this.spawnerTags != null || this.spawnList != null && this.spawnList.size() > 0){
                NBTTagList var2 = new NBTTagList();

                if (this.spawnList != null && this.spawnList.size() > 0){
                    Iterator var3 = this.spawnList.iterator();

                    while (var3.hasNext()){
                        TileEntityLimitedMobSpawnData var4 = (TileEntityLimitedMobSpawnData)var3.next();
                        var2.appendTag(var4.getNBT());
                    }
                }
                else{
                    var2.appendTag(this.spawnerTags.getNBT());
                }

                par1NBTTagCompound.setTag("SpawnPotentials", var2);
            }
    }

    /**
     * will create the entity from the internalID the first time it is accessed
     */
    @SideOnly(Side.CLIENT)
    public Entity getMobEntity(){
        if (this.displayEntity == null){
            Entity var1 = EntityList.createEntityByName(this.getEntityName(), (World)null);
            this.writeNBTTagsTo(var1);
            this.displayEntity = var1;
        }
        return this.displayEntity;
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket(){
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
    	super.onDataPacket(net, pkt);
    	NBTTagCompound tag = pkt.customParam1;
    	readFromNBT(tag);
    	
    }
    
    public void syncToServer(){
    	PacketManagerMobSpawner mobSpawnerPacketManager = (PacketManagerMobSpawner) PacketIDs.mobSpawner.createPacketManager();
    	NBTTagCompound tileEntityData = new NBTTagCompound();
    	writeToNBT(tileEntityData);
    	mobSpawnerPacketManager.setPacketData(xCoord, yCoord, zCoord, tileEntityData);
    	Packet mobSpawnerPacket = mobSpawnerPacketManager.createPacket();
    	PacketDispatcher.sendPacketToServer(mobSpawnerPacket);
    }
    
    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public void receiveClientEvent(int par1, int par2){
        if (par1 == 1 && this.worldObj.isRemote){
            this.delay = this.minSpawnDelay;
        }
    }

}
