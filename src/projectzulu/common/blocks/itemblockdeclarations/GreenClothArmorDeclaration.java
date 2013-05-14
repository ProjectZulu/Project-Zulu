package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GreenClothArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public GreenClothArmorDeclaration(int renderIndex) {
        super(new String[] { "GreenClothHelmet", "GreenClothChest", "GreenClothLegs", "GreenClothBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.greenClothHead = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 0)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 1:
            ItemList.greenClothChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 1)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 2:
            ItemList.greenClothLeg = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 2)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 3:
            ItemList.greenClothBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial,
                    renderIndex, 3)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.greenClothHead.get();
            LanguageRegistry.addName(item, "Green Cloth Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.greenClothChest.get();
            LanguageRegistry.addName(item, "Green Cloth Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.greenClothLeg.get();
            LanguageRegistry.addName(item, "Green Cloth Leggings");
        }

        case 3: {
            Item item = ItemList.greenClothBoots.get();
            LanguageRegistry.addName(item, "Green Cloth Boots");
        }
        }
    }
}
