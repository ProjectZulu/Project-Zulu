package projectzulu.common.api;

import net.minecraft.block.Block;

import com.google.common.base.Optional;

public enum BlockList {
	instance;
	public static Optional<? extends Block>	palmHoloTree = Optional.absent(); // Not Used?
	
	public static Optional<? extends Block>	aloeVera = Optional.absent();
	public static Optional<? extends Block>	wateredDirt = Optional.absent();
	public static Optional<? extends Block>	tumbleweed = Optional.absent();
	public static Optional<? extends Block>	jasper = Optional.absent();
	
	public static Optional<? extends Block>	palmTreeLog = Optional.absent();
	public static Optional<? extends Block>	palmTreePlank = Optional.absent();
	public static Optional<? extends Block>	palmTreeSlab = Optional.absent();
	public static Optional<? extends Block>	palmTreeDoubleSlab = Optional.absent();
	public static Optional<? extends Block>	palmTreeStairs = Optional.absent();
	public static Optional<? extends Block>	palmTreeLeaves = Optional.absent();
	public static Optional<? extends Block>	palmTreeSapling = Optional.absent();
	public static Optional<? extends Block>	coconut = Optional.absent();
	
	public static Optional<? extends Block>	nightBloom = Optional.absent();
	public static Optional<? extends Block>	creeperBlossom = Optional.absent();
	public static Optional<? extends Block>	quickSand = Optional.absent();
	
	public static Optional<? extends Block>	spike = Optional.absent();
	public static Optional<? extends Block>	campfire = Optional.absent();
	public static Optional<? extends Block>	mobHeads = Optional.absent();
	public static Optional<? extends Block>	tombstone = Optional.absent();
	
	public static Optional<? extends Block>	universalFlowerPot = Optional.absent();
	public static Optional<? extends Block>	customBrewingStand = Optional.absent();
	public static Optional<? extends Block>	limitedMobSpawner = Optional.absent();
	public static Optional<? extends Block> brewingStandSingle = Optional.absent();
    public static Optional<? extends Block> brewingStandTriple = Optional.absent();

}
