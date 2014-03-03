package projectzulu.common.blocks;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.temperature.ITempArmor;

public class ItemZuluArmor extends ItemArmor implements ITempArmor {
    /** Holds the 'base' maxDamage that each armorType have. */
    private static final int[] maxDamageArray = new int[] { 11, 16, 15, 13 };

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
    private final ArmorMaterial material;

    public ItemZuluArmor(ArmorMaterial armorMat, int renderIndex, int armorType, String name) {
        super(armorMat, renderIndex, armorType);
        this.material = armorMat;
        this.armorType = armorType;
        this.renderIndex = renderIndex;
        this.damageReduceAmount = armorMat.getDamageReductionAmount(armorType);
        this.setMaxDamage(armorMat.getDurability(armorType));
        this.maxStackSize = 1;
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setUnlocalizedName(name);
        setTextureName(DefaultProps.blockKey + ":" + name);
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
    static int[] getMaxDamageArray() {
        return maxDamageArray;
    }

    @Override
    public float getAddToPlayTempOnEquip(EntityPlayer player, float playerTemp, float playerLocationTemp) {
        return 0;
    }

    @Override
    public float getAddToLocTempOnEquip(EntityPlayer player, float playerTemp, float playerLocationTemp) {
        if (material.equals(ProjectZulu_Core.desertClothMaterial)) {
            return -0.4f;
        } else if (material.equals(ProjectZulu_Core.scaleMaterial)) {
            return -0.4f;
        } else if (material.equals(ProjectZulu_Core.goldScaleMaterial)) {
            return -0.3f;
        } else if (material.equals(ProjectZulu_Core.ironScaleMaterial)) {
            return -0.3f;
        } else if (material.equals(ProjectZulu_Core.diamondScaleMaterial)) {
            return -0.2f;
        }

        if (material.equals(ProjectZulu_Core.furMaterial)) {
            return 0.3f;
        }

        return 0f;
    }

    @Override
    public float getAddToHeatTransferOnEquip(EntityPlayer player, float playerTemp, float playerLocationTemp,
            float currentHeatRate) {
        return 0;
    }

    @Override
    public boolean getBooleanCauseFastHeatTransferOnEquip(EntityPlayer player, float playerTemp,
            float playerLocationTemp, float currentHeatRate) {
        return false;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return DefaultProps.blockKey + ":armor_sets/" + RenderBiped.bipedArmorFilenamePrefix[renderIndex] + "_"
                + (armorType == 2 ? 2 : 1) + ".png";
    }
}
