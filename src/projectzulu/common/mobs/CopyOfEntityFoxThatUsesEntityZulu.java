package projectzulu.common.mobs;

import net.minecraft.entity.EnumEntitySize;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.ItemBlockList;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.common.Loader;

public class CopyOfEntityFoxThatUsesEntityZulu extends EntityZulu {
	
	public CopyOfEntityFoxThatUsesEntityZulu(World par1World){
		super(par1World);
		this.moveSpeed = 0.4f;
		this.myEntitySize = EnumEntitySize.SIZE_6;
		setSize(1.0f, 1.0f);
		
		/* Declare AI */
		
	}

	@Override
	public String getTexture() {
		return "/mods/fox.png";
	}
	@Override
	public int getMaxHealth(){return 10;}
	@Override
	public int getTotalArmorValue(){return 2;}

	
	
	
	@Override
	public void onUpdate(){

		super.onUpdate();
	}

	@Override
	protected void UpdateMoveSpeedBasedOnState(World par1World) {
		//Sets MoveSpeed Based on State		
		if(entityState == listStates.idle.index || entityState == listStates.looking.index){

			this.moveSpeed = 0.5f;
		}else if(entityState == listStates.attacking.index || entityState == listStates.attackAnim.index || entityState == listStates.attackAnim2.index){

			this.moveSpeed = 0.6f;
		}
	}
	
	
	@Override
	public boolean isValidFollowItem(ItemStack itemStack) {
		if(itemStack != null && itemStack.getItem().shiftedIndex == Item.egg.shiftedIndex){
			return true;
		}else{
			return super.isValidFollowItem(itemStack);
		}
	}
	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		return isValidFollowItem(itemStack);
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere(){
		if(CustomEntityList.fox.get().secondarySpawnRate - rand.nextInt(100) >= 0){
			return super.getCanSpawnHere();
		}else{
			return false;
		}
	}
	
	/**
	 * Returns the item ID for the item the mob drops on death.
	 */	
	protected int getDropItemId()
	{
		if(rand.nextBoolean()){
			return Item.rottenFlesh.shiftedIndex;
		}else{
			return Item.beefRaw.shiftedIndex;
		}
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = this.rand.nextInt(2 + par2);
		int var4;

		if(Loader.isModLoaded(DefaultProps.BlocksModId)){
			if(var3 == 0){
				if(ItemBlockList.furPelt.isPresent()){
					this.dropItem(ItemBlockList.furPelt.get().shiftedIndex, 1);
				}
			}else{
				if(ItemBlockList.scrapMeat.isPresent()){
					this.dropItem(ItemBlockList.scrapMeat.get().shiftedIndex, 1);
				}
			}
		}else{
			this.dropItem(Item.beefRaw.shiftedIndex,1);
		}

	}

}