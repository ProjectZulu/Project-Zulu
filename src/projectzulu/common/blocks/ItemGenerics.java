package projectzulu.common.blocks;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import projectzulu.common.ProjectZulu_Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGenerics extends Item{
	
	public enum Properties{
		/* Generic Items */
		PoisonDroplet("Poison Droplet", 0, 10),
		Tusk("Tusk", 1, 12),
		RawFiber("Raw Fiber", 2, 24),
		
		/* Potion Effect Ingredients */
		BlackLichen("Black Lichen", 20, 240, "0-1+2+3+10&4-4", "0-1+2+3+10&8-8+9"), // Cleanse
		Pulp("Pulp", 21, 241),
		Salt("Salt", 22, 242),
		Antennae("Antennae", 23, 243, "-0+1+2+3+10&4-4", "-0+1+2+3+10&8-8+9"), // Blindness
		ShinyBauble("Shiny Bauble", 24, 244, "+8&4-4"),
		Talon("Talon", 25, 245, "+0-1-2+3&4-4+13", "0-1-2+3&8-8+9+13"), // Strength+
		PlantStalk("Plant Stalk", 26, 246), 
		Bark("Bark", 27, 247, "-0-1+2+3+10&4-4", "-0-1+2+3+10&8-8+9"), // Protection
		SmallHeart("Small Heart", 28, 248, "0+1+2-3+10&4-4", "0+1+2-3+10&8-8+9"), //DigSpeed+
		LargeHeart("Large Heart", 29, 249, "+0-1-2+3&4-4+13", "0-1-2+3&8-8+9+13"), // Strength+
		Gill("Gill", 30, 250, "-0-1+2-3+10&4-4", "-0-1+2-3+10&8-8+9"), // Underwater Breathing
		Ectoplasm("Ectoplasm", 31, 251, "0-1+2+3+10&4-4", "0-1+2+3+10&8-8+9"), // Curse
		FrogLegs("Frog Legs", 32, 252, "0+1-2-3+10&4-4", "0+1-2-3+10&8-8+9"), // Jump
		RabbitsFoot("Rabbits Feet", 33, 253, "0+1-2-3+10&4-4", "0+1-2-3+10&8-8+9"); // Jump
		//Salt + Small heart = DigSpeef-
		//Salt + Large heart = Strength-
		//Milk + Pulp  = Cleanse
		//Milk + Pulp + Ectoplasm = Curse
		//Cactus + Pulp = Thorns
		//Cactus + Pulp + Barl/Scale = Protection
		
		String name;
		int meta; public int meta(){ return meta; }
		int iconIndex;
		String defaultPotionEffect;
		String extraPotionEffect;
		Properties(String name, int meta, int iconIndex, String defaultPotionEffect, String extraPotionEffect) {
			this.name = name;
			this.meta = meta;
			this.iconIndex = iconIndex;
			this.defaultPotionEffect = defaultPotionEffect;
			this.extraPotionEffect = extraPotionEffect;
		}
		Properties(String name, int meta, int iconIndex, String defaultPotionEffect) {
			this(name, meta, iconIndex, defaultPotionEffect, null);
		}
		Properties(String name, int meta, int iconIndex) {
			this(name, meta, iconIndex, null, null);
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
		this.setIconIndex(par2);
		this.setCreativeTab(ProjectZulu_Blocks.projectZuluCreativeTab);
		setHasSubtypes(true);
	}
	
	@Override
	public boolean isPotionIngredient() { return false; }
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
		System.out.println("getPotionEffect");
		System.out.println((brewingIndex & 1 << 8) > 0);
		
		for (Properties property : Properties.values()) {
			if(property.meta == ingredientItemStack.getItemDamage()){
				/* If Tier 2 do a Different Potion Effect */
				if( (brewingIndex & 1 << 8) > 0 && property.extraPotionEffect != null){
					System.out.println(property.extraPotionEffect);
					return property.extraPotionEffect;
				}else{
					System.out.println(property.defaultPotionEffect);
					return property.defaultPotionEffect;
				}
			}
		}
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public String getTextureFile(){
		return "/mods/items_projectzulu.png";
    }
	
	@Override
	public String getItemNameIS(ItemStack itemStack){
		for (final Properties property : Properties.values()){
			if(property.meta == itemStack.getItemDamage()){
				return property.name;
			}
		}		
		return "";
	}
	
	@Override
	public int getIconFromDamage(int meta) {
		for (final Properties property : Properties.values()) {
			if(property.meta == meta){
				return property.iconIndex;
			}
		}
		return 0;
	}
	
	@Override
	public void getSubItems(int itemID, CreativeTabs par2CreativeTabs, List par3List) {
		for (final Properties property : Properties.values()) {
			par3List.add(new ItemStack(itemID, 1, property.meta));
		}
	}
}
