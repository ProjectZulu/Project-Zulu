package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.heads.BlockMobHeads;
import projectzulu.common.blocks.heads.ItemMobHeads;
import projectzulu.common.blocks.heads.TileEntityMobHeads;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MobSkullsDeclaration extends BlockDeclaration {

    public MobSkullsDeclaration() {
        super("MobSkulls");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.mobHeads = Optional.of(new BlockMobHeads(iD).setUnlocalizedName(DefaultProps.blockKey + ":"
                + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.mobHeads.get();
        GameRegistry.registerBlock(block, ItemMobHeads.class, name.toLowerCase());
        LanguageRegistry.addName(block, "mobHeads");
        for (BlockMobHeads.Head head : BlockMobHeads.Head.values()) {
            LanguageRegistry.addName(new ItemStack(block, 1, head.meta()), head.displayName());
        }
        GameRegistry.registerTileEntity(TileEntityMobHeads.class, "TileEntityMobHead");
        ProjectZulu_Core.proxy.registerTileEntitySpecialRender(TileEntityMobHeads.class,
                "projectzulu.common.blocks.heads.TileEntityMobHeadsRenderer");
    }

}
