package projectzulu.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class ZuluPotionDeclaration extends ItemDeclaration {

    public ZuluPotionDeclaration() {
        super("ZuluPotion");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.potion = Optional.of(new ItemPZPotion(iD).setUnlocalizedName(name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.potion.get();
        registerSubPotions(item.itemID);
    }

    private void registerSubPotions(int itemID) {
        int i = 0;
        List<SubItemPotion> list = new ArrayList<SubItemPotion>();
        list.add(new SubItemPotionBubbling(itemID, i++));
        list.add(new SubItemPotionIncendiary(itemID, i++));

        for (SubItemPotion subItemPotion : list) {
            SubItemPotionRegistry.INSTANCE.addSubPotions(subItemPotion);
        }
    }
}
