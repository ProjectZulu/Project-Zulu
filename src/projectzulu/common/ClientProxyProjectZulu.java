package projectzulu.common;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.blocks.EntityCreeperBlossomPrimed;
import projectzulu.common.blocks.RenderCreeperBlossomPrimed;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.SoundHandlerClass;
import projectzulu.common.core.SoundHookContainerClass;
import projectzulu.common.mobs.BossHealthDisplayTicker;
import projectzulu.common.mobs.EntityArmadillo;
import projectzulu.common.mobs.EntityBeaver;
import projectzulu.common.mobs.EntityBlackBear;
import projectzulu.common.mobs.EntityBlueFinch;
import projectzulu.common.mobs.EntityBoar;
import projectzulu.common.mobs.EntityBrownBear;
import projectzulu.common.mobs.EntityCentipede;
import projectzulu.common.mobs.EntityCrocodile;
import projectzulu.common.mobs.EntityEagle;
import projectzulu.common.mobs.EntityElephant;
import projectzulu.common.mobs.EntityFollower;
import projectzulu.common.mobs.EntityFox;
import projectzulu.common.mobs.EntityFrog;
import projectzulu.common.mobs.EntityGiraffe;
import projectzulu.common.mobs.EntityGorilla;
import projectzulu.common.mobs.EntityGreenFinch;
import projectzulu.common.mobs.EntityHauntedArmor;
import projectzulu.common.mobs.EntityHornBill;
import projectzulu.common.mobs.EntityHorseBase;
import projectzulu.common.mobs.EntityLizard;
import projectzulu.common.mobs.EntityLizardSpit;
import projectzulu.common.mobs.EntityMammoth;
import projectzulu.common.mobs.EntityMimic;
import projectzulu.common.mobs.EntityMinotaur;
import projectzulu.common.mobs.EntityMummy;
import projectzulu.common.mobs.EntityMummyPharaoh;
import projectzulu.common.mobs.EntityOstrich;
import projectzulu.common.mobs.EntityPelican;
import projectzulu.common.mobs.EntityPenguin;
import projectzulu.common.mobs.EntityPolarBear;
import projectzulu.common.mobs.EntityRabbit;
import projectzulu.common.mobs.EntityRedFinch;
import projectzulu.common.mobs.EntityRhino;
import projectzulu.common.mobs.EntitySandWorm;
import projectzulu.common.mobs.EntityTreeEnt;
import projectzulu.common.mobs.EntityVulture;
import projectzulu.common.mobs.ModelArmadillo;
import projectzulu.common.mobs.ModelBeaver;
import projectzulu.common.mobs.ModelBlackBear;
import projectzulu.common.mobs.ModelBoar;
import projectzulu.common.mobs.ModelBrownBear;
import projectzulu.common.mobs.ModelCentipede;
import projectzulu.common.mobs.ModelCrocodile;
import projectzulu.common.mobs.ModelEagle;
import projectzulu.common.mobs.ModelElephant;
import projectzulu.common.mobs.ModelFinch;
import projectzulu.common.mobs.ModelFollower;
import projectzulu.common.mobs.ModelFox;
import projectzulu.common.mobs.ModelFrog;
import projectzulu.common.mobs.ModelGiraffe;
import projectzulu.common.mobs.ModelGorilla;
import projectzulu.common.mobs.ModelHauntedArmor;
import projectzulu.common.mobs.ModelHornBill;
import projectzulu.common.mobs.ModelHorse;
import projectzulu.common.mobs.ModelLizard;
import projectzulu.common.mobs.ModelMammoth;
import projectzulu.common.mobs.ModelMimic;
import projectzulu.common.mobs.ModelMinotaur;
import projectzulu.common.mobs.ModelMummy;
import projectzulu.common.mobs.ModelMummyPharaoh;
import projectzulu.common.mobs.ModelOstrich;
import projectzulu.common.mobs.ModelPelican;
import projectzulu.common.mobs.ModelPenguin;
import projectzulu.common.mobs.ModelPolarBear;
import projectzulu.common.mobs.ModelRabbit;
import projectzulu.common.mobs.ModelRhino;
import projectzulu.common.mobs.ModelSandWorm;
import projectzulu.common.mobs.ModelTreeEnt;
import projectzulu.common.mobs.ModelVulture;
import projectzulu.common.mobs.RenderCentipede;
import projectzulu.common.mobs.RenderEagle;
import projectzulu.common.mobs.RenderFollower;
import projectzulu.common.mobs.RenderHauntedArmor;
import projectzulu.common.mobs.RenderHornBill;
import projectzulu.common.mobs.RenderHorse;
import projectzulu.common.mobs.RenderLizardSpit;
import projectzulu.common.mobs.RenderMinotaur;
import projectzulu.common.mobs.RenderMummyPharaoh;
import projectzulu.common.mobs.RenderPelican;
import projectzulu.common.mobs.RenderTameable;
import projectzulu.common.temperature.DisplayTemperatureTicker;
import projectzulu.common.temperature.TemperatureTicker;
import cpw.mods.fml.client.registry.ClientRegistry;
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
