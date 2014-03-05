package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;
import projectzulu.common.potion.brewingstands.BlockBrewingStandSingle;
import projectzulu.common.potion.brewingstands.RenderBrewingStandSingle;
import projectzulu.common.potion.brewingstands.TileEntityBrewingSingle;
import projectzulu.common.potion.brewingstands.TileEntityBrewingStandRenderer;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BrewingStandSingleDeclaration extends BlockDeclaration {

    private int renderID = -1;

    public BrewingStandSingleDeclaration() {
        super("BrewingStandSingle");
    }

    @Override
    protected void preCreateLoadConfig(Configuration config) {
        renderID = config.get("Do Not Touch", "Brewing Stand Single Render ID", renderID).getInt(renderID);
        renderID = renderID == -1 ? RenderingRegistry.getNextAvailableRenderId() : renderID;
    }

    @Override
    protected boolean createBlock() {
        BlockList.brewingStandSingle = Optional.of(new BlockBrewingStandSingle(renderID).setBlockName("brewingsingle")
                .setBlockTextureName(DefaultProps.blockKey + ":brewingsingle"));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.brewingStandSingle.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        GameRegistry.registerTileEntity(TileEntityBrewingSingle.class, "TileEntityBrewingSingle");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void clientRegisterBlock() {
        RenderingRegistry.registerBlockHandler(renderID, new RenderBrewingStandSingle());
        ProjectZuluLog.info("Brewing Stand Single Render ID Registed to %s", renderID);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrewingSingle.class,
                new TileEntityBrewingStandRenderer());
    }
}