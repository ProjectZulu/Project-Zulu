package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

public class CactusArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public CactusArmorDeclaration(int renderIndex) {
        super(new String[] { "CactusHelmet", "CactusChest", "CactusLegs", "CactusBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        Item item = new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex, partIndex,
                name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.cactusArmorHead = Optional.of(item);
            return true;
        case 1:
            ItemList.cactusArmorChest = Optional.of(item);
            return true;
        case 2:
            ItemList.cactusArmorLeg = Optional.of(item);
            return true;
        case 3:
            ItemList.cactusArmorBoots = Optional.of(item);
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
    }
}
