package projectzulu.common.blocks.itemblockdeclarations;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockTombstone;
import projectzulu.common.blocks.TileEntityTombstone;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TombstoneDeclaration extends BlockDeclaration {

    public TombstoneDeclaration() {
        super("Tombstone");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.tombstone = Optional.of(new BlockTombstone(iD, TileEntityTombstone.class)
                .setUnlocalizedName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.tombstone.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        LanguageRegistry.addName(block, "Tombstone");

        Configuration tempConfig = new Configuration(new File(ProjectZulu_Core.modConfigDirectoryFile,
                DefaultProps.configDirectory + DefaultProps.tempConfigFile));
        tempConfig.load();
        Property property = tempConfig.get("TempSettings.Tombstone", "useAlterantiveTileEntityName", false);
        if (!property.getBoolean(false)) {
            try {
                GameRegistry.registerTileEntity(TileEntityTombstone.class, "TileEntityTombstone");
            } catch (IllegalArgumentException e) {
                GameRegistry.registerTileEntity(TileEntityTombstone.class, "PZTileEntityTombstone");
            }
        } else {
            GameRegistry.registerTileEntity(TileEntityTombstone.class, "PZTileEntityTombstone");
            property.set(true);
        }
        tempConfig.save();

        /* Register TileEntity Render */
        ProjectZulu_Core.proxy.registerTileEntitySpecialRender(TileEntityTombstone.class,
                "projectzulu.common.blocks.TileEntityTombstoneRenderer");
    }
}
