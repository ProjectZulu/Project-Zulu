package projectzulu.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import projectzulu.common.ProjectZulu_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockZuluSlab extends BlockWoodSlab{

	public static int singleSlabID;
	Block baseBlock;	
	public BlockZuluSlab(int id, boolean isDouble, Block baseBlock) {
		super(id, isDouble);
		if (!isDouble) singleSlabID = id;
		this.baseBlock = baseBlock;
		setBurnProperties(blockID, 5, 20);
		setLightOpacity(0);
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundWoodFootstep);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
	}	
	
	@Override
	protected ItemStack createStackedBlock(int par1) {
        return new ItemStack(singleSlabID, 2, par1 & 7);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
        return baseBlock.getIcon(par1, par2 & 7);
	}
	
	@Override
	public String getFullSlabName(int par1) {
        return super.getUnlocalizedName();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return singleSlabID;
	}
}
