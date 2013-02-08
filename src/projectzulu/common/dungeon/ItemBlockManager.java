package projectzulu.common.dungeon;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.ProjectZulu_Dungeon;
import projectzulu.common.api.BlockList;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public enum ItemBlockManager {
	LimitedMobSpawner{
		@Override
		protected boolean isBlock() { return true;	}
		
		@Override
		protected void create() {
			BlockList.limitedMobSpawner = Optional.of(
					new BlockLimitedMobSpawner(blockID).setHardness(0.5F).setStepSound(Block.soundMetalFootstep).setBlockName("LimitedMobSpawner"));
		}
		
		@Override
		protected void register() {
			Block block = BlockList.limitedMobSpawner.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "LimitedMobSpawner");
			/* Register TileEntity Render */
			GameRegistry.registerTileEntity(TileEntityLimitedMobSpawner.class, "TileEntityLimitedMobSpawner");
			ProjectZulu_Core.proxy.registerTileEntitySpecialRender(TileEntityLimitedMobSpawner.class, "projectzulu.common.dungeon.TileEntityLimitedMobSpawnerRenderer");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	};
	
	protected int blockID;
	private boolean	blockCreated = false;

	public static void preInit(Configuration config) 
			throws Exception{
		loadSettings(config);
		createBlocks();
	}

	private static void loadSettings(Configuration config){
		
		/* Load Configuration Settings */
		for (final ItemBlockManager block : ItemBlockManager.values()) {
			block.blockID = block.isBlock() ? 
					config.getBlock(block.toString()+" ID", ProjectZulu_Dungeon.getNextDefaultBlockID()).getInt() 
					: config.getItem(block.toString()+" ID", ProjectZulu_Dungeon.getNextDefaultItemID()).getInt();
			block.loadCustomConfig(config);
		}
	}
	protected abstract boolean isBlock();
	protected abstract void loadCustomConfig(Configuration config);

	private static void createBlocks() throws Exception {
		for (final ItemBlockManager block : ItemBlockManager.values())
			if (block.blockID > 0) {
				try {
					block.create();
				} catch (final Exception e) {
					throw e;
				}
				block.blockCreated = true;
			}
	}
	
	protected abstract void create();
	
	public static void init() throws InstantiationException,
	IllegalAccessException{
		for (final ItemBlockManager block : values())
			if (block.blockCreated) block.register();
	}
	protected abstract void register();	
}
