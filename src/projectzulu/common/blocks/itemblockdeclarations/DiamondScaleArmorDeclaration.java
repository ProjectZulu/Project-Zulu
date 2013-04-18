package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class DiamondScaleArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public DiamondScaleArmorDeclaration(int renderIndex) {
        super(new String[] { "DiamondScaleHelmet", "DiamondScaleChest", "DiamondScaleLegs", "DiamondScaleBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.diamondScaleArmorHead = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.diamondScaleMaterial,
                    renderIndex, 0)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 1:
            ItemList.diamondScaleArmorChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.diamondScaleMaterial,
                    renderIndex, 1)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 2:
            ItemList.diamondScaleArmorLeg = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.diamondScaleMaterial,
                    renderIndex, 2)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 3:
            ItemList.diamondScaleArmorBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.diamondScaleMaterial,
                    renderIndex, 3)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.diamondScaleArmorHead.get();
            LanguageRegistry.addName(item, "Diamond Plated Scale Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.diamondScaleArmorChest.get();
            LanguageRegistry.addName(item, "Diamond Plated Scale Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.diamondScaleArmorLeg.get();
            LanguageRegistry.addName(item, "Diamond Plated Scale Leggings");
        }

        case 3: {
            Item item = ItemList.diamondScaleArmorBoots.get();
            LanguageRegistry.addName(item, "Diamond Plated Scale Boots");
        }
        }
    }
}
