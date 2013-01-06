package projectzulu.common.blocks;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import projectzulu.common.ProjectZulu_Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGenerics extends Item{
	String[] itemNames = {"Poison Droplet", "Tusk","Raw Fiber"};
	
	public ItemGenerics(int par1, int par2) {
		super(par1);
		this.setIconIndex(par2);
        this.setCreativeTab(ProjectZulu_Blocks.projectZuluCreativeTab);
		setHasSubtypes(true);
	}
	
	
	
	@Override
	public String getPotionEffect() {
		// TODO Auto-generated method stub
		return super.getPotionEffect();
	}
	
	@SideOnly(Side.CLIENT)
	public String getTextureFile()
    {
            return "/mods/items_projectzulu.png";
    }
	
	@Override
	public int getIconFromDamage(int par1) {
		switch (par1) {
		case 0:
			return 10;
		case 1:
			return 12;
		case 2:
			return 24;
		default:
			return super.getIconFromDamage(par1);
		}
	}
	
	@Override
	public String getItemNameIS(ItemStack par1ItemStack) {
		try{
			return itemNames[par1ItemStack.getItemDamage()];
		}catch(Exception e){
			return "";
		}
	}
	
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		for (int i = 0; i < itemNames.length; i++) {
			par3List.add(new ItemStack(par1,1,i));
		}
	}
}
