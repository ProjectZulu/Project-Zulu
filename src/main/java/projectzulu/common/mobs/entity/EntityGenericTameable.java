package projectzulu.common.mobs.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.PZPacket;
import projectzulu.common.core.packets.PacketTameParticle;

/**
 * Contains Breeding Specific Code. Toggled on by returning true from isValidTamingItem() function
 */
public abstract class EntityGenericTameable extends EntityGenericRideable{
	boolean shouldFollow = false;
	boolean shouldAttack = false;

	public EntityGenericTameable(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		/* Handles the Taming State 0 == Wild, 
		 *  Bit 3 handles if Tamed (&4), 
		 *  Bit 1 handles if Sitting (&1), 
		 */
        this.dataWatcher.addObject(22, Byte.valueOf((byte)0));
        
		/* Handles the Owners name */
        this.dataWatcher.addObject(23, "");
        
		/* Handles the Entity's name */
        this.dataWatcher.addObject(24, "");
        
//		/* Handles the Entity's Health */
//        this.dataWatcher.addObject(25, Short.valueOf((short) getMaxHealth()));

	}
	
	public String getUsername() {
        return this.dataWatcher.getWatchableObjectString(24);
	}
	
	public void setUsername(String username) {
        this.dataWatcher.updateObject(24, username);
	}
	
    public boolean isTamed() {
        return (this.dataWatcher.getWatchableObjectByte(22) & 4) != 0;
    }

    public void setTamed(boolean par1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(22);

        if (par1) {
            this.dataWatcher.updateObject(22, Byte.valueOf((byte)(var2 | 4)));
        }
        else {
            this.dataWatcher.updateObject(22, Byte.valueOf((byte)(var2 & -5)));
        }
    }
    
    @Override
    protected boolean canDespawn() {
        return isTamed() ? false : super.canDespawn();
    }
    
    public boolean isSitting() {
        return (this.dataWatcher.getWatchableObjectByte(22) & 1) != 0;
    }

    public void setSitting(boolean par1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(22);
        if (par1) {
            this.dataWatcher.updateObject(22, Byte.valueOf((byte)(var2 | 1)));
        }
        else {
            this.dataWatcher.updateObject(22, Byte.valueOf((byte)(var2 & -2)));
        }
    }
    
    public String getOwnerName() {
        return this.dataWatcher.getWatchableObjectString(23);
    }

    public void setOwner(String par1Str) {
        this.dataWatcher.updateObject(23, par1Str);
    }

    public EntityLivingBase getOwner() {
        return this.worldObj.getPlayerEntityByName(this.getOwnerName());
    }
    
//    public void updateDWHealth(int health) {
//        this.dataWatcher.updateObject(25, (short)health);
//    }
//
//    public int getDWHealth() {
//        return this.dataWatcher.getWatchableObjectShort(25);
//    }
    /**
     * Play the taming effect, will either be hearts or smoke depending on status
     */
    public void playTameEffect(boolean par1){		
        String var2 = "heart";

        if (!par1){
            var2 = "smoke";
        }

        for (int var3 = 0; var3 < 7; ++var3){
            double var4 = this.rand.nextGaussian() * 0.02D;
            double var6 = this.rand.nextGaussian() * 0.02D;
            double var8 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(var2, 
            		this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, 
            		this.posY + 0.5D + this.rand.nextFloat() * this.height, 
            		this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, var4, var6, var8);
        }
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound){
        super.writeEntityToNBT(par1NBTTagCompound);
        if (this.getOwnerName() == null){
            par1NBTTagCompound.setString("Owner", "");
        }
        else{
            par1NBTTagCompound.setString("Owner", this.getOwnerName());
        }
        
        if (this.getUsername() == null){
            par1NBTTagCompound.setString("Username", "");
        }
        else{
            par1NBTTagCompound.setString("Username", this.getUsername());
        }
        par1NBTTagCompound.setBoolean("Sitting", this.isSitting());
        par1NBTTagCompound.setBoolean("Should Follow", shouldFollow );
        par1NBTTagCompound.setBoolean("Should Attack", shouldAttack );
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound){
        super.readEntityFromNBT(par1NBTTagCompound);
        String owner = par1NBTTagCompound.getString("Owner");
        if (owner.length() > 0){
            this.setOwner(owner);
            this.setTamed(true);
        }

        String username = par1NBTTagCompound.getString("Username");
        if (username.length() > 0){
            this.setUsername(username);
        }
        this.setSitting(par1NBTTagCompound.getBoolean("Sitting"));
        shouldFollow = par1NBTTagCompound.getBoolean("Should Follow");
        shouldAttack = par1NBTTagCompound.getBoolean("Should Attack");
    }
    
    /** 
     * Validates if Itemstack can be used to Tame Entity
     * Caution: ItemStack may be Null
     */
    public boolean isValidTamingItem(ItemStack itemStack){
		return false;
    }
    
    /** 
     * Validates if Itemstack can be used to Heal Entity
     * Usually Considered valid if return > 0
     * Caution: ItemStack may be Null
     * @return Heal amount
      */
    public int getHealingValueIfValid(ItemStack itemStack){    	
		return 0;
    }
    
    @Override
    protected void updateAITasks() {
    	super.updateAITasks();
    }
    
    @Override
    public void onUpdate() {
    	super.onUpdate();
//    	if(!worldObj.isRemote){
//        	updateDWHealth(health);
//    	}
//    	health = getDWHealth();
    }
    
    @Override
    public boolean interact(EntityPlayer par1EntityPlayer) {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
        
    	if(isTamed()){
    		/* TODO: Un Tame? */
    		if (par1EntityPlayer.getCommandSenderName().equalsIgnoreCase(this.getOwnerName())){
    			if(var2 != null){
    				if(var2.getItem() == Items.paper || var2.getItem() == Items.name_tag){
    					par1EntityPlayer.openGui(ProjectZulu_Core.modInstance, 2, par1EntityPlayer.worldObj, getEntityId(), 0, 0);
    					return true;
    				}else if( getHealingValueIfValid(var2) > 0 && getHealth() < getMaxHealth() ){
    					 if (!par1EntityPlayer.capabilities.isCreativeMode) {
                             --var2.stackSize;
                         }
                         this.heal(getHealingValueIfValid(var2) );

                         if (var2.stackSize <= 0) {
                             par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                         }
                         return true;
    				}
    			}
    			
    			/* Toggle Animal Action Sitting --> Follow -- > Attack(Not Implemented) -- > None --> Sitting etc..*/
                if (!isValidBreedingItem(var2)) {
                    if (this.worldObj.isRemote) {
                        return true;
                    }
                    if (getEntityState() == EntityStates.sitting) {
                        setSitting(false);
                        shouldFollow = true;
                    } else if (getEntityState() == EntityStates.following) {
                        shouldFollow = false;
                        shouldAttack = true;
                        setAngerLevel(3);
                    } else if (getEntityState() == EntityStates.attacking || getEntityState() == EntityStates.looking) {
                        shouldAttack = false;
                    } else if (getEntityState() == EntityStates.idle) {
                        setSitting(true);
                        this.isJumping = false;
                    }
                    return true;
                }
            }
    	}else if(var2 != null && isValidTamingItem(var2)) {
    		if (!par1EntityPlayer.capabilities.isCreativeMode){
    			--var2.stackSize;
    		}

    		if (var2.stackSize <= 0){
    			par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
    		}

    		if(!worldObj.isRemote){
    			boolean tameEffectSuccess = false;
    			if(rand.nextInt(3) == 0){
    				this.setTamed(true);
    				this.setAttackTarget((EntityLiving)null);
    				setSitting(true);
                    this.setHealth(getMaxHealth());
    				this.setOwner(par1EntityPlayer.getCommandSenderName());
    				setUsername(getDefaultEntityName());
    				tameEffectSuccess = true;
    			}
    			/* Send Tame Effect Packet */
    			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    			DataOutputStream data = new DataOutputStream(bytes);
    			/* Write PacketID into Packet */
    			try {
    				data.writeInt(5);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}

    			/* Write Temperature Into Packet */
                try {
                    data.writeInt(getEntityId());
                    data.writeBoolean(tameEffectSuccess);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PZPacket packet = new PacketTameParticle().setPacketData(getEntityId(), tameEffectSuccess);
                ProjectZulu_Core.getPipeline()
                        .sendToAllAround(packet, new TargetPoint(dimension, posX, posY, posZ, 10));
            }
            return true;
        }
        
    	if(super.interact(par1EntityPlayer)){
    		return true;
    	}
    	
    	return false;
    }
    
    
    public String getDefaultEntityName() {
        return getCommandSenderName();
    }
}
