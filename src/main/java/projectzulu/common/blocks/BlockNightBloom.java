package projectzulu.common.blocks;

import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNightBloom extends BlockBush {
    public static final String[] imageSuffix = new String[] { "_0", "_1", "_2", "_3", "_4" };
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    private int tickInterval = 4;

    public int getTickInterval() {
        return tickInterval;
    }

    public BlockNightBloom() {
        super(Material.plants);
        setTickRandomly(true);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        disableStats();
        setHardness(0.5F);
        setStepSound(Block.soundTypeGrass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
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
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.getBlockMetadata(x, y, z) > 1) {
            return 9;
        } else {
            return 0;
        }
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        // If Night Time && And is not open (meta != 4) : begin opening
        if (mapTimeTo24000(par1World.getWorldTime()) > 13000 && par1World.getBlockMetadata(par2, par3, par4) != 4) {

            par1World.setBlock(par2, par3, par4, this, par1World.getBlockMetadata(par2, par3, par4) + 1, 3);

            par1World.scheduleBlockUpdate(par2, par3, par4, this, 20);
        }

        // If Day Time && And is not closed (meta != 0) : begin opening
        if (mapTimeTo24000(par1World.getWorldTime()) < 13000 && par1World.getBlockMetadata(par2, par3, par4) != 0) {

            par1World.setBlock(par2, par3, par4, this, par1World.getBlockMetadata(par2, par3, par4) - 1, 3);

            par1World.scheduleBlockUpdate(par2, par3, par4, this, 20);
        }
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.getBlockMetadata(par2, par3, par4) == 4) {
            setLightLevel(0.6f);
        }
        if (par1World.getBlockMetadata(par2, par3, par4) == 0) {
            setLightLevel(0);
        }

        par1World.scheduleBlockUpdate(par2, par3, par4, this, (20 * 5) + par5Random.nextInt(20 * 10));
    }

    private Long mapTimeTo24000(Long worldTime) {
        Long tempWorldTime = worldTime;
        while (tempWorldTime > 24000) {
            tempWorldTime -= 24000;
        }
        if (tempWorldTime <= 0) {
            return 0L;
        }
        return tempWorldTime;
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        par1World.scheduleBlockUpdate(par2, par3, par4, this, 2);
        super.onBlockAdded(par1World, par2, par3, par4);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland
                || (BlockList.wateredDirt.isPresent() && block == BlockList.wateredDirt.get());
    }
}
