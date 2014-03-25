package projectzulu.common.core;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.potion.brewingstands.PotionIngredients.IngredientProperty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGenerics extends Item implements IngredientProperty {

    public enum Properties {
        /* Generic Items */
        PoisonDroplet("Poison Droplet", 0), Tusk("Tusk", 1), RawFiber("Raw Fiber", 2), LizardSpit("lizardspit", 39),

        /* Potion Effect Ingredients */
        BlackLichen("Black Lichen", 20), Pulp("Pulp", 21), Salt("Salt", 22), PlantStalk("Plant Stalk", 26), Antennae(
                "Antennae", 23, true), ShinyBauble("Shiny Bauble", 24, true), Talon("Talon", 25, true), Bark("Bark",
                27, true), SmallHeart("Small Heart", 28, true), LargeHeart("Large Heart", 29, true), Gill("Gill", 30,
                true), Ectoplasm("Ectoplasm", 31), FrogLegs("Frog Legs", 32, true), RabbitsFoot("Rabbits Feet", 33,
                true), PricklyPowder("Prickly Powder", 34, true), PowderSlush("Powder Slush", 35, true), GlowingGoo(
                "Glowing Goo", 36, true), SmallUnhealthyHeart("Small Unhealthy Heart", 37, true), LargeUnhealthyHeart(
                "Large Unhealthy Heart", 38, true);

        public final String displayName;
        public final boolean isIngredient;
        public final int meta;

        public int meta() {
            return meta;
        }

        @SideOnly(Side.CLIENT)
        private IIcon icon;

        Properties(String name, int meta, boolean isIngredient) {
            this.displayName = name;
            this.meta = meta;
            this.isIngredient = isIngredient;
        }

        Properties(String name, int iconIndex) {
            this(name, iconIndex, false);
        }

        public void setIcon(IIcon icon) {
            this.icon = icon;
        }

        public IIcon getIcon() {
            return icon;
        }

        public static Properties getPropertyByMeta(int meta) {
            for (Properties property : Properties.values()) {
                if (property.meta == meta) {
                    return property;
                }
            }
            return null;
        }
    }

    public ItemGenerics() {
        super();
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return Properties.getPropertyByMeta(par1).getIcon();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        for (Properties type : Properties.values()) {
            type.setIcon(par1IconRegister.registerIcon(DefaultProps.blockKey + ":" + type.toString().toLowerCase()));
        }
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        Properties.getPropertyByMeta(ingredient.getItemDamage());
        return true;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        for (final Properties property : Properties.values()) {
            if (property.meta == itemStack.getItemDamage()) {
                return "item.".concat(property.displayName.toLowerCase().replaceAll("\\s", ""));
            }
        }
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List par3List) {
        for (final Properties property : Properties.values()) {
            par3List.add(new ItemStack(item, 1, property.meta));
        }
    }
}