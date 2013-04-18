package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockSpikes;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SpikesDeclaration extends BlockDeclaration {

    public SpikesDeclaration() {
        super("Spikes");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.spike = Optional.of(new BlockSpikes(iD).setHardness(0.5F).setStepSound(Block.soundMetalFootstep)
                .setUnlocalizedName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.spike.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        LanguageRegistry.addName(block, "Ivory Spikes");
    }
}
