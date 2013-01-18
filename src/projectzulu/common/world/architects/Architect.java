package projectzulu.common.world.architects;

import java.util.ArrayList;
import java.util.Random;

import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.blueprints.Blueprint;

/**
 * This Class Is Responsible for Supplying Individual Buildings to the BuildingManager
 * @author CaseyB
 */
public abstract class Architect {
	
	/**
	 * Used to Track which building should be being built
	 */
	int unCarvedState;
	int carvedState;
	
	/**
	 * List of BLueprints for different Cell SubStates
	 */
	ArrayList<Blueprint> unCarvedBlueprintList = new ArrayList<Blueprint>();
	ArrayList<Blueprint> carvedBlueprintList = new ArrayList<Blueprint>();

	
	/**
	 * Marks the orientation within the cell of an invidiual node
	 */
	CellIndexDirection cellIndexDirection = CellIndexDirection.Inner;
	
	
	public void randomiseState(Random random){
		unCarvedState = getWeightedState(unCarvedBlueprintList, random);
		carvedState = getWeightedState(carvedBlueprintList, random);
		cellIndexDirection = cellIndexDirection.randomCardinalDirection(random);
	}
	
	public BlockWithMeta getCarvedBlock(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex, Random random){
		return getCarvedBlock(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection, carvedState);
	}
	public BlockWithMeta getUnCarvedBlock(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex, Random random){
		return getUnCarvedBlock(cellIndex, cellSize, curHeight, maxHeight, xIndex, zIndex, random, cellIndexDirection, unCarvedState);
	}
	
	public abstract BlockWithMeta getCarvedBlock(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection, int buildingIndex);
	public abstract BlockWithMeta getUnCarvedBlock(int cellIndex, int cellSize, int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection, int buildingIndex);

	public int searchUncarvedFor(String identifier){
		for (int i = 0; i < unCarvedBlueprintList.size(); i++) {
			if(unCarvedBlueprintList.get(i).getIdentifier().equals(identifier)){
				return i;
			}
		}
		return -1;
	}
	
	public int searchCarvedFor(String identifier){
		for (int i = 0; i < carvedBlueprintList.size(); i++) {
			if(carvedBlueprintList.get(i).getIdentifier().equals(identifier)){
				return i;
			}
		}
		return -1;
	}
	
	public int getWeightedState(ArrayList<Blueprint> bluePrintList, Random random){

		int weightSum = 0; 
		for (Blueprint blueprint : bluePrintList){
			weightSum += blueprint.getWeight();
		}
		weightSum = weightSum > 0 ? random.nextInt(weightSum) + 1 : 0;
		for (int i = 0; i < bluePrintList.size(); i++){
			weightSum -= bluePrintList.get(i).getWeight();
			if(weightSum <= 0){
				return i;
			}
		}
		return 0;
	}
}
