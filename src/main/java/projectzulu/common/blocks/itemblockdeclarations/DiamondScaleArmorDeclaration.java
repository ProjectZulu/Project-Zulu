package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class DiamondScaleArmorDeclaration extends ItemSetDeclaration {

    public final int renderIndex;

    public DiamondScaleArmorDeclaration(int renderIndex) {
        super(new String[] { "DiamondScaleHelmet", "DiamondScaleChest", "DiamondScaleLegs", "DiamondScaleBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int partIndex) {
        Item item = new ItemZuluArmor(ProjectZulu_Core.diamondScaleMaterial, renderIndex, partIndex,
                name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.diamondScaleArmorHead = Optional.of(item);
            return true;
        case 1:
            ItemList.diamondScaleArmorChest = Optional.of(item);
            return true;
        case 2:
            ItemList.diamondScaleArmorLeg = Optional.of(item);
            return true;
        case 3:
            ItemList.diamondScaleArmorBoots = Optional.of(item);
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
        Item item = null;
        switch (partIndex) {
        case 0:
            item = ItemList.diamondScaleArmorHead.get();
            break;
        case 1:
            item = ItemList.diamondScaleArmorChest.get();
            break;
        case 2:
            item = ItemList.diamondScaleArmorLeg.get();
            break;
        case 3:
            item = ItemList.diamondScaleArmorBoots.get();
            break;
        }
        GameRegistry.registerItem(item, name[partIndex]);
    }
}
