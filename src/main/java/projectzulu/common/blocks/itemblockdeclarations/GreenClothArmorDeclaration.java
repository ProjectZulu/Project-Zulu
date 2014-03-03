package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

public class GreenClothArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public GreenClothArmorDeclaration(int renderIndex) {
        super(new String[] { "GreenClothHelmet", "GreenClothChest", "GreenClothLegs", "GreenClothBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int partIndex) {
        Item item = new ItemZuluArmor(ProjectZulu_Core.desertClothMaterial, renderIndex, partIndex,
                name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.greenClothHead = Optional.of(item);
            return true;
        case 1:
            ItemList.greenClothChest = Optional.of(item);
            return true;
        case 2:
            ItemList.greenClothLeg = Optional.of(item);
            return true;
        case 3:
            ItemList.greenClothBoots = Optional.of(item);
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
    }
}
