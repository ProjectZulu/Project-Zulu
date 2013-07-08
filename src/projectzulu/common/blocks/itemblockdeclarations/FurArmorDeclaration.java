package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class FurArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public FurArmorDeclaration(int renderIndex) {
        super(new String[] { "FurHelmet", "FurChest", "FurLegs", "FurBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.furArmorHead = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex, 0,
                    DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 1:
            ItemList.furArmorChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex, 1,
                    DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 2:
            ItemList.furArmorLeg = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex, 2,
                    DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 3:
            ItemList.furArmorBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex, 3,
                    DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.furArmorHead.get();
            LanguageRegistry.addName(item, "Fur Cloth Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.furArmorChest.get();
            LanguageRegistry.addName(item, "Fur Cloth Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.furArmorLeg.get();
            LanguageRegistry.addName(item, "Fur Cloth Leggings");
        }

        case 3: {
            Item item = ItemList.furArmorBoots.get();
            LanguageRegistry.addName(item, "Fur Cloth Boots");
        }
        }
    }

}
