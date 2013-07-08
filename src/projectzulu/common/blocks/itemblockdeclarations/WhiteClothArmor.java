package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class WhiteClothArmor extends ItemSetDeclaration {

    public final int renderIndex;

    public WhiteClothArmor(int renderIndex) {
        super(new String[] { "WhiteClothHelmet", "WhiteClothChest", "WhiteClothLegs", "WhiteClothBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.whiteClothHead = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 0, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 1:
            ItemList.whiteClothChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 1, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 2:
            ItemList.whiteClothLeg = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 2, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        case 3:
            ItemList.whiteClothBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 3, DefaultProps.blockKey + ":" + name[partIndex].toLowerCase())));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.whiteClothHead.get();
            LanguageRegistry.addName(item, "White Cloth Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.whiteClothChest.get();
            LanguageRegistry.addName(item, "White Cloth Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.whiteClothLeg.get();
            LanguageRegistry.addName(item, "White Cloth Leggings");
        }

        case 3: {
            Item item = ItemList.whiteClothBoots.get();
            LanguageRegistry.addName(item, "White Cloth Boots");
        }
        }
    }
}
