package projectzulu.common.core;

import java.io.File;
import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;
import projectzulu.common.core.itemblockdeclaration.ItemBlockDeclaration;
import cpw.mods.fml.common.FMLCommonHandler;

public enum ItemBlockManager {
    INSTANCE;
    private ArrayList<ItemBlockDeclaration> itemBlocks = new ArrayList<ItemBlockDeclaration>();

    public void addItemBlock(ItemBlockDeclaration... itemBlock) {
        for (ItemBlockDeclaration declaration : itemBlock) {
            itemBlocks.add(declaration);
        }
    }

    public void createBlocks(File configDirectory) {
        Configuration config = new Configuration(new File(configDirectory, DefaultProps.configDirectory
                + DefaultProps.defaultConfigFile));
        config.load();
        createFromConfig(config, true);
        createFromConfig(config, false);
        config.save();
    }

    private void createFromConfig(Configuration config, boolean readOnly) {
        int currentRenderPass = 0;
        boolean hasNextPass;
        do {
            hasNextPass = false;
            for (ItemBlockDeclaration itemBlock : itemBlocks) {
                if (currentRenderPass == itemBlock.getRegisterPass()) {
                    itemBlock.createWithConfig(config, readOnly);
                } else if (currentRenderPass < itemBlock.getRegisterPass()) {
                    hasNextPass = true;
                }
            }
            currentRenderPass++;
        } while (hasNextPass);
    }

    public void registerBlocks() {
        for (ItemBlockDeclaration itemBlock : itemBlocks) {
            itemBlock.register(FMLCommonHandler.instance().getEffectiveSide());
        }
    }
}
