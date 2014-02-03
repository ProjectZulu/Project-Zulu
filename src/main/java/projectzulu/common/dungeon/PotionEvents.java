package projectzulu.common.dungeon;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.brewing.PotionBrewedEvent;
import projectzulu.common.core.ProjectZuluLog;

public class PotionEvents {

    @ForgeSubscribe
    public void potionBrewed(PotionBrewedEvent event) {
        int i = -1;
        for (ItemStack potionStack : event.brewingStacks) {
            i++;
            if (potionStack == null) {
                continue;
            }
            ProjectZuluLog.info("Potions Stack %s is %s", i, potionStack.getItem().itemID);
        }
    }
}
