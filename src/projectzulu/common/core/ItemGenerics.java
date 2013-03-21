package projectzulu.common.core;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import projectzulu.common.ProjectZulu_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGenerics extends Item{
	
	public enum Properties{
		/* Generic Items */
		PoisonDroplet("Poison Droplet", 0),
		Tusk("Tusk", 1),
		RawFiber("Raw Fiber", 2),
		
		/* Potion Effect Ingredients */
		BlackLichen("Black Lichen", 20),
		Pulp("Pulp", 21),
		Salt("Salt", 22),
		//  "-0+1+2+3&4-4+13", "-0+1+2+3+10&8-8+9+13"
		Antennae("Antennae", 23, "-0+1+2-3&4-4+13", "-0+1+2-3&8-8+9+13"), // Night Vision
		ShinyBauble("Shiny Bauble", 24, "+8&4-4"),
		Talon("Talon", 25, "+0-1-2+3&4-4+13", "0-1-2+3&8-8+9+13"), // Strength+
		PlantStalk("Plant Stalk", 26), 
		Bark("Bark", 27, "-0-1+2+3+10&4-4", "-0-1+2+3+10&8-8+9"), // Resistance
		SmallHeart("Small Heart", 28, "0+1+2-3+10&4-4", "0+1+2-3+10&8-8+9"), // DigSpeed+
		LargeHeart("Large Heart", 29, "+0-1-2+3&4-4+13", "0-1-2+3&8-8+9+13"), // Strength+
		Gill("Gill", 30, "-0-1+2-3+10&4-4", "-0-1+2-3+10&8-8+9"), // Underwater Breathing
		Ectoplasm("Ectoplasm", 31),
		FrogLegs("Frog Legs", 32, "0+1-2-3+10&4-4", "0+1-2-3+10&8-8+9"), // Jump
		RabbitsFoot("Rabbits Feet", 33, "0+1-2-3+10&4-4", "0+1-2-3+10&8-8+9"), // Jump
		
		PricklyPowder("Prickly Powder", 34, "0-1+2+3+10&4-4", "0-1+2+3+10&8-8+9"), // Thorns
		PowderSlush("Powder Slush", 35, "0-1+2+3+10&4-4", "0-1+2+3+10&8-8+9"), // Cleanse
		GlowingGoo("Glowing Goo", 36, "-0+1+2+3+10&4-4", "-0+1+2+3+10&8-8+9"), // Curse
		SmallUnhealthyHeart("Small Unhealthy Heart", 37, "0+1+2-3+10&4-4", "0+1+2-3+10&8-8+9"), // DigSpeed-
		LargeUnhealthyHeart("Large Unhealthy Heart", 38, "+0-1-2+3&4-4+13", "0-1-2+3&8-8+9+13"); // Strength-
		
		private String displayName; public String getDisplayName(){ return displayName; }
		private int meta; public int meta(){ return meta; }
        @SideOnly(Side.CLIENT)
    	private Icon icon;
		private String defaultPotionEffect;
		private String extraPotionEffect;
		
		Properties(String name, int meta, String defaultPotionEffect, String extraPotionEffect) {
			this.displayName = name;
			this.meta = meta;
			this.defaultPotionEffect = defaultPotionEffect;
			this.extraPotionEffect = extraPotionEffect;
		}
		Properties(String name, int iconIndex, String defaultPotionEffect) {
			this(name, iconIndex, defaultPotionEffect, null);
		}
		Properties(String name, int iconIndex) {
			this(name, iconIndex, null, null);
		}
		
		public void setIcon(Icon icon){
			this.icon = icon;
		}
		
		public Icon getIcon(){
			return icon;
		}
		
		public static Properties getPropertyByMeta(int meta){
			for (Properties property : Properties.values()) {
				if(property.meta == meta){
					return property;
				}
			}
			return null;
		}
	}
	
	public ItemGenerics(int par1, int par2) {
		super(par1);
		this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
		setHasSubtypes(true);
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1) {
    	return Properties.getPropertyByMeta(par1).getIcon();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister) {
    	for (Properties type : Properties.values()) {
    		type.setIcon(par1IconRegister.registerIcon(DefaultProps.blockKey+":"+type.toString().toLowerCase()));
    	}   
    }
	
	@Override
	public boolean isPotionIngredient() { return true; }
	@Override
	public String getPotionEffect() { return ""; }
	
	public boolean isPotionIngredient(int brewingIndex, ItemStack ingredientItemStack) {
		if(getPotionEffect(brewingIndex, ingredientItemStack) != null){
			return true;
		}
		return false;
	}
	
	/**
	*					0123 4567 8901 2345
	* Craft Pois: 1028  0010 0000 0010 0101
	* Creat Pois: 15796 0010 1101 1011 11//
	*           : 65535 1111 1111 1111 1111
	*/
	public String getPotionEffect(int brewingIndex, ItemStack ingredientItemStack) {				
		for (Properties property : Properties.values()) {
			if(property.meta == ingredientItemStack.getItemDamage()){
				/* If Tier 2 do a Different Potion Effect */
				if( (brewingIndex & 1 << 8) > 0 && property.extraPotionEffect != null){
					return property.extraPotionEffect;
				}else{
					return property.defaultPotionEffect;
				}
			}
		}
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public String getTextureFile(){
		return DefaultProps.itemSpriteSheet;
    }
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		for (final Properties property : Properties.values()){
			if(property.meta == itemStack.getItemDamage()){
				return property.displayName;
			}
		}		
		return "";
	}
	
	//TODO: Commented
//	@Override
//	public int getIconFromDamage(int meta) {
//		for (final Properties property : Properties.values()) {
//			if(property.meta == meta){
//				return property.iconIndex;
//			}
//		}
//		return 0;
//	}
	
	@Override
	public void getSubItems(int itemID, CreativeTabs par2CreativeTabs, List par3List) {
		for (final Properties property : Properties.values()) {
			par3List.add(new ItemStack(itemID, 1, property.meta));
		}
	}
}
