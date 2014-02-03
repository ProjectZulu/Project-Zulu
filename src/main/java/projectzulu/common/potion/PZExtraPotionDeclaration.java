package projectzulu.common.potion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;
import projectzulu.common.potion.subitem.SubItemPotion;
import projectzulu.common.potion.subitem.SubItemPotionBlindness;
import projectzulu.common.potion.subitem.SubItemPotionBubbling;
import projectzulu.common.potion.subitem.SubItemPotionCleansing;
import projectzulu.common.potion.subitem.SubItemPotionCurse;
import projectzulu.common.potion.subitem.SubItemPotionDigslowdown;
import projectzulu.common.potion.subitem.SubItemPotionDigspeed;
import projectzulu.common.potion.subitem.SubItemPotionIncendiary;
import projectzulu.common.potion.subitem.SubItemPotionJump;
import projectzulu.common.potion.subitem.SubItemPotionRegistry;
import projectzulu.common.potion.subitem.SubItemPotionResistance;
import projectzulu.common.potion.subitem.SubItemPotionSlowfall;
import projectzulu.common.potion.subitem.SubItemPotionThorns;
import projectzulu.common.potion.subitem.SubItemPotionWaterBreathing;

import com.google.common.base.Optional;

public class PZExtraPotionDeclaration extends ItemDeclaration {

    public PZExtraPotionDeclaration() {
        super("PZCustomPotion");
    }

    @Override
    protected boolean createItem(int iD) {
        Item item = new ItemPZPotion(iD, name);
        ItemList.customPotions = Optional.of(item);
        int i = 0;
        List<SubItemPotion> list = new ArrayList<SubItemPotion>();
        addToLists(item.itemID, i++, SubItemPotionList.BUBBLING, list, SubItemPotionBubbling.class);
        addToLists(item.itemID, i++, SubItemPotionList.INCENDIARY, list, SubItemPotionIncendiary.class);
        addToLists(item.itemID, i++, SubItemPotionList.SLOWFALL, list, SubItemPotionSlowfall.class);
        addToLists(item.itemID, i++, SubItemPotionList.CLEANSING, list, SubItemPotionCleansing.class);
        addToLists(item.itemID, i++, SubItemPotionList.CURSE, list, SubItemPotionCurse.class);
        addToLists(item.itemID, i++, SubItemPotionList.THORNS, list, SubItemPotionThorns.class);
        addToLists(item.itemID, i++, SubItemPotionList.JUMP, list, SubItemPotionJump.class);
        addToLists(item.itemID, i++, SubItemPotionList.DIG_SPEED, list, SubItemPotionDigspeed.class);
        addToLists(item.itemID, i++, SubItemPotionList.DIG_SLOW, list, SubItemPotionDigslowdown.class);
        addToLists(item.itemID, i++, SubItemPotionList.RESISTANCE, list, SubItemPotionResistance.class);
        addToLists(item.itemID, i++, SubItemPotionList.WATER_BREATHING, list, SubItemPotionWaterBreathing.class);
        addToLists(item.itemID, i++, SubItemPotionList.BLINDNESS, list, SubItemPotionBlindness.class);
        for (SubItemPotion subItemPotion : list) {
            SubItemPotionRegistry.INSTANCE.addSubPotions(subItemPotion);
        }

        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.customPotions.get();
        ProjectZuluLog.info("register");
        registerSubPotions(item.itemID);
    }

    private void registerSubPotions(int itemID) {
        Collection<SubItemPotion> potions = SubItemPotionRegistry.INSTANCE.getPotions(itemID);
        for (SubItemPotion subItemPotion : potions) {
            subItemPotion.register();
        }
    }

    private void addToLists(int itemID, int subID, SubItemPotionList entry, List<SubItemPotion> registryList,
            Class<? extends SubItemPotion> potionClass) {
        try {
            SubItemPotion subItemPotion = potionClass.getConstructor(new Class[] { int.class, int.class }).newInstance(
                    new Object[] { itemID, subID });
            entry.set(subItemPotion);
            registryList.add(subItemPotion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
