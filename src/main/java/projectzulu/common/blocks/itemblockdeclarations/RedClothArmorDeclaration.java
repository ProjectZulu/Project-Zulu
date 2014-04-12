package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class RedClothArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public RedClothArmorDeclaration(int renderIndex) {
        super(new String[] { "RedClothHelmet", "RedClothChest", "RedClothLegs", "RedClothBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int partIndex) {
        Item item = new ItemZuluArmor(ProjectZulu_Core.desertClothMaterial, renderIndex, partIndex,
                name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.redClothHead = Optional.of(item);
            return true;
        case 1:
            ItemList.redClothChest = Optional.of(item);
            return true;
        case 2:
            ItemList.redClothLeg = Optional.of(item);
            return true;
        case 3:
            ItemList.redClothBoots = Optional.of(item);
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        Item item = null;
        switch (partIndex) {
        case 0:
            item = ItemList.redClothHead.get();
            break;
        case 1:
            item = ItemList.redClothChest.get();
            break;
        case 2:
            item = ItemList.redClothLeg.get();
            break;
        case 3:
            item = ItemList.redClothBoots.get();
            break;
        }
        GameRegistry.registerItem(item, name[partIndex]);
    }
}
