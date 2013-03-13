package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import projectzulu.common.ProjectZulu_Core;

public class BlockPalmTreePlank extends Block{
	
	public BlockPalmTreePlank(int par1, int par2){
        super(par1, Material.wood);
        Block.setBurnProperties(this.blockID, 5, 20);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
    }
    
    @Override
    public boolean isOpaqueCube() {
    	return true;
    }
}
