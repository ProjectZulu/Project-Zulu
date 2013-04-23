package projectzulu.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class PZExtraPotionDeclaration extends ItemDeclaration {

    public PZExtraPotionDeclaration() {
        super("ZuluPotion");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.customPotions = Optional.of(new ItemPZPotion(iD).setUnlocalizedName(name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.customPotions.get();
        registerSubPotions(item.itemID);
    }

    private void registerSubPotions(int itemID) {
        int i = 0;
        List<SubItemPotion> list = new ArrayList<SubItemPotion>();
        list.add(new SubItemPotionBubbling(itemID, i++));
        list.add(new SubItemPotionIncendiary(itemID, i++));
        list.add(new SubItemPotionSlowfall(itemID, i++));
        list.add(new SubItemPotionCleansing(itemID, i++));
        list.add(new SubItemPotionThorns(itemID, i++));
        list.add(new SubItemPotionJump(itemID, i++));
        list.add(new SubItemPotionDigspeed(itemID, i++));
        list.add(new SubItemPotionDigslowdown(itemID, i++));
        list.add(new SubItemPotionResistance(itemID, i++));
        list.add(new SubItemPotionWaterBreathing(itemID, i++));
        for (SubItemPotion subItemPotion : list) {
            SubItemPotionRegistry.INSTANCE.addSubPotions(subItemPotion);
        }
    }
}
