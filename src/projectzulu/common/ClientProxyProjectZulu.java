package projectzulu.common;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.blocks.EntityCreeperBlossomPrimed;
import projectzulu.common.blocks.RenderCreeperBlossomPrimed;
import projectzulu.common.blocks.TileEntityTombstone;
import projectzulu.common.blocks.TileEntityTombstoneRenderer;
import projectzulu.common.blocks.TileEntityUniversalFlowerPot;
import projectzulu.common.blocks.TileEntityUniversalFlowerPotRenderer;
import projectzulu.common.blocks.heads.TileEntityMobHeads;
import projectzulu.common.blocks.heads.TileEntityMobHeadsRenderer;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.SoundHandlerClass;
import projectzulu.common.core.SoundHookContainerClass;
import projectzulu.common.mobs.*;
import projectzulu.common.temperature.DisplayTemperatureTicker;
import projectzulu.common.temperature.TemperatureTicker;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyProjectZulu extends CommonProxyProjectZulu{
	public static int scaleIndex;
	public static int whiteWoolIndex;
	public static int redWoolIndex;
	public static int greenWoolIndex;
	public static int blueWoolIndex;
	
	public static int goldScaleIndex;
	public static int ironScaleIndex;
	public static int diamondScaleIndex;
	public static int cactusIndex;
	public static int mammothIndex;


	@Override
	public void registerRenderThings(){
		MinecraftForgeClient.preloadTexture(DefaultProps.blockSpriteSheet);
		MinecraftForgeClient.preloadTexture(DefaultProps.itemSpriteSheet);
		MinecraftForgeClient.preloadTexture("/mods/icons/temperature_icon.png");
	}
	
 	/**
 	 * Called during Init
 	 */
 	@Override
 	public void registerTileEntitySpecialRender(Class <? extends TileEntity> tileEntityClass, Object specialRenderer){
 		if(specialRenderer instanceof TileEntitySpecialRenderer){
 			ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, (TileEntitySpecialRenderer) specialRenderer);
 		}else{
 			ProjectZuluLog.warning("Attempted to Register TileEntitySpecifalRenderer that was not a TileEntitySpecialRendere. Object: %s",specialRenderer);
 		}
       
 	}
	@Override
 	public void registerTileEntitySkullSpecialRender(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMobHeads.class, new TileEntityMobHeadsRenderer());
 	}
	
	@Override
 	public void registerTileEntityTombstoneSpecialRender(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTombstone.class, new TileEntityTombstoneRenderer());
 	}
	
	@Override
 	public void registerTileEntityUniversalFlowerPotSpecialRender(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUniversalFlowerPot.class, new TileEntityUniversalFlowerPotRenderer());
	}
	
	@Override
	public int registerBlockCustomRender(ISimpleBlockRenderingHandler renderClass){
		int renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(renderID, renderClass);
		ProjectZuluLog.info("Spike Render ID Registed to %s", renderID);
 		return renderID;
 	}
	
	//Init
	@Override
	public void registerBlockRenders(){
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperBlossomPrimed.class, new RenderCreeperBlossomPrimed());
	}
	
	@Override
	public int addArmor(String armor){
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	//Init
	@Override
	public void registerSound(){
        
	}
	
	//Init
	@Override
	public void clientEventHooks(){
		//Is Not Needed
//		MinecraftForge.EVENT_BUS.register(new EventClientHookContainerClass());
	}
	
	//PreInit
	@Override
    public void registerSoundHandler() {
    }
	
	//Pre-Init
	@Override
	public void bossHealthTicker(){
        TickRegistry.registerTickHandler(new BossHealthDisplayTicker(), Side.CLIENT);
//        TickRegistry.registerTickHandler(new RenderCustomArmorTicker(), Side.CLIENT);
	}
	
	//Init
	@Override
	public void registerMobRenders(){
		RenderingRegistry.registerEntityRenderingHandler(EntityArmadillo.class, new RenderArmadillo(new ModelArmadillo(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySandWorm.class, new RenderSandWorm(new ModelSandWorm(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityLizard.class, new RenderLizard(new ModelLizard(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityLizardSpit.class, new RenderLizardSpit(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderMummy(new ModelMummy(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMummyPharaoh.class, new RenderMummyPharaoh(new ModelMummyPharaoh(),0.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntityVulture.class, new RenderVulture(new ModelVulture(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTreeEnt.class, new RenderTreeEnt(new ModelTreeEnt(),1.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntityMammoth.class, new RenderMammoth(new ModelMammoth(),3.0f));

		RenderingRegistry.registerEntityRenderingHandler(EntityFox.class, new RenderTameable(new ModelFox(),0.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntityBoar.class, new RenderBoar(new ModelBoar(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMimic.class, new RenderMimic(new ModelMimic(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityCrocodile.class, new RenderCrocodile(new ModelCrocodile(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrog.class, new RenderFrog(new ModelFrog(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPenguin.class, new RenderPenguin(new ModelPenguin(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBeaver.class, new RenderBeaver(new ModelBeaver(),0.5f));

//		RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, new RenderBear(new ModelBear(0),0.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntityPolarBear.class, new RenderBear(new ModelPolarBear(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBrownBear.class, new RenderBear(new ModelBrownBear(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackBear.class, new RenderBear(new ModelBlackBear(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityOstrich.class, new RenderOstrich(new ModelOstrich(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityRhino.class, new RenderRhino(new ModelRhino(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbit.class, new RenderRabbit(new ModelRabbit(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityRedFinch.class, new RenderFinch(new ModelFinch(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlueFinch.class, new RenderFinch(new ModelFinch(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGreenFinch.class, new RenderFinch(new ModelFinch(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGorilla.class, new RenderGorilla(new ModelGorilla(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGiraffe.class, new RenderGiraffe(new ModelGiraffe(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityElephant.class, new RenderElephant(new ModelElephant(),0.5f));


		/*Horse Rendering Register*/
		RenderingRegistry.registerEntityRenderingHandler(EntityHorseBase.class, new RenderHorse(new ModelHorse(),1.0f));

		/*Bird Rendering Register*/
		RenderingRegistry.registerEntityRenderingHandler(EntityEagle.class, new RenderEagle(new ModelEagle(),1.0f));
		RenderingRegistry.registerEntityRenderingHandler(EntityHornBill.class, new RenderHornBill(new ModelHornBill(),1.0f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPelican.class, new RenderPelican(new ModelPelican(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMinotaur.class, new RenderMinotaur(new ModelMinotaur(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityHauntedArmor.class, new RenderHauntedArmor(new ModelHauntedArmor(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityCentipede.class, new RenderCentipede(new ModelCentipede(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFollower.class, new RenderFollower(new ModelFollower(),0.5f));

	}
	
	
	@Override
	//Pre-Init
	public void registerMobSounds(){
        MinecraftForge.EVENT_BUS.register(new SoundHandlerClass());
	}
	
	//Init
	@Override
	public void registerMobSoundEvent(){
        MinecraftForge.EVENT_BUS.register(new SoundHookContainerClass());
	}
	
	@Override
	public TemperatureTicker initializeTempTicker() {
		TemperatureTicker tempTicker = new TemperatureTicker();        
        TickRegistry.registerTickHandler(tempTicker, Side.SERVER );
        TickRegistry.registerTickHandler(new TemperatureTicker(), Side.CLIENT );
        
        TickRegistry.registerTickHandler(new DisplayTemperatureTicker(), Side.CLIENT);
        
        return tempTicker;

	}
}
