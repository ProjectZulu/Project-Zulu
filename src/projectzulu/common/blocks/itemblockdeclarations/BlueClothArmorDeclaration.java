package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlueClothArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public BlueClothArmorDeclaration(int renderIndex) {
        super(new String[] { "BlueClothHelmet", "BlueClothChest", "BlueClothLegs", "BlueClothBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.blueClothHead = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 0)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 1:
            ItemList.blueClothChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 1)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 2:
            ItemList.blueClothLeg = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 2)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 3:
            ItemList.blueClothBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 3)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.blueClothHead.get();
            LanguageRegistry.addName(item, "Blue Cloth Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.blueClothChest.get();
            LanguageRegistry.addName(item, "Blue Cloth Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.blueClothLeg.get();
            LanguageRegistry.addName(item, "Blue Cloth Leggings");
        }

        case 3: {
            Item item = ItemList.blueClothBoots.get();
            LanguageRegistry.addName(item, "Blue Cloth Boots");
        }
        }
    }
}
