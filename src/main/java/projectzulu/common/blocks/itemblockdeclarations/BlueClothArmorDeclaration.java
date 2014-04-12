package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlueClothArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public BlueClothArmorDeclaration(int renderIndex) {
        super(new String[] { "BlueClothHelmet", "BlueClothChest", "BlueClothLegs", "BlueClothBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int partIndex) {
        Item item = new ItemZuluArmor(ProjectZulu_Core.desertClothMaterial, renderIndex, partIndex,
                name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.blueClothHead = Optional.of(item);
            return true;
        case 1:
            ItemList.blueClothChest = Optional.of(item);
            return true;
        case 2:
            ItemList.blueClothLeg = Optional.of(item);
            return true;
        case 3:
            ItemList.blueClothBoots = Optional.of(item);
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        Item item = null;
        switch (partIndex) {
        case 0:
            item = ItemList.blueClothHead.get();
            break;
        case 1:
            item = ItemList.blueClothChest.get();
            break;
        case 2:
            item = ItemList.blueClothLeg.get();
            break;
        case 3:
            item = ItemList.blueClothBoots.get();
            break;
        }
        GameRegistry.registerItem(item, name[partIndex]);
    }
}
