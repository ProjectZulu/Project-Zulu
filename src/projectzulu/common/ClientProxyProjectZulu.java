package projectzulu.common;

import net.minecraft.client.renderer.entity.RenderLiving;
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
import projectzulu.common.dungeon.TileEntityLimitedMobSpawner;
import projectzulu.common.dungeon.TileEntityLimitedMobSpawnerRenderer;
import projectzulu.common.mobs.*;
import projectzulu.common.temperature.DisplayTemperatureTicker;
import projectzulu.common.temperature.TemperatureTicker;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyProjectZulu extends CommonProxyProjectZulu{
	
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
 	public void registerTileEntitySpecialRender(Class <? extends TileEntity> tileEntityClass, String specialRenderer){
 		try {
			Object renderer = Class.forName(specialRenderer).newInstance();
 			ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, (TileEntitySpecialRenderer) renderer);
		} catch (InstantiationException e) {
			ProjectZuluLog.warning("Failed Registering TileEntitySpecialRenderer from String %s", specialRenderer);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			ProjectZuluLog.warning("Failed Registering TileEntitySpecialRenderer from String %s", specialRenderer);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			ProjectZuluLog.warning("Failed Registering TileEntitySpecialRenderer from String %s", specialRenderer);
			e.printStackTrace();
		} catch (ClassCastException e) {
			ProjectZuluLog.warning("Failed Registering TileEntitySpecialRenderer from String %s", specialRenderer);
			e.printStackTrace();
		}
 	}
	
	@Override
	public void registerBlockRenders(){
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperBlossomPrimed.class, new RenderCreeperBlossomPrimed());
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
	public void registerMobRenders(){
		RenderingRegistry.registerEntityRenderingHandler(EntityArmadillo.class, new RenderLiving(new ModelArmadillo(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySandWorm.class, new RenderLiving(new ModelSandWorm(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityLizard.class, new RenderLiving(new ModelLizard(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityLizardSpit.class, new RenderLizardSpit(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderLiving(new ModelMummy(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMummyPharaoh.class, new RenderMummyPharaoh(new ModelMummyPharaoh(),0.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntityVulture.class, new RenderLiving(new ModelVulture(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTreeEnt.class, new RenderLiving(new ModelTreeEnt(),1.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntityMammoth.class, new RenderLiving(new ModelMammoth(),3.0f));

		RenderingRegistry.registerEntityRenderingHandler(EntityFox.class, new RenderTameable(new ModelFox(),0.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntityBoar.class, new RenderLiving(new ModelBoar(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMimic.class, new RenderLiving(new ModelMimic(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityCrocodile.class, new RenderLiving(new ModelCrocodile(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrog.class, new RenderLiving(new ModelFrog(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPenguin.class, new RenderLiving(new ModelPenguin(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBeaver.class, new RenderLiving(new ModelBeaver(),0.5f));

//		RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, new RenderBear(new ModelBear(0),0.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntityPolarBear.class, new RenderLiving(new ModelPolarBear(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBrownBear.class, new RenderLiving(new ModelBrownBear(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackBear.class, new RenderLiving(new ModelBlackBear(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityOstrich.class, new RenderLiving(new ModelOstrich(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityRhino.class, new RenderLiving(new ModelRhino(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbit.class, new RenderLiving(new ModelRabbit(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityRedFinch.class, new RenderLiving(new ModelFinch(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlueFinch.class, new RenderLiving(new ModelFinch(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGreenFinch.class, new RenderLiving(new ModelFinch(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGorilla.class, new RenderLiving(new ModelGorilla(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityGiraffe.class, new RenderLiving(new ModelGiraffe(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityElephant.class, new RenderLiving(new ModelElephant(),0.5f));


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
	public void registerMobSounds(){
        MinecraftForge.EVENT_BUS.register(new SoundHandlerClass());
	}
	
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
