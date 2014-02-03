//package projectzulu.experimental;
//
//import java.util.List;
//import java.util.Random;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.material.Material;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.item.ItemStack;
//import projectzulu.common.core.DefaultProps;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//public class BlockHoloPalmTree extends Block{
//
//	public BlockHoloPalmTree(int par1, int par2){
//		super(par1, Material.wood);
//		
//		this.setTickRandomly(true);
//
//		this.setCreativeTab(CreativeTabs.tabBlock);
//	}
//	
//	//TODO Commented
////	public int getIcon(int par1, int par2) 
////	{
////			switch (par2) {
////			case 0:
////				return 17;
////			case 1:
////				return 19;
////			case 2:
////				return 18;
////			default:
////				return 17;
////			}
////	}
//	
//	public int damageDropped(int par1)
//	{
//		return par1;
//	}
//	
//	//This may be neccesary cause otherwise Install will fail on server?
//	@SideOnly(Side.CLIENT)
//    public String getTextureFile()
//    {
//    	return DefaultProps.blockSpriteSheet;
//    }
//    
//    public int idDropped(int par1, Random par2Random, int par3)
//    {
//    	switch(par1)
//    	{
//    	case 0:
//    		return this.blockID;
//    	case 1:
//    		return this.blockID;
//    	default: 
//    		return this.blockID;
//    	}
//    }
//    
//    
//    public int quantityDropped(Random par1Random)
//    {
//        return par1Random.nextInt(2) + 1;
//    }
//    
//    @SideOnly(Side.CLIENT)
//    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
//    {
//        for (int var4 = 0; var4 < 11; ++var4)
//        {
//            par3List.add(new ItemStack(par1, 1, var4));
//        }
//    }
//
//
//    @SideOnly(Side.CLIENT)
//    /**
//     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
//     */
//    public int getRenderBlockPass()
//    {
//        return 0;
//    }
//
//    /**
//     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
//     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
//     */
//    public boolean isOpaqueCube()
//    {
//        return false;
//    }
//
//    /**
//     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
//     */
//    public boolean renderAsNormalBlock()
//    {
//        return false;
//    }
//
//
//}
