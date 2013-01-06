package projectzulu.common;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

import org.w3c.dom.css.Counter;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PotionCleansingTicker implements ITickHandler {
	
	private static Multimap entityToPotionEffect = Multimaps.synchronizedMultimap(HashMultimap.create());
	private static int tickCounter; 
	
	public static void addPotionEffectToRemove(int entityId, int PotionID){
		synchronized (entityToPotionEffect) {
			entityToPotionEffect.put(entityId, PotionID);
		}
	}
	
	public static boolean isEntityPresentInMap(int entityId){
		return entityToPotionEffect.containsKey(entityId);
	}
	
	public static Collection getValuesOfEntity(int entityId){
		Collection collection;
		synchronized (entityToPotionEffect) {
			collection = entityToPotionEffect.get(entityId);
		}

		return collection;
	}
	
	public static void removeEntityandValues(int entityId){
		synchronized (entityToPotionEffect) {
			entityToPotionEffect.removeAll(entityId);
		}
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		World worldObj = (World)tickData[0];

		/* This is to remove EntityIDs that get placed on the stack but are for entities that don't exist */
		if(!entityToPotionEffect.isEmpty() && tickCounter % 120 + worldObj.rand.nextInt(120) == 0) {
			synchronized (entityToPotionEffect) {
				Iterator entityIDIterator = entityToPotionEffect.keySet().iterator();
				while(entityIDIterator.hasNext()) {
					int entityId = (Integer) entityIDIterator.next();
					EntityLiving entityWithPotions = (EntityLiving) worldObj.getEntityByID(entityId);
					if(entityWithPotions == null) {
						entityIDIterator.remove();
					}
				}
			}
		}
		tickCounter++;
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return null;
	}

}
