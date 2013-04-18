package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GoldScaleArmorDeclaration extends ItemSetDeclaration {

    public GoldScaleArmorDeclaration() {
        super(new String[] { "GoldScaleHelmet", "GoldScaleChest", "GoldScaleLegs", "GoldScaleBoots" });
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.goldScaleArmorHead = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.goldScaleMaterial,
                    ProjectZulu_Blocks.goldScaleIndex, 0)).setUnlocalizedName(DefaultProps.blockKey + ":"
                    + name[partIndex].toLowerCase()));
            return true;
        case 1:
            ItemList.goldScaleArmorChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.goldScaleMaterial,
                    ProjectZulu_Blocks.goldScaleIndex, 1)).setUnlocalizedName(DefaultProps.blockKey + ":"
                    + name[partIndex].toLowerCase()));
            return true;
        case 2:
            ItemList.goldScaleArmorLeg = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.goldScaleMaterial,
                    ProjectZulu_Blocks.goldScaleIndex, 2)).setUnlocalizedName(DefaultProps.blockKey + ":"
                    + name[partIndex].toLowerCase()));
            return true;
        case 3:
            ItemList.goldScaleArmorBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.goldScaleMaterial,
                    ProjectZulu_Blocks.goldScaleIndex, 3)).setUnlocalizedName(DefaultProps.blockKey + ":"
                    + name[partIndex].toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.goldScaleArmorHead.get();
            LanguageRegistry.addName(item, "Gold Scale Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.goldScaleArmorChest.get();
            LanguageRegistry.addName(item, "Gold Scale Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.goldScaleArmorLeg.get();
            LanguageRegistry.addName(item, "Gold Scale Leggings");
        }

        case 3: {
            Item item = ItemList.goldScaleArmorBoots.get();
            LanguageRegistry.addName(item, "Gold Scale Boots");
        }
        }
    }
}
