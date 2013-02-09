package projectzulu.common.blocks;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.world.StructureManager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemStructurePlacer extends Item{

	String[] structureName = new String[]{"Oasis", "Pyramid", "Labyrinth", "Cemetary"};

	public ItemStructurePlacer(int par1) {
		super(par1);
		this.setIconIndex(1);
		setHasSubtypes(true);
		this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);

	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
		if( !Loader.isModLoaded(DefaultProps.WorldModId)){
			return;
		}			

		if(!par2World.isRemote){			
			int buildingID = par1ItemStack.getItemDamage();
			int Xcoord = (int) par3EntityPlayer.posX;
			int Zcoord = (int) par3EntityPlayer.posZ;
			int Ycoord = ((int) par3EntityPlayer.posY)-1;
			switch (buildingID) {
			case 0:
				/* Oasis Generation */
				StructureManager.tryToGenerateSpecificStructure(StructureManager.Oasis, par2World, Xcoord, Ycoord, Zcoord, par2World.rand);
				break;
			case 1:
				/* Pyramid Generation */
				StructureManager.tryToGenerateSpecificStructure(StructureManager.Pyramid, par2World, Xcoord, Ycoord, Zcoord, par2World.rand);
				break;
			case 2:
				/* Labyrinth Generation */
				StructureManager.tryToGenerateSpecificStructure(StructureManager.Labyrinth, par2World, Xcoord, Ycoord, Zcoord, par2World.rand);
				break;
			case 3:
				/* Cemetary Generation */
				StructureManager.tryToGenerateSpecificStructure(StructureManager.Cemetary, par2World, Xcoord, Ycoord, Zcoord, par2World.rand);
				break;
			default:
				break;
			}	
		}
		
		if(!par3EntityPlayer.capabilities.isCreativeMode){
			par1ItemStack.stackSize--;
		}

	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 16;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int par1) {
		return 58;
	}
	
	/**
     * Return an item rarity from EnumRarity
     */
	@Override
    public EnumRarity getRarity(ItemStack par1ItemStack){
        return EnumRarity.rare;
    }
    
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack){
        return EnumAction.bow;
    }
	
	public String getItemNameIS(ItemStack itemstack) {
		try{
			return structureName[itemstack.getItemDamage()];
		}catch(Exception e){
			return "";
		}
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		for (int i = 0; i < structureName.length; i++) {
			par3List.add(new ItemStack(par1,1,i));
		}
	}

}
