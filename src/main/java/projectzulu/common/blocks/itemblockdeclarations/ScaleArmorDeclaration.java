package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class ScaleArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public ScaleArmorDeclaration(int renderIndex) {
        super(new String[] { "ScaleHelmet", "ScaleChest", "ScaleLegs", "ScaleBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int partIndex) {
        Item item = new ItemZuluArmor(ProjectZulu_Core.scaleMaterial, renderIndex, partIndex,
                name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.scaleArmorHead = Optional.of(item);
            return true;
        case 1:
            ItemList.scaleArmorChest = Optional.of(item);
            return true;
        case 2:
            ItemList.scaleArmorLeg = Optional.of(item);
            return true;
        case 3:
            ItemList.scaleArmorBoots = Optional.of(item);
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        Item item = null;
        switch (partIndex) {
        case 0:
            item = ItemList.scaleArmorHead.get();
            break;
        case 1:
            item = ItemList.scaleArmorChest.get();
            break;
        case 2:
            item = ItemList.scaleArmorLeg.get();
            break;
        case 3:
            item = ItemList.scaleArmorBoots.get();
            break;
        }
        GameRegistry.registerItem(item, name[partIndex]);
    }
}
