package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class FurArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public FurArmorDeclaration(int renderIndex) {
        super(new String[] { "FurHelmet", "FurChest", "FurLegs", "FurBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int partIndex) {
        Item item = new ItemZuluArmor(ProjectZulu_Core.scaleMaterial, renderIndex, partIndex,
                name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.furArmorHead = Optional.of(item);
            return true;
        case 1:
            ItemList.furArmorChest = Optional.of(item);
            return true;
        case 2:
            ItemList.furArmorLeg = Optional.of(item);
            return true;
        case 3:
            ItemList.furArmorBoots = Optional.of(item);
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        Item item = null;
        switch (partIndex) {
        case 0:
            item = ItemList.furArmorHead.get();
            break;
        case 1:
            item = ItemList.furArmorChest.get();
            break;
        case 2:
            item = ItemList.furArmorLeg.get();
            break;
        case 3:
            item = ItemList.furArmorBoots.get();
            break;
        }
        GameRegistry.registerItem(item, name[partIndex]);
    }
}