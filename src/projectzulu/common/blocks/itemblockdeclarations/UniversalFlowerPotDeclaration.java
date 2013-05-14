package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import projectzulu.common.Properties;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockUniversalFlowerPot;
import projectzulu.common.blocks.ItemUniversalFlowerPot;
import projectzulu.common.blocks.RenderUniversalFlowerPot;
import projectzulu.common.blocks.TileEntityUniversalFlowerPot;
import projectzulu.common.blocks.TileEntityUniversalFlowerPotRenderer;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class UniversalFlowerPotDeclaration extends BlockDeclaration {

    private int renderID = -1;

    public UniversalFlowerPotDeclaration() {
        super("UniversalFlowerPot");
    }

    @Override
    protected void preCreateLoadConfig(Configuration config) {
        renderID = config.get("Do Not Touch", "Universal Flower Pot Render ID", renderID).getInt(renderID);
        renderID = renderID == -1 ? RenderingRegistry.getNextAvailableRenderId() : renderID;
    }

    @Override
    protected boolean createBlock(int iD) {
        if (Properties.replaceFlowerPot) {
            Block.blocksList[Block.flowerPot.blockID] = null;
            BlockList.universalFlowerPot = Optional.of(new BlockUniversalFlowerPot(Block.flowerPot.blockID, renderID)
                    .setUnlocalizedName("flowerPot"));
        } else {
            BlockList.universalFlowerPot = Optional.of(new BlockUniversalFlowerPot(iD, renderID)
                    .setUnlocalizedName("flowerPot"));
        }
        return true;
    }

    @Override
    protected void registerBlock() {
        if (!Properties.replaceFlowerPot) {
            Block block = BlockList.universalFlowerPot.get();
            GameRegistry.registerBlock(block, name.toLowerCase());
            LanguageRegistry.addName(block, "Universal Flower Pot");
            new ItemUniversalFlowerPot(block.blockID - 256, block).setUnlocalizedName("flowerPot");
        }
        GameRegistry.registerTileEntity(TileEntityUniversalFlowerPot.class, "TileEntityUniversalFlowerPot");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void clientRegisterBlock() {
        RenderingRegistry.registerBlockHandler(renderID, new RenderUniversalFlowerPot());
        ProjectZuluLog.info("Universal Flower Pot Render ID Registed to %s", renderID);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUniversalFlowerPot.class,
                new TileEntityUniversalFlowerPotRenderer());
    }
}
