package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCoconutSeed extends Item{
	
	public ItemCoconutSeed(int par1,int par2, boolean par3bool){
		super(par1);
		maxStackSize = 12;
		setMaxDamage(5);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
		bFull3D = par3bool;
		this.setIconIndex(par2);
	}
	
    public int getMetadata(int par1){
        return par1;
    }

	
	@SideOnly(Side.CLIENT)
	public String getTextureFile(){
            return DefaultProps.itemSpriteSheet;
    }
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

		int var11 = par3World.getBlockId(par4, par5, par6);
		int var12 = par3World.getBlockMetadata(par4, par5, par6);

		if (BlockList.palmTreeLog.isPresent() && var11 == BlockList.palmTreeLog.get().blockID && !par3World.isAirBlock(par4, par5, par6)){
			if (par7 == 0){
				return false;
			}

			if (par7 == 1){
				return false;
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

			if (par3World.isAirBlock(par4, par5, par6) && BlockList.coconut.isPresent()){
				
                int var13 = Block.blocksList[BlockList.coconut.get().blockID].onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, 0);
                par3World.setBlockAndMetadataWithNotify(par4, par5, par6, BlockList.coconut.get().blockID, var13);

                if (!par2EntityPlayer.capabilities.isCreativeMode){
                    --par1ItemStack.stackSize;
                }
			}
		}

        return true;
	}
}
