package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class RedClothArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public RedClothArmorDeclaration(int renderIndex) {
        super(new String[] { "RedClothHelmet", "RedClothChest", "RedClothLegs", "RedClothBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.redClothHead = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 0, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 1:
            ItemList.redClothChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 1, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 2:
            ItemList.redClothLeg = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 2, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 3:
            ItemList.redClothBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 3, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.redClothHead.get();
            LanguageRegistry.addName(item, "Red Cloth Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.redClothChest.get();
            LanguageRegistry.addName(item, "Red Cloth Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.redClothLeg.get();
            LanguageRegistry.addName(item, "Red Cloth Leggings");
        }

        case 3: {
            Item item = ItemList.redClothBoots.get();
            LanguageRegistry.addName(item, "Red Cloth Boots");
        }
        }
    }
}
