package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockCampfire;
import projectzulu.common.blocks.ItemCampFire;
import projectzulu.common.blocks.RenderCampFire;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CampfireDeclaration extends BlockDeclaration {

    private int renderID = -1;

    public CampfireDeclaration() {
        super("Campfire");
    }

    @Override
    protected void preCreateLoadConfig(Configuration config) {
        renderID = config.get("Do Not Touch", "Campfire Render ID", renderID).getInt(renderID);
        renderID = renderID == -1 ? RenderingRegistry.getNextAvailableRenderId() : renderID;
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.campfire = Optional.of(new BlockCampfire(iD, renderID).setUnlocalizedName(DefaultProps.blockKey + ":"
                + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.campfire.get();
        GameRegistry.registerBlock(block, ItemCampFire.class, name.toLowerCase());
        LanguageRegistry.addName(block, "Campfire");
        for (BlockCampfire.Type type : BlockCampfire.Type.values()) {
            LanguageRegistry.addName(new ItemStack(block, 1, type.meta()), type.displayName());
        }
    }

    @Override
    protected void clientRegisterBlock() {
        RenderingRegistry.registerBlockHandler(renderID, new RenderCampFire());
        ProjectZuluLog.info("Campfire Render ID Registed to %s", renderID);
    }
}
