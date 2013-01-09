package projectzulu.common.blocks;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemScale extends Item{
	
	public ItemScale(int par1, int par2, boolean par3bool){
		super(par1);
		maxStackSize = 64;
		setMaxDamage(5);
        this.setCreativeTab(ProjectZulu_Blocks.projectZuluCreativeTab);
		bFull3D = par3bool;
		this.setHasSubtypes(true);
		this.setIconIndex(par2);
	}
	
	@SideOnly(Side.CLIENT)
	public String getTextureFile(){
            return DefaultProps.itemSpriteSheet;
    }	
}
