package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockUniversalFlowerPot;
import projectzulu.common.blocks.ItemUniversalFlowerPot;
import projectzulu.common.blocks.TileEntityUniversalFlowerPot;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class UniversalFlowerPotDeclaration extends BlockDeclaration {

    public UniversalFlowerPotDeclaration() {
        super("UniversalFlowerPot");
    }

    @Override
    protected boolean createBlock(int iD) {
        if (ProjectZulu_Core.replaceFlowerPot) {
            Block.blocksList[Block.flowerPot.blockID] = null;
            BlockList.universalFlowerPot = Optional.of(new BlockUniversalFlowerPot(Block.flowerPot.blockID)
                    .setUnlocalizedName("flowerPot"));
        } else {
            BlockList.universalFlowerPot = Optional.of(new BlockUniversalFlowerPot(iD).setUnlocalizedName("flowerPot"));
        }
        return true;
    }

    @Override
    protected void registerBlock() {
        if (!ProjectZulu_Core.replaceFlowerPot) {
            Block block = BlockList.universalFlowerPot.get();
            GameRegistry.registerBlock(block, name.toLowerCase());
            LanguageRegistry.addName(block, "Universal Flower Pot");
            new ItemUniversalFlowerPot(block.blockID - 256, block).setUnlocalizedName("flowerPot");
        }
        GameRegistry.registerTileEntity(TileEntityUniversalFlowerPot.class, "TileEntityUniversalFlowerPot");
        ProjectZulu_Core.proxy.registerTileEntitySpecialRender(TileEntityUniversalFlowerPot.class,
                "projectzulu.common.blocks.TileEntityUniversalFlowerPotRenderer");
    }
}
