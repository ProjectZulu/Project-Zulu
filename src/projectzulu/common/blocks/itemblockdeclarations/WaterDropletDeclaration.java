package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemFoodProjectZulu;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class WaterDropletDeclaration extends ItemDeclaration {

    public WaterDropletDeclaration() {
        super("WaterDroplet");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.waterDroplets = Optional.of(new ItemFoodProjectZulu(iD, 1, 0.6f, false)
                .setUnlocalizedName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.waterDroplets.get();
        LanguageRegistry.addName(item, "Water Droplets");
    }

}
