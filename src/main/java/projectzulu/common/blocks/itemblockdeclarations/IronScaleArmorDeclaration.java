package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

public class IronScaleArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public IronScaleArmorDeclaration(int renderIndex) {
        super(new String[] { "IronScaleHelmet", "IronScaleChest", "IronScaleLegs", "IronScaleBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int partIndex) {
        Item item = new ItemZuluArmor(ProjectZulu_Core.ironScaleMaterial, renderIndex, partIndex,
                name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.ironScaleArmorHead = Optional.of(item);
            return true;
        case 1:
            ItemList.ironScaleArmorChest = Optional.of(item);
            return true;
        case 2:
            ItemList.ironScaleArmorLeg = Optional.of(item);
            return true;
        case 3:
            ItemList.ironScaleArmorBoots = Optional.of(item);
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
    }
}