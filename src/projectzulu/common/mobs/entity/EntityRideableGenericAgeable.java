package projectzulu.common.mobs.entity;

import net.minecraft.world.World;

/**
 * Class Holds Entity Age Methods
 * such as setting and saving age
 */
public class EntityRideableGenericAgeable extends EntityRideableHerd{

	public EntityRideableGenericAgeable(World par1World) {
		super(par1World);
	}
//
//	@Override
//	public int getMaxHealth() {
//		return 0;
//	}
	
//	@Override
//	protected void entityInit()
//    {
//        super.entityInit();
//        this.dataWatcher.addObject(12, new Integer(0));
//    }
	
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
	@Override
    public void onUpdate()
    {
//        int var1 = this.getGrowingAge();
//
//        if (var1 < 0)
//        {
//            ++var1;
//            this.setGrowingAge(var1);
//        }
//        else if (var1 > 0)
//        {
//            --var1;
//            this.setGrowingAge(var1);
//        }
        super.onUpdate();
    }

    /**
     * The age value may be negative or positive or zero. If it's negative, it get's incremented on each tick, if it's
     * positive, it get's decremented each tick. Don't confuse this with EntityLiving.getAge. With a negative value the
     * Entity is considered a child.
     */
//    public int getGrowingAge()
//    {
//        return this.dataWatcher.getWatchableObjectInt(12);
//    }
//    
//    @Override
//    public boolean isChild() {
//    	return getGrowingAge() < 0;
//    }

//    /**
//     * The age value may be negative or positive or zero. If it's negative, it get's incremented on each tick, if it's
//     * positive, it get's decremented each tick. With a negative value the Entity is considered a child.
//     */
//    public void setGrowingAge(int par1)
//    {
//        this.dataWatcher.updateObject(12, Integer.valueOf(par1));
//    }
//
//    /**
//     * (abstract) Protected helper method to write subclass entity data to NBT.
//     */
//    @Override
//    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.writeEntityToNBT(par1NBTTagCompound);
//        par1NBTTagCompound.setInteger("Age", this.getGrowingAge());
//    }
//
//    /**
//     * (abstract) Protected helper method to read subclass entity data from NBT.
//     */
//    @Override
//    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.readEntityFromNBT(par1NBTTagCompound);
//        this.setGrowingAge(par1NBTTagCompound.getInteger("Age"));
//    }

}
