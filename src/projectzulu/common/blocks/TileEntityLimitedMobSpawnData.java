package projectzulu.common.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomItem;

public class TileEntityLimitedMobSpawnData extends WeightedRandomItem {
    public final NBTTagCompound properties;
    public final String type;

    final TileEntityLimitedMobSpawner tileEntityMobSpawner;

    public TileEntityLimitedMobSpawnData(TileEntityLimitedMobSpawner tileEntityLimitedMobSpawner, NBTTagCompound nbtTagCompound){
        super(nbtTagCompound.getInteger("Weight"));
        this.tileEntityMobSpawner = tileEntityLimitedMobSpawner;
        this.properties = nbtTagCompound.getCompoundTag("Properties");
        this.type = nbtTagCompound.getString("Type");
    }

    public TileEntityLimitedMobSpawnData(TileEntityLimitedMobSpawner tileEntityLimitedMobSpawner, NBTTagCompound properties, String type){
        super(1);
        this.tileEntityMobSpawner = tileEntityLimitedMobSpawner;
        this.properties = properties;
        this.type = type;
    }

    public NBTTagCompound getNBT(){
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setCompoundTag("Properties", this.properties);
        nbt.setString("Type", this.type);
        nbt.setInteger("Weight", this.itemWeight);
        return nbt;
    }
}
