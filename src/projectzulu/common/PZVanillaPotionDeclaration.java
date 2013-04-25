package projectzulu.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class PZVanillaPotionDeclaration extends ItemDeclaration {

    public PZVanillaPotionDeclaration() {
        super("PZVanillaPotion");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.vanillaPotions = Optional.of(new ItemPZPotion(iD).setUnlocalizedName(name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.vanillaPotions.get();
        registerSubPotions(item.itemID);
    }

    private void registerSubPotions(int itemID) {
        int i = 0;
        List<SubItemPotion> list = new ArrayList<SubItemPotion>();
        list.add(new SubItemPotionDamageBoost(itemID, i++));
        list.add(new SubItemPotionRegeneration(itemID, i++));
        list.add(new SubItemPotionPoison(itemID, i++));
        list.add(new SubItemPotionWeakness(itemID, i++));
        list.add(new SubItemPotionMoveSpeed(itemID, i++));
        list.add(new SubItemPotionMoveSlowdown(itemID, i++));
        list.add(new SubItemPotionFireResistance(itemID, i++));
        list.add(new SubItemPotionNightVision(itemID, i++));
        list.add(new SubItemPotionBlindness(itemID, i++));
        list.add(new SubItemPotionHeal(itemID, i++));
        list.add(new SubItemPotionHarm(itemID, i++));

        for (SubItemPotion subItemPotion : list) {
            SubItemPotionRegistry.INSTANCE.addSubPotions(subItemPotion);
        }
    }
}