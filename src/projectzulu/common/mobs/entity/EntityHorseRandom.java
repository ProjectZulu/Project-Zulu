package projectzulu.common.mobs.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;

public class EntityHorseRandom extends EntityHorseBase{

	int horseType = -1;
	
	public EntityHorseRandom(World par1World) {
		super(par1World);
		horseType = rand.nextInt(7);
	}
	
	@Override
	protected void entityInit(){
		super.entityInit();
		/* Horse Type */
		this.dataWatcher.addObject(26, Short.valueOf((short) 0));
	}	
	
	public void updateHorseType(){
		this.dataWatcher.updateObject(26, (short)(horseType));
	}

	public int getHorseType(){
		return this.dataWatcher.getWatchableObjectShort(26);
    }

    @Override
    public void onUpdate() {
        horseType = getHorseType();
        if (!this.isDead) {
            List<EntityHorseBase> horses = new ArrayList<EntityHorseBase>();
            if (CustomEntityList.HORSEBEIGE.modData.isPresent()) {
                horses.add(new EntityHorseBeige(worldObj));
            }

            if (CustomEntityList.HORSEBLACK.modData.isPresent()) {
                horses.add(new EntityHorseBlack(worldObj));
            }

            if (CustomEntityList.HORSEBROWN.modData.isPresent()) {
                horses.add(new EntityHorseBrown(worldObj));
            }

            if (CustomEntityList.HORSEDARKBLACK.modData.isPresent()) {
                horses.add(new EntityHorseDarkBlack(worldObj));
            }

            if (CustomEntityList.HORSEDARKBROWN.modData.isPresent()) {
                horses.add(new EntityHorseDarkBrown(worldObj));
            }

            if (CustomEntityList.HORSEGREY.modData.isPresent()) {
                horses.add(new EntityHorseGrey(worldObj));
            }

            if (CustomEntityList.HORSEWHITE.modData.isPresent()) {
                horses.add(new EntityHorseWhite(worldObj));
            }

            EntityHorseBase entityToReplace;
            if (horses.isEmpty()) {
                entityToReplace = null;
            } else if (horseType < horses.size()) {
                entityToReplace = horses.get(horseType);
            } else {
                Collections.shuffle(horses);
                entityToReplace = horses.get(0);
            }

            this.setDead();
            if (entityToReplace != null) {
                entityToReplace.setPositionAndRotation(posX, posY, posZ, rotationYaw, rotationPitch);
                worldObj.spawnEntityInWorld(entityToReplace);
            }
        }
        super.onUpdate();
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		horseType = par1nbtTagCompound.getByte("HorseType");
		updateHorseType();

	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setByte("HorseType", (byte) horseType);
		updateHorseType();

	}
}
