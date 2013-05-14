package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class ScaleArmorDeclaration extends ItemSetDeclaration {
    
    public final int renderIndex;
    public ScaleArmorDeclaration(int renderIndex) {
        super(new String[] { "ScaleHelmet", "ScaleChest", "ScaleLegs", "ScaleBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.scaleArmorHead = Optional
                    .of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex, 0))
                            .setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 1:
            ItemList.scaleArmorChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex,
                    1)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 2:
            ItemList.scaleArmorLeg = Optional
                    .of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex, 2))
                            .setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 3:
            ItemList.scaleArmorBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex,
                    3)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.scaleArmorHead.get();
            LanguageRegistry.addName(item, "Scale Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.scaleArmorChest.get();
            LanguageRegistry.addName(item, "Scale Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.scaleArmorLeg.get();
            LanguageRegistry.addName(item, "Scale Leggings");
        }

        case 3: {
            Item item = ItemList.scaleArmorBoots.get();
            LanguageRegistry.addName(item, "Scale Boots");
        }
        }
    }
}
