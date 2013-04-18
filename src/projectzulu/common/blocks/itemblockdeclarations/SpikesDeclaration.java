package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockSpikes;
import projectzulu.common.blocks.RenderSpike;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.RenderingRegistry;
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

    @Override
    protected void clientRegisterBlock() {
        ProjectZulu_Core.spikeRenderID = ProjectZulu_Core.spikeRenderID == -1 ? RenderingRegistry
                .getNextAvailableRenderId() : ProjectZulu_Core.spikeRenderID;
        RenderingRegistry.registerBlockHandler(ProjectZulu_Core.spikeRenderID, new RenderSpike());
        ProjectZuluLog.info("Spike Render ID Registed to %s", ProjectZulu_Core.spikeRenderID);
    }
}
