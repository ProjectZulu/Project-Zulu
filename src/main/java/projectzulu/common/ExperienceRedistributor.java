package projectzulu.common;

import java.util.concurrent.ConcurrentHashMap;

import projectzulu.common.core.ProjectZuluLog;

import cpw.mods.fml.common.IPlayerTracker;

import net.minecraft.entity.player.EntityPlayer;

public class ExperienceRedistributor implements IPlayerTracker {
    private ConcurrentHashMap<Integer, Integer> entityIdToExperience = new ConcurrentHashMap<Integer, Integer>();

    public void addExpereince(EntityPlayer player, int experience) {
        entityIdToExperience.put(player.entityId, experience);
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player) {
        Integer experience = entityIdToExperience.remove(player.entityId);
        if (experience != null) {
            ProjectZuluLog.info("Removing Experience [%s] and adding [%s]", player.experienceTotal, experience);
            player.experienceLevel = 0;
            player.experienceTotal = 0;
            player.experience = 0;
            player.addExperience(experience);
        }
    }

    @Override
    public void onPlayerLogout(EntityPlayer player) {
        Integer experience = entityIdToExperience.remove(player.entityId);
        if (experience != null) {
            ProjectZuluLog.info("Removing Experience (%s) and adding (%s)", player.experienceTotal, experience);
            player.experienceLevel = 0;
            player.experienceTotal = 0;
            player.experience = 0;
            player.addExperience(experience);
        }
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {
    }

    @Override
    public void onPlayerLogin(EntityPlayer player) {
    }
}
