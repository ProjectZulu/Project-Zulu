package projectzulu.common.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import projectzulu.common.api.ItemList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWateredDirt extends Block {
    public static final String[] imageSuffix = new String[] { "_d0", "_d1", "_d2", "_d3", "_s0", "_s1", "_s2", "_s3" };
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockWateredDirt() {
        super(Material.sand);
        setHardness(0.5f);
        setResistance(1.0f);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        if (par2 < 4) {
            this.setStepSound(Block.soundTypeGravel);
        } else {
            this.setStepSound(Block.soundTypeSand);
        }
        return icons[par2];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.icons = new IIcon[imageSuffix.length];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = par1IconRegister.registerIcon(getTextureName() + imageSuffix[i]);
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (metadata < 3) {
            ret.add(new ItemStack(Blocks.dirt));
            return ret;
        }

        if (metadata == 3) {
            if (ItemList.waterDroplets.isPresent()) {
                ret.add(new ItemStack(ItemList.waterDroplets.get()));
                ret.add(new ItemStack(ItemList.waterDroplets.get()));
                ret.add(new ItemStack(ItemList.waterDroplets.get()));
            }
            ret.add(new ItemStack(Blocks.dirt));
            return ret;
        }

        if (metadata > 3 && metadata < 7) {
            ret.add(new ItemStack(Blocks.sand));
            return ret;
        }

        if (metadata == 7) {
            if (ItemList.waterDroplets.isPresent()) {
                ret.add(new ItemStack(ItemList.waterDroplets.get()));
                ret.add(new ItemStack(ItemList.waterDroplets.get()));
                ret.add(new ItemStack(ItemList.waterDroplets.get()));
            }
            ret.add(new ItemStack(Blocks.sand));
            return ret;
        }
        return ret;
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }

    @Override
    public int getRenderType() {
        return 0;
    }
}
