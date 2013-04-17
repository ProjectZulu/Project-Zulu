package projectzulu.common.core;

import java.io.File;
import java.util.ArrayList;

import net.minecraftforge.common.Configuration;
import projectzulu.common.core.itemblockdeclaration.ItemBlockDeclaration;

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
        int currentRenderPass = 0;
        boolean hasNextPass = false;
        do {
            for (ItemBlockDeclaration itemBlock : itemBlocks) {
                if (currentRenderPass == itemBlock.getRegisterPass()) {
                    itemBlock.loadFromConfig(config, true);
                    itemBlock.create();
                }
            }
            for (ItemBlockDeclaration itemBlock : itemBlocks) {
                if (currentRenderPass == itemBlock.getRegisterPass()) {
                    itemBlock.loadFromConfig(config, false);
                    itemBlock.create();
                } else if (currentRenderPass < itemBlock.getRegisterPass()) {
                    hasNextPass = true;
                }
            }
            currentRenderPass++;
        } while (hasNextPass);

        config.save();
    }

    public void registerBlocks() {
        for (ItemBlockDeclaration itemBlock : itemBlocks) {
            itemBlock.register();
        }
    }
}
