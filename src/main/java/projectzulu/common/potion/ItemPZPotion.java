package projectzulu.common.potion;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.potion.subitem.SubItemPotion;
import projectzulu.common.potion.subitem.SubItemPotionRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPZPotion extends ItemPotion {

    @SideOnly(Side.CLIENT)
    private IIcon splashIcon;
    @SideOnly(Side.CLIENT)
    private IIcon regularIcon;
    @SideOnly(Side.CLIENT)
    private IIcon contentIcon;

    public ItemPZPotion(String name) {
        super();
        setCreativeTab(ProjectZulu_Core.projectZuluPotionTab);
        setMaxStackSize(3);
        setUnlocalizedName(DefaultProps.blockKey + ":" + name.toLowerCase());
        setTextureName(DefaultProps.blockKey + ":" + name.toLowerCase());
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return isSplash(par1) ? splashIcon : regularIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
        return par2 == 0 ? contentIcon : getIconFromDamage(par1);
    }

    // TODO: Get Rid Staticness and Check if it can be safely used instead of ItemPotion static isSplash
    public static boolean isSplash(int par0) {
        return PotionParser.isSplash(par0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isEffectInstant(int damageMeta) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(this, PotionParser.readID(damageMeta));
        return potion != null ? potion.isEffectInstant(damageMeta) : false;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(itemStack);
        return potion != null ? potion.getDisplayName(itemStack) : "Unknown Concoction";
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(itemStack);
        return potion != null ? potion.onEaten(itemStack, world, player) : super.onEaten(itemStack, world, player);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(itemStack);
        return potion != null ? potion.onItemRightClick(itemStack, world, player) : super.onItemRightClick(itemStack,
                world, player);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(itemStack);
        if (potion != null) {
            potion.addInformation(itemStack, player, list, par4);
        } else {
            super.addInformation(itemStack, player, list, par4);
        }
    }

    @Override
    public List<PotionEffect> getEffects(int damageMeta) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(this, PotionParser.readID(damageMeta));
        return potion != null ? potion.getPotionEffects(damageMeta) : Collections.<PotionEffect> emptyList();
    }

    @Override
    public List<PotionEffect> getEffects(ItemStack itemStack) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(itemStack);
        return potion != null ? potion.getPotionEffects(itemStack) : Collections.<PotionEffect> emptyList();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(itemStack);
        return potion != null ? potion.hasPotionEffects(itemStack) : false;
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        Collection<SubItemPotion> potion = SubItemPotionRegistry.INSTANCE.getPotions(item);
        for (SubItemPotion subItemPotion : potion) {
            subItemPotion.getSubItems(item, creativeTabs, list);
        }
    }

    public ItemStack getPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(brewingStack);
        return potion != null ? potion.getPotionResult(ingredient, brewingStack) : null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.regularIcon = iconRegister.registerIcon("potion_bottle_drinkable");
        this.splashIcon = iconRegister.registerIcon("potion_bottle_splash");
        this.contentIcon = iconRegister.registerIcon("potion_overlay");
    }
}
