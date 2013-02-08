package projectzulu.common.dungeon;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomItem;

public class TileEntityLimitedMobSpawnData extends WeightedRandomItem {
    public final NBTTagCompound properties;
    public final String type;
    public final String spawnSound;
    
    final TileEntityLimitedMobSpawner tileEntityMobSpawner;

    public TileEntityLimitedMobSpawnData(TileEntityLimitedMobSpawner tileEntityLimitedMobSpawner, NBTTagCompound nbtTagCompound){
        super(nbtTagCompound.getInteger("Weight"));
        this.tileEntityMobSpawner = tileEntityLimitedMobSpawner;
        this.properties = nbtTagCompound.getCompoundTag("Properties");
        this.type = nbtTagCompound.getString("Type");
        this.spawnSound = nbtTagCompound.getString("SpawnSound");
    }

    public TileEntityLimitedMobSpawnData(TileEntityLimitedMobSpawner tileEntityLimitedMobSpawner, NBTTagCompound properties, String type, String spawnSound){
        super(1);
        this.tileEntityMobSpawner = tileEntityLimitedMobSpawner;
        this.properties = properties;
        this.type = type;
        this.spawnSound = spawnSound;
    }

    public NBTTagCompound getNBT(){
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setCompoundTag("Properties", this.properties);
        nbt.setString("Type", this.type);
        nbt.setInteger("Weight", this.itemWeight);
        nbt.setString("SpawnSound", this.spawnSound);
        return nbt;
    }
}
