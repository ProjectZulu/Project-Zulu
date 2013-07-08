package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class IronScaleArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public IronScaleArmorDeclaration(int renderIndex) {
        super(new String[] { "IronScaleHelmet", "IronScaleChest", "IronScaleLegs", "IronScaleBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.ironScaleArmorHead = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.ironScaleMaterial,
                    renderIndex, 0, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 1:
            ItemList.ironScaleArmorChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.ironScaleMaterial,
                    renderIndex, 1, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 2:
            ItemList.ironScaleArmorLeg = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.ironScaleMaterial,
                    renderIndex, 2, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 3:
            ItemList.ironScaleArmorBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.ironScaleMaterial,
                    renderIndex, 3, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.ironScaleArmorHead.get();
            LanguageRegistry.addName(item, "Iron Plated Scale Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.ironScaleArmorChest.get();
            LanguageRegistry.addName(item, "Iron Plated Scale Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.ironScaleArmorLeg.get();
            LanguageRegistry.addName(item, "Iron Plated Scale Leggings");
        }

        case 3: {
            Item item = ItemList.ironScaleArmorBoots.get();
            LanguageRegistry.addName(item, "Iron Plated Scale Boots");
        }
        }
    }
}
