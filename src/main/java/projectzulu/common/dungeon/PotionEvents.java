package projectzulu.common.dungeon;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.brewing.PotionBrewedEvent;
import projectzulu.common.core.ProjectZuluLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PotionEvents {

    @SubscribeEvent
    public void potionBrewed(PotionBrewedEvent event) {
        int i = -1;
        for (ItemStack potionStack : event.brewingStacks) {
            i++;
            if (potionStack == null) {
                continue;
            }
            ProjectZuluLog.info("Potions Stack %s is %s", i, potionStack.getItem());
        }
    }
}
