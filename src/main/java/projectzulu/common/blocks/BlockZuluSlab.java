package projectzulu.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import projectzulu.common.ProjectZulu_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockZuluSlab extends BlockWoodSlab {

    public static Block singleSlabID;
    Block baseBlock;

    /** Single Slab Constructor */
    public BlockZuluSlab(Block baseBlock) {
        super(false);
        singleSlabID = this;
        this.baseBlock = baseBlock;
        setLightOpacity(0);
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(Block.soundTypeWood);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
    }

    /** Double Slab Constructor */
    public BlockZuluSlab(Block singleSlab, Block baseBlock) {
        super(true);
        singleSlabID = singleSlab;
        this.baseBlock = baseBlock;
        setLightOpacity(0);
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(Block.soundTypeWood);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
    }

    @Override
    protected ItemStack createStackedBlock(int par1) {
        return new ItemStack(singleSlabID, 2, par1 & 7);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return baseBlock.getIcon(par1, par2 & 7);
    }

    @Override
    public String func_150002_b(int par1) {
        return super.getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return Item.getItemFromBlock(singleSlabID);
    }
}
