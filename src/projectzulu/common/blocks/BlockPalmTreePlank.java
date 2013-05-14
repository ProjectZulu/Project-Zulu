package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import projectzulu.common.ProjectZulu_Core;

public class BlockPalmTreePlank extends Block{
	
	public BlockPalmTreePlank(int par1){
        super(par1, Material.wood);
        Block.setBurnProperties(this.blockID, 5, 20);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setHardness(2.0F);
        setResistance(5.0F);
    }
    
    @Override
    public boolean isOpaqueCube() {
    	return true;
    }
}
