package projectzulu.common.world;

import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.mobs.EntityHauntedArmor;

public class HauntedArmorWithMeta extends BlockWithMeta{
	
	public HauntedArmorWithMeta() {
		super(-1);
	}
	
	@Override
	public void placeBlock(World world, ChunkCoordinates position, Random random) {
		if(!world.isRemote){
			
			/* Place Air cause we don't want to spawn Inside something*/
			world.setBlock(position.posX, position.posY, position.posZ, 0);
			
			/* Spawn Mimic */
			EntityHauntedArmor mob = new EntityHauntedArmor(world, position.posX+0.5, position.posY, position.posZ+0.5, true);
			mob.initCreature();
			world.spawnEntityInWorld(mob);

		}
	}

}
