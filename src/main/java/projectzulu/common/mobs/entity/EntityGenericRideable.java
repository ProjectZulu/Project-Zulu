package projectzulu.common.mobs.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Contains Breeding Specific Code. Toggled on by returning true from isRideable() function
 */
public abstract class EntityGenericRideable extends EntityGenericBreedable{

	public EntityGenericRideable(World par1World) {
		super(par1World);
	}
	/* Toggles Whether the Child Class can be Ridden and used methods in this Class
	 * i.e. If false, will not be able to be saddled/mounted */
	public boolean isRideable(){
		return false;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		
		/* Handles Whether the Entity Is Saddled */
		this.dataWatcher.addObject(21, Byte.valueOf((byte)0));
	}
	
	/**
     * Returns true if the Entity is saddled.
     */
    public boolean getSaddled() {
        return (this.dataWatcher.getWatchableObjectByte(21) & 1) != 0;
    }
    
    /**
     * Set or remove the saddle of the Entity.
     */
    public void setSaddled(boolean par1) {
        if (par1) {
            this.dataWatcher.updateObject(21, Byte.valueOf((byte)1));
        }
        else {
            this.dataWatcher.updateObject(21, Byte.valueOf((byte)0));
        }
    }
    
	 /**
     * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
     * by a player and the player is holding a carrot-on-a-stick
     */
    @Override
    public boolean canBeSteered() {
        ItemStack var1 = ((EntityPlayer) this.riddenByEntity).getHeldItem();
        return var1 != null && var1.getItem().equals(Items.stick);
    }
    
    /**
     * Checks if Item should Cause the Horse to Continue Moving in the Direction it was already going
     */
    public boolean shouldIgnorePlayerRot(){
    	if(riddenByEntity != null && riddenByEntity instanceof EntityPlayer){
            ItemStack var1 = ((EntityPlayer)this.riddenByEntity).getHeldItem();
            return var1 != null && var1.getItem() != Items.stick;
    	}
    	return false;
    }
    
    /**
     * Sets the Player Rotation to Face the Direction of the Entity
     */
    @Override
    public boolean shouldRiderFaceForward(EntityPlayer player) {
    	return true;
    }
    
    @Override
    public boolean interact(EntityPlayer par1EntityPlayer) {
    	 if (super.interact(par1EntityPlayer)) {
             return true;
         }
    	 
    	 if(isRideable()){
    		 /* If Saddled Get on/off Entity */
    		 if (this.getSaddled() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer)) {
    			 par1EntityPlayer.mountEntity(this);
    			 return true;

    			 /* If Not Saddled: Try to Saddle Entity */
    		 }else if(!getSaddled() && par1EntityPlayer.inventory.getCurrentItem() != null 
    				 && par1EntityPlayer.inventory.getCurrentItem().getItem() == Items.saddle  ){
    			 setSaddled(true);
    			 return true;
    		 }
    	 }
		 return false;
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound){
    	super.writeEntityToNBT(par1NBTTagCompound);
    	par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound){
    	super.readEntityFromNBT(par1NBTTagCompound);
    	this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
    }
}
