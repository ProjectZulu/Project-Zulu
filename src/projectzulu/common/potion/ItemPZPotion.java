package projectzulu.common.potion;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.potion.subitem.SubItemPotion;
import projectzulu.common.potion.subitem.SubItemPotionRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPZPotion extends ItemPotion {
    @SideOnly(Side.CLIENT)
    private Icon splashIcon;
    @SideOnly(Side.CLIENT)
    private Icon regularIcon;
    @SideOnly(Side.CLIENT)
    private Icon contentIcon;

    public ItemPZPotion(int itemID) {
        super(itemID);
        setCreativeTab(ProjectZulu_Core.projectZuluPotionTab);
        setMaxStackSize(3);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1) {
        return isSplash(par1) ? splashIcon : regularIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    public Icon getIconFromDamageForRenderPass(int par1, int par2) {
        return par2 == 0 ? contentIcon : getIconFromDamage(par1);
    }

    // TODO: Get Rid Staticness and Check if it can be safely used instead of ItemPotion static isSplash
    public static boolean isSplash(int par0) {
        return PotionParser.isSplash(par0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isEffectInstant(int damageMeta) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(itemID, PotionParser.readID(damageMeta));
        return potion != null ? potion.isEffectInstant(damageMeta) : false;
    }

    @Override
    public String getItemDisplayName(ItemStack itemStack) {
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
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(itemID, PotionParser.readID(damageMeta));
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
    public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
        Collection<SubItemPotion> potion = SubItemPotionRegistry.INSTANCE.getPotions(itemID);
        for (SubItemPotion subItemPotion : potion) {
            subItemPotion.getSubItems(itemID, creativeTabs, list);
        }
    }

    public ItemStack getPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        SubItemPotion potion = SubItemPotionRegistry.INSTANCE.getPotion(brewingStack);
        return potion != null ? potion.getPotionResult(ingredient, brewingStack) : null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister) {
        this.regularIcon = par1IconRegister.registerIcon("potion");
        this.splashIcon = par1IconRegister.registerIcon("potion_splash");
        this.contentIcon = par1IconRegister.registerIcon("potion_contents");
    }
}
