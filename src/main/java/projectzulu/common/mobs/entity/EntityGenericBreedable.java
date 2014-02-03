package projectzulu.common.mobs.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Contains Breeding Specific Code. Toggled on by returning true from isValidBreedingItem() function
 */
public abstract class EntityGenericBreedable extends EntityGenericAgeable{

	protected int loveTimer = 0;
	public boolean isInLove(){return loveTimer > 0 ? true: false;}
	public void resetInLove(){ loveTimer = 0; }
	
	/* How long an Entity should be in Love Mode when Fed */
	protected int loveCooldown = 600;
	
	public EntityGenericBreedable(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
	}
	
	@Override
	protected void updateAITasks() {
		super.updateAITasks();
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
    	
    	/* Breeding is all Done Server Side, and only while growingAge is 0
    	 * Thus on Client, we know that if growage is not 0 we are not breeding / in love anymore*/
    	 if (this.getGrowingAge() != 0)  {
             this.loveTimer = 0;
         }
    	 
    	 /* Decrement loveTimer for the purpose of hearts */
    	if(getEntityState() == EntityStates.inLove){
    		loveTimer--;
    		if(loveTimer % 10 == 0){
        		String var1 = "heart";
        		double var2 = this.rand.nextGaussian() * 0.02D;
        		double var4 = this.rand.nextGaussian() * 0.02D;
        		double var6 = this.rand.nextGaussian() * 0.02D;
        		this.worldObj.spawnParticle(var1, 
        				this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 
        				this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), 
        				this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var2, var4, var6);
    		}
    	}
	}
	
	/** 
	 * Checks if the Provided ItemStack is considered an item that should be used for Breeding
	 * This is overriden by each Entity if deviations from default are desired
	 */
	public boolean isValidBreedingItem(ItemStack itemStack){
		return false;
	}
	
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

        if (var2 != null && this.isValidBreedingItem(var2) && this.getGrowingAge() == 0) {
            if (!par1EntityPlayer.capabilities.isCreativeMode) {
                --var2.stackSize;

                if (var2.stackSize <= 0) {
                    par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                }
            }

            this.loveTimer = loveCooldown;

            for (int var3 = 0; var3 < 7; ++var3) {
                double var4 = this.rand.nextGaussian() * 0.02D;
                double var6 = this.rand.nextGaussian() * 0.02D;
                double var8 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("heart", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var4, var6, var8);
            }

            return true;
        }
        else {
            return super.interact(par1EntityPlayer);
        }
	}
	
	/**
	 * Returns true if the mob is currently able to mate with the specified mob.
	 */
	public boolean canMateWith(EntityGenericBreedable targetEntity){
		/* If Passed Entity is Self or Different Type, return False */
		if(targetEntity == this || targetEntity.getClass() != this.getClass()){
			return false;
		}
		/* Otherwise, if Target is in Love, and Self is. Then let them mate */
		if(this.isInLove() && targetEntity.isInLove()){
			return true;
		}
		
		return false;
	}
	
	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal of their Type.
	 */
	public EntityGenericBreedable getBabyAnimalEntity(Entity par1EntityAnimal) {
		Object object = null;
		try {
			Class<?> thisClass = par1EntityAnimal.getClass();
			Constructor<?> ctor= thisClass.getConstructor(World.class);
			try {
				object = ctor.newInstance(new Object[] { this.worldObj });
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return (EntityGenericBreedable) object;
	}

}
