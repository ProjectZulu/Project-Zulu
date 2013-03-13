package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockNightBloom extends BlockFlower{
	public static final String[] imageSuffix = new String[] {"_0", "_1", "_2", "_3","_4"};
    @SideOnly(Side.CLIENT)
    private Icon[] icons;

	private int tickInterval = 4;
	public int getTickInterval(){
		return tickInterval;
	}

	public BlockNightBloom(int i, int j){
		super(i, Material.plants);
		setTickRandomly(true);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
		this.disableStats();
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
    	return icons[par2];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister){
        this.icons = new Icon[imageSuffix.length];
        for (int i = 0; i < this.icons.length; ++i){
            this.icons[i] = par1IconRegister.func_94245_a(func_94330_A()+imageSuffix[i]);
        }
    }
    
	@Override
    public int getLightValue(IBlockAccess world, int x, int y, int z){
    	if(world.getBlockMetadata(x, y, z) > 1){
    		return 9;
    	}else{
    		return 0;
    	}
    }
    
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		//If Night Time && And is not open (meta != 4) : begin opening
		if(mapTimeTo24000(par1World.getWorldTime()) > 13000 && par1World.getBlockMetadata(par2, par3, par4) != 4){
			
			par1World.setBlockAndMetadataWithNotify( par2, par3, par4, blockID, par1World.getBlockMetadata(par2, par3, par4) + 1, 3);
			
			par1World.scheduleBlockUpdate(par2, par3, par4, blockID, 20);
		}
		
		//If Day Time && And is not closed (meta != 0) : begin opening
		if(mapTimeTo24000(par1World.getWorldTime()) < 13000 && par1World.getBlockMetadata(par2, par3, par4) != 0){
			
			par1World.setBlockAndMetadataWithNotify( par2, par3, par4, blockID, par1World.getBlockMetadata(par2, par3, par4) - 1, 3);

			par1World.scheduleBlockUpdate(par2, par3, par4, blockID, 20);
		}
		super.updateTick(par1World, par2, par3, par4, par5Random);
		
		if(par1World.getBlockMetadata(par2, par3, par4) == 4){
			setLightValue(0.6f);
		}
		if(par1World.getBlockMetadata(par2, par3, par4) == 0){
			setLightValue(0);
		}

		
		par1World.scheduleBlockUpdate(par2, par3, par4, blockID, (20*5)+par5Random.nextInt(20*10));
	}
	
	private Long mapTimeTo24000(Long worldTime){
		Long tempWorldTime = worldTime;
		while(tempWorldTime > 24000){
			tempWorldTime -= 24000;
		}
		if(tempWorldTime <= 0){
			return 0L;
		}
		return tempWorldTime;
	}	
		
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		par1World.scheduleBlockUpdate(par2, par3, par4, blockID, 2);
		super.onBlockAdded(par1World, par2, par3, par4);
	}
	
    public boolean isOpaqueCube(){
            return false;
    }

    public boolean renderAsNormalBlock(){
            return false;
    }

    public int getRenderType(){
            return 1;
    }
        
    protected boolean canThisPlantGrowOnThisBlockID(int i){
            return i == Block.grass.blockID || i == Block.dirt.blockID || i == Block.tilledField.blockID  
            		|| ( BlockList.wateredDirt.isPresent() && i == BlockList.wateredDirt.get().blockID);
    }
}
