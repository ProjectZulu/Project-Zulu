package projectzulu.common.mobs.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import cpw.mods.fml.common.Loader;

public class EntityBlackBear extends EntityBear{

	public EntityBlackBear(World par1World) {
		super(par1World);
		setSize(1.0f, 1.35f);
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
	}
	
	/** 
	 * Set Entity Attack Strength
	 * This is overriden by each Entity if deviations from default are desired
	 **/
	@Override
	protected int getAttackStrength(World par1World){
		switch (par1World.difficultySetting) {
		case 0:
			return 3; 
		case 1:
			return 3; 
		case 2:
			return 4; 
		case 3:
			return 6; 
		default:
			return 3;
		}
	}

	@Override
	protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
	    return super.isValidLocation(world, xCoord, yCoord, zCoord) && worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
	}
	
	/**
	 * Used when Entity Initializes to set Max Health
	 */
	@Override
	public int getMaxHealth(){
		return 15;
		}

	    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue() {
        return 2;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }
	
	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID,1,3), 1);
		}
		super.dropRareDrop(par1);
	}
}
