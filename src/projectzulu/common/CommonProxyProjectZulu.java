package projectzulu.common;

import net.minecraft.tileentity.TileEntity;
import projectzulu.common.temperature.TemperatureTicker;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxyProjectZulu{
	
	/**
	 * Call During Pre-Init
	 */
	public TemperatureTicker initializeTempTicker(){
		TemperatureTicker tempTicker = new TemperatureTicker();
        TickRegistry.registerTickHandler(tempTicker, Side.SERVER );
        return tempTicker;
	}
	
 	public void registerRenderThings(){
 		//Not Done on server
	}

	public int registerBlockCustomRender(ISimpleBlockRenderingHandler renderClass){
 		return 0;
 	}
 	
 	/**
 	 * Called during Init
 	 */
 	public void registerTileEntitySpecialRender(Class <? extends TileEntity> tileEntityClass, Object specialRenderer){}
 	public void registerTileEntitySkullSpecialRender(){}
 	public void registerTileEntityTombstoneSpecialRender(){}
 	public void registerTileEntityUniversalFlowerPotSpecialRender(){}

 	
	public int addArmor(String armor){
		return 0;
	}
    public void registerSoundHandler() {}


	public void Init() {}

	public void clientEventHooks(){}


	public void registerSound() {}	
	
	public void bossHealthTicker(){}
	
	
	/**
	 * Call During Init
	 */
	public void registerMobRenders(){

	}
	
	/**
	 * Call During Init
	 */
 	public void registerBlockRenders(){
 		//Not Done on server
	}
 	
	/**
	 * Call During Pre-Init
	 */
	public void registerMobSounds(){

	}
	/**
	 * Call During Init
	 */
	public void registerMobSoundEvent(){

	}
}
