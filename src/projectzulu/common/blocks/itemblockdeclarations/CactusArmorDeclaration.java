package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class CactusArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public CactusArmorDeclaration(int renderIndex) {
        super(new String[] { "CactusHelmet", "CactusChest", "CactusLegs", "CactusBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        switch (partIndex) {
        case 0:
            ItemList.cactusArmorHead = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex,
                    0)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 1:
            ItemList.cactusArmorChest = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex,
                    1)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 2:
            ItemList.cactusArmorLeg = Optional
                    .of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex, 2))
                            .setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        case 3:
            ItemList.cactusArmorBoots = Optional.of((new ItemZuluArmor(iD, ProjectZulu_Core.scaleMaterial, renderIndex,
                    3)).setUnlocalizedName(DefaultProps.blockKey + ":" + name[partIndex].toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        switch (partIndex) {
        case 0: {
            Item item = ItemList.cactusArmorHead.get();
            LanguageRegistry.addName(item, "Cactus Cloth Helmet");
            break;
        }
        case 1: {
            Item item = ItemList.cactusArmorChest.get();
            LanguageRegistry.addName(item, "Cactus Cloth Chestplate");
            break;
        }
        case 2: {
            Item item = ItemList.cactusArmorLeg.get();
            LanguageRegistry.addName(item, "Cactus Cloth Leggings");
        }

        case 3: {
            Item item = ItemList.cactusArmorBoots.get();
            LanguageRegistry.addName(item, "Cactus Cloth Boots");
        }
        }
    }
}
