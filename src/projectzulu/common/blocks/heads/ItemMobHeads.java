package projectzulu.common.blocks.heads;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.API.ItemBlockList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMobHeads extends ItemBlock{

	public ItemMobHeads(int par1){
		super(par1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		setMaxDamage(0);
		setHasSubtypes(true);
		maxStackSize = 8;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getTextureFile(){
            return "/mods/blocks_projectzulu.png";
    }
	
	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 == 0){
			return false;
		}
		else if (!par3World.getBlockMaterial(par4, par5, par6).isSolid()){
			return false;
		}
		else
		{
			if (par7 == 1){
				++par5;
			}

			if (par7 == 2){
				--par6;
			}

			if (par7 == 3){
				++par6;
			}

			if (par7 == 4){
				--par4;
			}

			if (par7 == 5){
				++par4;
			}

			if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)){
				return false;
			}
			else if (!ItemBlockList.mobHeads.get().canPlaceBlockAt(par3World, par4, par5, par6)){
				return false;
			}
			else if(ItemBlockList.mobHeads.isPresent()){
				par3World.setBlockAndMetadataWithNotify(par4, par5, par6, ItemBlockList.mobHeads.get().blockID, par7);
				int var11 = 0;

				if (par7 == 1){
					var11 = MathHelper.floor_double((double)(par2EntityPlayer.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
				}

				TileEntity var12 = par3World.getBlockTileEntity(par4, par5, par6);

				if (var12 != null && var12 instanceof TileEntityMobHeads){
					String var13 = "";

					if (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("SkullOwner")){
						var13 = par1ItemStack.getTagCompound().getString("SkullOwner");
					}

					((TileEntityMobHeads)var12).setSkullType(par1ItemStack.getItemDamage(), var13);
					((TileEntityMobHeads)var12).setRotation(var11);
					/*Was Used to Summon wither For Block Skulls*/
//					((BlockSkull)Block.field_82512_cj).func_82529_a(par3World, par4, par5, par6, (TileEntitySkull)var12);
				}

				--par1ItemStack.stackSize;
				return true;
			}else{
				return false;
			}
		}
	}

	@SideOnly(Side.CLIENT)

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List){
		for (int var4 = 0; var4 < 18; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	/**
	 * Returns the metadata of the block which this Item (ItemBlock) can place
	 */
	public int getMetadata(int par1)
	{
		return par1;
	}


	/* for every block, you need a name. it doesn't matter, really. its just so all your blocks wont have the same name. 
	 * delete this part and all your blocks have the same name. */
	@Override
	public String getItemNameIS(ItemStack itemstack)
	{
		String name = "";
		switch(itemstack.getItemDamage())
		{
		case 0:
			name = "base_0";
			break;
		case 1:
			name = "base_1";
			break;
		case 2:
			name = "base_2";
			break;
		case 3:
			name = "base_3";
			break;
		case 4:
			name = "base_4";
			break;
		case 5:
			name = "base_5";
			break;
		case 6:
			name = "base_6";
			break;
		case 7:
			name = "base_7";
			break;
		case 8:
			name = "base_8";
			break;
		case 9:
			name = "base_9";
			break;
		case 10:
			name = "base_10";
			break;
		case 11:
			name = "base_11";
			break;
		case 12:
			name = "base_12";
			break;
		case 13:
			name = "base_13";
			break;
		case 14:
			name = "base_14";
			break;
		case 15:
			name = "base_15";
			break;
		case 16:
			name = "base_16";
			break;
		case 17:
			name = "base_17";
			break;
		default: name = "base_0";
		}
		return itemstack.getItem().getItemName() + "." + name;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int par1) {
		switch (par1) {
		case 0:
			return 144;
		case 1:
			return 134;
		case 2:
			return 128;
		case 3:
			return 129;
		case 4:
			return 130;
		case 5:
			return 131;
		case 6:
			return 132;
		case 7:
			return 133;
		case 8:
			return 136;
		case 9:
			return 137;
		case 10:
			return 138;
		case 11:
			return 139;
		case 12:
			return 140;
		case 13:
			return 135;
		case 14:
			return 141;
		case 15:
			return 142;
		case 16:
			return 143;
		case 17:
			return 145;
		default:
			return super.getIconFromDamage(par1);
		}
	}
	
//	public String getItemNameIS(ItemStack par1ItemStack)
//	{
//		int var2 = par1ItemStack.getItemDamage();
//
//		if (var2 < 0 || var2 >= field_82807_a.length)
//		{
//			var2 = 0;
//		}
//
//		return super.getItemName() + "." + field_82807_a[var2];
//	}
//
//	public String getItemDisplayName(ItemStack par1ItemStack)
//	{
//		return super.getItemDisplayName(par1ItemStack);
////		return par1ItemStack.getItemDamage() == 3 && par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("SkullOwner") ? StatCollector.translateToLocalFormatted("item.skull.player.name", new Object[] {par1ItemStack.getTagCompound().getString("SkullOwner")}): super.getItemDisplayName(par1ItemStack);
//	}
	
	

}
