package projectzulu.common;

import projectzulu.common.temperature.TemperatureTicker;
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
	
	public int addArmor(String armor){ return 0; }
	public void bossHealthTicker(){}
	public void registerMobRenders(){}
 	public void registerBlockRenders(){}
	public void registerMobSounds(){}
	public void registerMobSoundEvent(){}
	public void registerModelsAndRender(){}
}
