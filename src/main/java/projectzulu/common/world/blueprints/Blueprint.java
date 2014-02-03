package projectzulu.common.world.blueprints;

import java.util.Random;

import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;

public abstract class Blueprint {
	
	public abstract BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection);	
	
	public int getWeight(){
		return 1;
	}
	
	
	/**
	 * Used to Search Architect list for a specific Building Type
	 * Should be All lowercase
	 */
	public String getIdentifier(){
		return "generic";
	}
}
