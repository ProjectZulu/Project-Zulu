package projectzulu.common.world;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.API.ItemBlockList;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.world.buildingmanager.BuildingManagerCemetary;
import projectzulu.common.world.buildingmanager.BuildingManagerLabyrinth;

public enum StructureManager {
	Pyramid{
		@Override
		protected void loadDefaultProperties() {
			structureName = "Pyramid";
			shouldGenerate = true;
			minDistance = 9;
			chunksPerSpawn = 100;
			defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);			defaultBiomesToSpawn.add("Mountainous Desert");
		}
		@Override
		public boolean checkToGenerate(World world, int Xcoord, int Ycoord, int Zcoord, Random random){	
			if(world.getBlockMaterial(Xcoord, Ycoord, Zcoord) == Material.water){
				return false;
			}
			if(!doesTerrainFluctuateTooMuch(world, Xcoord, Ycoord, Zcoord, 5, 12)){
				return false;
			}
			return true;
		}
		
		@Override
		public boolean generate(World world, int Xcoord, int Ycoord, int Zcoord, Random random){
			return (new WorldGenPyramid(1, 1)).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
	},
	Labyrinth{
		@Override
		protected void loadDefaultProperties() {
			structureName = "Labyrinth";
			shouldGenerate = true;
			minDistance = 9;
			chunksPerSpawn = 100;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);			defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);	
			defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName);		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);	
			defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);				defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName);				defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName);	
			defaultBiomesToSpawn.add(BiomeGenBase.iceMountains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.extremeHillsEdge.biomeName); 	defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);			defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName);
			defaultBiomesToSpawn.add("Birch Forest"); 							defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Forested Hills"); 						defaultBiomesToSpawn.add("Green Hills");
			defaultBiomesToSpawn.add("Mountain Taiga"); 						defaultBiomesToSpawn.add("Pine Forest");
			defaultBiomesToSpawn.add("Rainforest"); 							defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods"); 							defaultBiomesToSpawn.add("Snow Forest");
			defaultBiomesToSpawn.add("Snowy Rainforest"); 						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");								defaultBiomesToSpawn.add("Mountainous Desert");
		}
		@Override
		public boolean checkToGenerate(World world, int Xcoord, int Ycoord, int Zcoord, Random random){		
			if(world.getBlockMaterial(Xcoord, Ycoord, Zcoord) == Material.water){
				return false;
			}
			if(!doesTerrainFluctuateTooMuch(world, Xcoord, Ycoord, Zcoord, 5, 12)){
				return false;
			}
			return true;
		}
		@Override
		public boolean generate(World world, int Xcoord, int Ycoord, int Zcoord, Random random){
			return new MazeGenerator(new BuildingManagerLabyrinth(world), 1, 4, 3, 24, 1, 1).generate(world, random, Xcoord, Ycoord-(12), Zcoord);
		}
	},
	Cemetary{
		@Override
		protected void loadDefaultProperties() {
			structureName = "Cemetary";
			shouldGenerate = true;
			minDistance = 3;
			chunksPerSpawn = 100;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);			defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);			defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);				defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName);			defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.iceMountains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.extremeHillsEdge.biomeName); 	
			defaultBiomesToSpawn.add("Birch Forest"); 							defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Forested Hills"); 						defaultBiomesToSpawn.add("Green Hills");
			defaultBiomesToSpawn.add("Mountain Taiga"); 						defaultBiomesToSpawn.add("Pine Forest");
			defaultBiomesToSpawn.add("Rainforest"); 							defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods"); 							defaultBiomesToSpawn.add("Snow Forest");
			defaultBiomesToSpawn.add("Snowy Rainforest"); 						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public boolean checkToGenerate(World world, int Xcoord, int Ycoord, int Zcoord, Random random){	
			if(world.getBlockMaterial(Xcoord, Ycoord-1, Zcoord) == Material.ice 
					|| world.getBlockMaterial(Xcoord, Ycoord, Zcoord) == Material.water 
					|| world.getBlockMaterial(Xcoord, Ycoord-1, Zcoord) == Material.sand){
				return false;
			}
			
			if(!doesTerrainFluctuateTooMuch(world, Xcoord, Ycoord, Zcoord, 2, 6)){
				return false;
			}
			
			return true;
		}
		@Override
		public boolean generate(World world, int Xcoord, int Ycoord, int Zcoord, Random random){
			return (new MazeGenerator(new BuildingManagerCemetary(world), 1, 4, 3, 11, 1, 1)).generate(world, world.rand, Xcoord, Ycoord-1, Zcoord);
		}
	},
	Oasis{
		@Override
		protected void loadDefaultProperties() {
			structureName = "Oasis";
			shouldGenerate = true;
			minDistance = 3;
			chunksPerSpawn = 400;
			defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);			defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		}
		@Override
		public boolean checkToGenerate(World world, int Xcoord, int Ycoord, int Zcoord, Random random){	
			if(!doesTerrainFluctuateTooMuch(world, Xcoord, Ycoord, Zcoord, 3, 6)){
				return false;
			}
			if( !ItemBlockList.palmTreeSapling.isPresent() || !ItemBlockList.palmTreeLog.isPresent() || !ItemBlockList.palmTreeLeaves.isPresent()){
				return false;
			}
			
			return true;
		}
		@Override
		public boolean generate(World world, int Xcoord, int Ycoord, int Zcoord, Random random){
			return (new WorldGenOasis()).generate(world, random, Xcoord, Ycoord-1, Zcoord);
		}
	};
	
	protected String structureName;
	protected boolean shouldGenerate = true;
	protected int minDistance = 10;
	protected int chunksPerSpawn = 400;
	protected boolean printToLog = false;
	protected ArrayList<String> defaultBiomesToSpawn = new ArrayList();
	protected ArrayList<BiomeGenBase> biomesToSpawn = new ArrayList();

	public static void loadGeneralSettings(Configuration structureConfig){
		for (final StructureManager structure : StructureManager.values()){
			structure.loadDefaultProperties();
			structure.shouldGenerate = structureConfig.get("World Generation Controls."+structure.structureName, structure.structureName.toLowerCase()+" Should Generate",
					structure.shouldGenerate).getBoolean(structure.shouldGenerate);
			structure.minDistance = structureConfig.get("World Generation Controls."+structure.structureName, structure.structureName.toLowerCase()+" minDistance",
					structure.minDistance).getInt(structure.minDistance);
			structure.chunksPerSpawn = structureConfig.get("World Generation Controls."+structure.structureName, structure.structureName.toLowerCase()+" chunksPerSpawn",
					structure.chunksPerSpawn).getInt(structure.chunksPerSpawn);
			structure.printToLog = structureConfig.get("World Generation Controls."+structure.structureName, structure.structureName.toLowerCase()+" printToLog",
					structure.printToLog).getBoolean(structure.printToLog);
		}
	}
	protected abstract void loadDefaultProperties();
	
	public static void loadBiomeSettings(Configuration structureConfig){
		for (final StructureManager structure : StructureManager.values()) {
			for (int i = 0; i < BiomeGenBase.biomeList.length; i++) {
				if(BiomeGenBase.biomeList[i] == null){
					continue;
				}
				if(structureConfig.get(
						"Mob Spawn Biome Controls."+structure.structureName,
						structure.structureName.toLowerCase()+" in "+BiomeGenBase.biomeList[i].biomeName,
						structure.defaultBiomesToSpawn.contains(BiomeGenBase.biomeList[i].biomeName)).getBoolean(false)){
					structure.biomesToSpawn.add(BiomeGenBase.biomeList[i]);
				}
			}
		}
	}

	/**
	 * Main method to generate structures. Tries to generate one of all structure in StructureManager.
	 * Performs all neccesary checks for location. Biomes / Probability / Distance / Block / enabled.
	 * @param world
	 * @param Chunk to Generate in: chunkX, chunkZ
	 * @param Block Position to Generate to generate at: Xcoord, Ycoord, Zcoord
	 * @param random
	 * @return Whether Structure was Placed Successfully
	 */
	public static boolean tryToGenerateStructure(World world,int chunkX, int chunkZ, int Xcoord, int Ycoord, int Zcoord, Random random){
		for (StructureManager structure : StructureManager.values()) {
			/*Do Generic Checks: Chunk Distance & Random Chance & Biome */
			if( (chunkX/16) % structure.minDistance != 0 && (chunkZ/16) % structure.minDistance != 0){
				continue;
			}
			if( !structure.shouldGenerate || random.nextInt(structure.chunksPerSpawn) != 0){
				continue;
			}
			if(!structure.biomesToSpawn.contains(world.getBiomeGenForCoords(Xcoord, Zcoord))){
				return false;
			}
			
			/*Do Structure Specific Checks: Invalid Block, terrain to Wobbly */
			if(structure.checkToGenerate(world, Xcoord, Ycoord, Zcoord, random)){
				if(structure.generate(world, Xcoord, Ycoord, Zcoord, random)){
					if(structure.printToLog){
						ProjectZuluLog.info("Generating %s at %s, %s, %s", structure.name(), Xcoord, Ycoord, Zcoord);
					}
					return true;
				}else{
					if(structure.printToLog){
						ProjectZuluLog.info("Failed to Generate %s at %s, %s, %s", structure.name(), Xcoord, Ycoord, Zcoord);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Used to place a specific structure, does not Check if Location is Valid
	 * @param Structure to Place
	 * @param world
	 * @param Xcoord, Ycoord, Zcoord
	 * @param random
	 * @return
	 */
	public static boolean tryToGenerateSpecificStructure(StructureManager structure, World world, int Xcoord, int Ycoord, int Zcoord, Random random){
		if(!structure.shouldGenerate){
			ProjectZuluLog.warning("Attempted to Generate %s that was disabeld. Effort was Thwarted.", structure.name());
			return false;
		}
		
		if(structure.generate(world, Xcoord, Ycoord, Zcoord, random)){
			if(structure.printToLog){
				ProjectZuluLog.info("Generating %s at %s, %s, %s", structure.name(), Xcoord, Ycoord, Zcoord);
			}
			return true;
		}

		return false;
	}	
	
	/**
	 * Method to Check if the particular structure should Generate at Provided X,Y,Z
	 * @param shouldLog
	 */
	public abstract boolean checkToGenerate(World world, int Xcoord, int Ycoord, int Zcoord, Random random);
	/**
	 * Method to Generate the Structure at Provided X,Y,Z
	 * @param shouldLog
	 */
	public abstract boolean generate(World world, int Xcoord, int Ycoord, int Zcoord, Random random);
	
	/**
	 * Method to Evaluate The Terain Fluctuation. Compares the height along each point of an X to the center. 
	 * If greater than provided difference return false;
	 * @param world
	 * @param Xcoord
	 * @param Ycoord
	 * @param Zcoord
	 * @param maxDifference
	 * @param squareLength
	 * @return
	 */
	private static boolean doesTerrainFluctuateTooMuch(World world, int Xcoord, int Ycoord, int Zcoord, int maxDifference, int squareLength){
		if( Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord+squareLength) - Ycoord) < maxDifference 
				&& Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord-squareLength) - Ycoord ) < maxDifference 
				&& Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord+squareLength) - Ycoord ) < maxDifference 
				&& Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord-squareLength) - Ycoord ) < maxDifference){
			return true;
		}

		return false;
	}
}
