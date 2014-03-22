package projectzulu.common;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import projectzulu.common.core.ProjectZuluLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class ExperienceRedistributor {
    private ConcurrentHashMap<Integer, Integer> entityIdToExperience = new ConcurrentHashMap<Integer, Integer>();

    public void addExpereince(EntityPlayer player, int experience) {
        entityIdToExperience.put(player.getEntityId(), experience);
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Integer experience = entityIdToExperience.remove(event.player.getEntityId());
        if (experience != null) {
            ProjectZuluLog.info("Removing Experience [%s] and adding [%s]", event.player.experienceTotal, experience);
            event.player.experienceLevel = 0;
            event.player.experienceTotal = 0;
            event.player.experience = 0;
            event.player.addExperience(experience);
        }
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerLoggedOutEvent event) {
        Integer experience = entityIdToExperience.remove(event.player.getEntityId());
        if (experience != null) {
            ProjectZuluLog.info("Removing Experience (%s) and adding (%s)", event.player.experienceTotal, experience);
            event.player.experienceLevel = 0;
            event.player.experienceTotal = 0;
            event.player.experience = 0;
            event.player.addExperience(experience);
        }
    }
}
