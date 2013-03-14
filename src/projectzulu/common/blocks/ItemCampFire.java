package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCampFire extends ItemBlock {

	public ItemCampFire(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	public int getMetadata(int par1){
		return par1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return Block.blocksList[this.itemID].getBlockTextureFromSideAndMetadata(2, par1);
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + BlockCampfire.Type.getTypeByMeta(itemstack.getItemDamage()).displayName();
	}
}
