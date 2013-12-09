package projectzulu.common;

import java.util.concurrent.ConcurrentHashMap;

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
            player.addExperience(experience);
        }
    }

    @Override
    public void onPlayerLogout(EntityPlayer player) {
        entityIdToExperience.remove(player.entityId);
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {
    }

    @Override
    public void onPlayerLogin(EntityPlayer player) {
    }
}
