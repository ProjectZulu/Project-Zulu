package projectzulu.common;

import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.blocks.EntityCreeperBlossomPrimed;
import projectzulu.common.blocks.RenderCreeperBlossomPrimed;
import projectzulu.common.core.AudioLoader;
import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.mobs.BossHealthDisplayTicker;
import projectzulu.common.temperature.TemperatureTicker;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyProjectZulu extends CommonProxyProjectZulu{ 	
	@Override
	public void registerBlockRenders(){
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperBlossomPrimed.class, new RenderCreeperBlossomPrimed(0.5f));
	}
	
	@Override
	public int addArmor(String armor){
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	@Override
	public void bossHealthTicker(){
        TickRegistry.registerTickHandler(new BossHealthDisplayTicker(), Side.CLIENT);
	}
	
	@Override
	public void registerModelsAndRender(){
		CustomEntityManager.INSTANCE.registerModelsAndRender();
	}
	
	@Override
	public void registerAudioLoader(){
        MinecraftForge.EVENT_BUS.register(new AudioLoader());
	}
	
	@Override
	public TemperatureTicker initializeTempTicker() {
		TemperatureTicker tempTicker = new TemperatureTicker();        
        TickRegistry.registerTickHandler(tempTicker, Side.SERVER );
        TickRegistry.registerTickHandler(new TemperatureTicker(), Side.CLIENT );
//        TickRegistry.registerTickHandler(new DisplayTemperatureTicker(), Side.CLIENT);
        return tempTicker;
	}
}
