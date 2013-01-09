package projectzulu.common.mobs;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;

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
		this.dataWatcher.addObject(20, Short.valueOf((short) 0));
	}	
	
	public void updateHorseType(){
		this.dataWatcher.updateObject(20, (short)(horseType));
	}

	public int getHorseType(){
		return this.dataWatcher.getWatchableObjectShort(20);
	}
	
	@Override
	public String getTexture() {
		switch (horseType) {
		case 0:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige.png";
			}
			break;
		case 1:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_black_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_black.png";
			}
			break;
		case 2:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_brown_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_brown.png";
			}
			break;
		case 3:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_black_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_black.png";
			}
			break;
		case 4:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_brown_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_brown.png";
			}
			break;
		case 5:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_grey_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_grey.png";
			}
			break;
		case 6:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_white_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_white.png";
			}
			break;
		default:
			if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige_saddled.png";
			}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige.png";
			}
			break;
		}
		return this.texture;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		boolean wasSuccesful = false;
		
		if (CustomEntityList.horseRandom.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere() 
				&& worldObj.getClosestPlayerToEntity(this, 32) == null && this.worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1
				&& worldObj.canBlockSeeTheSky(var1, var2, var3) ){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.horseRandom.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}
	
	@Override
	public void onUpdate() {
		horseType = getHorseType();
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
	
	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(2 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.horseRandom.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}
}
