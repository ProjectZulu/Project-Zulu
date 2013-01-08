package projectzulu.common.blocks;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCoconutItem extends Item{
	
//	private String[] names = new String[]{"wholecoconut", "milkcoconut", "coconutseed"};

	
	public ItemCoconutItem(int par1, int par2, boolean par3bool){
		super(par1);
		/**
		 * Extra Potions, bit10
		 */
		/*Waterbreathing setPotionEffect*/
//		setPotionEffect("-0-1+2-3+10&8-8+9"); // Tier 2 Waterbreathing
//		setPotionEffect("-0-1+2-3+10&4-4"); // Tier 1 Waterbreathing

		/*Curse setPotionEffect*/
//		setPotionEffect("-0+1+2+3+10&8-8+9"); // Tier 2 Curse
//		setPotionEffect("-0+1+2+3+10&4-4"); // Tier 1 Curse
		/*Cleansing setPotionEffect*/
//		setPotionEffect("0+1+2+3+10&8-8+9"); // Tier 2 Cleansing
//		setPotionEffect("0+1+2+3+10&4-4"); // Tier 1 Cleansing
		
		/*DigSlow setPotionEffect*/
//		setPotionEffect("-0+1+2-3+10&8-8+9"); // Tier 2 DigSlow
//		setPotionEffect("-0+1+2-3+10&4-4"); // Tier 1 DigSlow
		/*DigSpeed setPotionEffect*/
//		setPotionEffect("0+1+2-3+10&8-8+9"); // Tier 2 DigSpeed
//		setPotionEffect("0+1+2-3+10&4-4"); // Tier 1 DigSpeed
		
		/*Slowfall setPotionEffect*/
//		setPotionEffect("-0+1-2-3+10&8-8+9"); // Tier 2 Slowfall
//		setPotionEffect("-0+1-2-3+10&4-4"); // Tier 1 Slowfall
		/*Jump setPotionEffect*/
//		setPotionEffect("0+1-2-3+10&8-8+9"); // Tier 2 Jump
//		setPotionEffect("0+1-2-3+10&4-4"); // Tier 1 Jump
		
		/* Protection setPotionEffect */
//		setPotionEffect("-0-1+2+3+10&8-8+9"); // Tier 2 Protection
//		setPotionEffect("-0-1+2+3+10&4-4"); // Tier 1 Protection
		/* Thorn setPotionEffect */
//		setPotionEffect("0-1+2+3+10&8-8+9"); // Tier 2 Thorn
//		setPotionEffect("0-1+2+3+10&4-4"); // Tier 1 Thorn
		

		
		/**
		 * Tier 2 Potions
		 */
		/*NightVision setPotionEffect*/
//		setPotionEffect("-0+1+2-3&8-8+9+13");
		/*Fire Resistance setPotionEffect*/
//		setPotionEffect("0+1-2-3&8-8+9+13");
		/*Swiftness setPotionEffect*/
//		setPotionEffect("-0+1-2-3&8-8+9+13");
		/*Heal setPotionEffect*/
//		setPotionEffect("0-1+2-3&8-8+9+13");
		/*Poison setPotionEffect*/
//		setPotionEffect("-0-1+2-3&8-8+9+13");
		/*Regeneration setPotionEffect*/
//		setPotionEffect("+0-1-2-3&8-8+9+13");
		/*Strength setPotionEffect*/
//		setPotionEffect("0-1-2+3&8-8+9+13");
		

		/* Bubbly setPotionEffect*/
//		setPotionEffect("+8&4-4");
		
		
		/*Slowness setPotionEffect*/
//		setPotionEffect("-0+1-2+3&8-8+9"); // Should not be used? Get via Corruption Effect
		/**
		 * Tier 2 Van. Ingredient
		 */
		/* Tier 2 Fermented Spider Eye */
//		setPotionEffect("-0+3&9-4");

		maxStackSize = 1;
		setMaxDamage(5);
        this.setCreativeTab(ProjectZulu_Blocks.projectZuluCreativeTab);
		bFull3D = par3bool;
		this.setHasSubtypes(true);
		this.setIconIndex(par2);
	}
	
	@Override
	public boolean isPotionIngredient() {
		return super.isPotionIngredient();
	}
	
	@Override
	public String getPotionEffect() {
		return super.getPotionEffect();
	}
	
	@SideOnly(Side.CLIENT)
	public String getTextureFile()
    {
            return "/mods/items_projectzulu.png";
    }
	    
//    @SideOnly(Side.CLIENT)
//    /**
//     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
//     */
//    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
//    {
//        for (int var4 = 0; var4 <= 2; ++var4)
//        {
//            par3List.add(new ItemStack(par1, 1, var4));
//        }
//    }

	
//	@Override
//	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
//			EntityPlayer par3EntityPlayer) {
//		//int tempMeta = par2World.getIte
//
//		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
//	}
	


}
