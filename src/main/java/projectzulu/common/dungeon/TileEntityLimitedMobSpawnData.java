package projectzulu.common.dungeon;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandom;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettingsSpawning;

public class TileEntityLimitedMobSpawnData extends WeightedRandom.Item {
    public final NBTTagCompound properties;
    public final String type;
    public final String spawnSound;
    public final String optionalParameters;
    public final OptionalSettingsSpawning optionalSpawning;

    final TileEntityLimitedMobSpawner tileEntityMobSpawner;

    public TileEntityLimitedMobSpawnData(TileEntityLimitedMobSpawner tileEntityLimitedMobSpawner,
            NBTTagCompound nbtTagCompound) {
        super(nbtTagCompound.getInteger("Weight"));
        this.tileEntityMobSpawner = tileEntityLimitedMobSpawner;
        this.properties = nbtTagCompound.getCompoundTag("Properties");
        this.type = nbtTagCompound.getString("Type");
        this.spawnSound = nbtTagCompound.getString("SpawnSound");
        this.optionalParameters = nbtTagCompound.getString("OptionalParameter");
        optionalSpawning = new OptionalSettingsSpawning(optionalParameters != null ? optionalParameters : "");
    }

    public TileEntityLimitedMobSpawnData(TileEntityLimitedMobSpawner tileEntityLimitedMobSpawner,
            NBTTagCompound properties, String type, String spawnSound, String optionalParameters) {
        super(1);
        this.tileEntityMobSpawner = tileEntityLimitedMobSpawner;
        this.properties = properties;
        this.type = type;
        this.spawnSound = spawnSound;
        this.optionalParameters = optionalParameters != null ? optionalParameters : "";
        optionalSpawning = new OptionalSettingsSpawning(optionalParameters != null ? optionalParameters : "");
    }

    public NBTTagCompound getNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("Properties", this.properties);
        nbt.setString("Type", this.type);
        nbt.setInteger("Weight", this.itemWeight);
        nbt.setString("SpawnSound", this.spawnSound);
        nbt.setString("OptionalParameter", this.optionalParameters);
        return nbt;
    }
}
