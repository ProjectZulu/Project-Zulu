package projectzulu.common.blocks;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.temperature.ITempArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemZuluArmor extends ItemArmor implements ITempArmor, IArmorTextureProvider
{
    /** Holds the 'base' maxDamage that each armorType have. */
    private static final int[] maxDamageArray = new int[] {11, 16, 15, 13};

    /**
     * Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
     */
    public final int armorType;

    /** Holds the amount of damage that the armor reduces at full durability. */
    public final int damageReduceAmount;

    /**
     * Used on RenderPlayer to select the correspondent armor to be rendered on the player: 0 is cloth, 1 is chain, 2 is
     * iron, 3 is diamond and 4 is gold.
     */
    public final int renderIndex;

    /** The EnumArmorMaterial used for this ItemArmor */
    private final EnumArmorMaterial material;

    public ItemZuluArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
    	super(par1, par2EnumArmorMaterial, par3, par4);
        //super(par1);
        this.material = par2EnumArmorMaterial;
        this.armorType = par4;
        this.renderIndex = par3;
        this.damageReduceAmount = par2EnumArmorMaterial.getDamageReductionAmount(par4);
        this.setMaxDamage(par2EnumArmorMaterial.getDurability(par4));
        this.maxStackSize = 1;
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
    }

    //Adds Custom Item png for Icons?
    @SideOnly(Side.CLIENT)
    public String getTextureFile(){
    	return DefaultProps.itemSpriteSheet;
    }
    
    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability() {
        return this.material.getEnchantability();
    }

    /**
     * Returns the 'max damage' factor array for the armor, each piece of armor have a durability factor (that gets
     * multiplied by armor material factor)
     */
    static int[] getMaxDamageArray(){
        return maxDamageArray;
    }

	@Override
	public float getAddToPlayTempOnEquip(EntityPlayer player, float playerTemp,
			float playerLocationTemp) {
		return 0;
	}

	@Override
	public float getAddToLocTempOnEquip(EntityPlayer player, float playerTemp,
			float playerLocationTemp) {
		if( material.equals(ProjectZulu_Core.desertClothMaterial) ){
			return -0.4f;
		}else if( material.equals(ProjectZulu_Core.scaleMaterial) ){
			return -0.4f;
		}else if( material.equals(ProjectZulu_Core.goldScaleMaterial) ){
			return -0.3f;
		}else if( material.equals(ProjectZulu_Core.ironScaleMaterial) ){
			return -0.3f;
		}else if( material.equals(ProjectZulu_Core.diamondScaleMaterial) ){
			return -0.2f;
		}

		if(material.equals(ProjectZulu_Core.furMaterial)){
			return 0.3f;
		}
		
		return 0f;
	}

	@Override
	public float getAddToHeatTransferOnEquip(EntityPlayer player,
			float playerTemp, float playerLocationTemp, float currentHeatRate) {
		return 0;
	}

	@Override
	public boolean getBooleanCauseFastHeatTransferOnEquip(EntityPlayer player,
			float playerTemp, float playerLocationTemp, float currentHeatRate) {
		return false;
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		return DefaultProps.blockDiretory + "armor_sets/" + RenderPlayer.armorFilenamePrefix[renderIndex] + "_" + (armorType == 2 ? 2 : 1) + ".png";
	}
}
