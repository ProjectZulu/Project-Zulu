package projectzulu.common;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.blocks.EntityCreeperBlossomPrimed;
import projectzulu.common.blocks.RenderCreeperBlossomPrimed;
import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.SoundHandlerClass;
import projectzulu.common.core.SoundHookContainerClass;
import projectzulu.common.mobs.BossHealthDisplayTicker;
import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.entity.EntityBeaver;
import projectzulu.common.mobs.entity.EntityBlackBear;
import projectzulu.common.mobs.entity.EntityBlueFinch;
import projectzulu.common.mobs.entity.EntityBoar;
import projectzulu.common.mobs.entity.EntityBrownBear;
import projectzulu.common.mobs.entity.EntityCentipede;
import projectzulu.common.mobs.entity.EntityCrocodile;
import projectzulu.common.mobs.entity.EntityEagle;
import projectzulu.common.mobs.entity.EntityElephant;
import projectzulu.common.mobs.entity.EntityFollower;
import projectzulu.common.mobs.entity.EntityFox;
import projectzulu.common.mobs.entity.EntityFrog;
import projectzulu.common.mobs.entity.EntityGiraffe;
import projectzulu.common.mobs.entity.EntityGorilla;
import projectzulu.common.mobs.entity.EntityGreenFinch;
import projectzulu.common.mobs.entity.EntityHauntedArmor;
import projectzulu.common.mobs.entity.EntityHornBill;
import projectzulu.common.mobs.entity.EntityHorseBase;
import projectzulu.common.mobs.entity.EntityLizard;
import projectzulu.common.mobs.entity.EntityLizardSpit;
import projectzulu.common.mobs.entity.EntityMammoth;
import projectzulu.common.mobs.entity.EntityMimic;
import projectzulu.common.mobs.entity.EntityMinotaur;
import projectzulu.common.mobs.entity.EntityMummy;
import projectzulu.common.mobs.entity.EntityMummyPharaoh;
import projectzulu.common.mobs.entity.EntityOstrich;
import projectzulu.common.mobs.entity.EntityPelican;
import projectzulu.common.mobs.entity.EntityPenguin;
import projectzulu.common.mobs.entity.EntityPolarBear;
import projectzulu.common.mobs.entity.EntityRabbit;
import projectzulu.common.mobs.entity.EntityRedFinch;
import projectzulu.common.mobs.entity.EntityRhino;
import projectzulu.common.mobs.entity.EntitySandWorm;
import projectzulu.common.mobs.entity.EntityTreeEnt;
import projectzulu.common.mobs.entity.EntityVulture;
import projectzulu.common.mobs.models.ModelArmadillo;
import projectzulu.common.mobs.models.ModelBeaver;
import projectzulu.common.mobs.models.ModelBlackBear;
import projectzulu.common.mobs.models.ModelBoar;
import projectzulu.common.mobs.models.ModelBrownBear;
import projectzulu.common.mobs.models.ModelCentipede;
import projectzulu.common.mobs.models.ModelCrocodile;
import projectzulu.common.mobs.models.ModelEagle;
import projectzulu.common.mobs.models.ModelElephant;
import projectzulu.common.mobs.models.ModelFinch;
import projectzulu.common.mobs.models.ModelFollower;
import projectzulu.common.mobs.models.ModelFox;
import projectzulu.common.mobs.models.ModelFrog;
import projectzulu.common.mobs.models.ModelGiraffe;
import projectzulu.common.mobs.models.ModelGorilla;
import projectzulu.common.mobs.models.ModelHauntedArmor;
import projectzulu.common.mobs.models.ModelHornBill;
import projectzulu.common.mobs.models.ModelHorse;
import projectzulu.common.mobs.models.ModelLizard;
import projectzulu.common.mobs.models.ModelMammoth;
import projectzulu.common.mobs.models.ModelMimic;
import projectzulu.common.mobs.models.ModelMinotaur;
import projectzulu.common.mobs.models.ModelMummy;
import projectzulu.common.mobs.models.ModelMummyPharaoh;
import projectzulu.common.mobs.models.ModelOstrich;
import projectzulu.common.mobs.models.ModelPelican;
import projectzulu.common.mobs.models.ModelPenguin;
import projectzulu.common.mobs.models.ModelPolarBear;
import projectzulu.common.mobs.models.ModelRabbit;
import projectzulu.common.mobs.models.ModelRhino;
import projectzulu.common.mobs.models.ModelSandWorm;
import projectzulu.common.mobs.models.ModelTreeEnt;
import projectzulu.common.mobs.models.ModelVulture;
import projectzulu.common.mobs.renders.RenderLizardSpit;
import projectzulu.common.mobs.renders.RenderMummyPharaoh;
import projectzulu.common.mobs.renders.RenderTameable;
import projectzulu.common.temperature.DisplayTemperatureTicker;
import projectzulu.common.temperature.TemperatureTicker;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyProjectZulu extends CommonProxyProjectZulu{
    
 	/**
 	 * Called during Init
 	 */
 	@Override
 	public void registerTileEntitySpecialRender(Class <? extends TileEntity> tileEntityClass, String specialRenderer){
 		try {
			Object renderer = Class.forName(specialRenderer).newInstance();
 			ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, (TileEntitySpecialRenderer) renderer);
		} catch (InstantiationException e) {
			ProjectZuluLog.severe("Failed Registering TileEntitySpecialRenderer from String %s due to %s", specialRenderer, e.getClass().getSimpleName());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			ProjectZuluLog.severe("Failed Registering TileEntitySpecialRenderer from String %s due to %s", specialRenderer, e.getClass().getSimpleName());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			ProjectZuluLog.severe("Failed Registering TileEntitySpecialRenderer from String %s due to %s", specialRenderer, e.getClass().getSimpleName());
			e.printStackTrace();
		} catch (ClassCastException e) {
			ProjectZuluLog.severe("Failed Registering TileEntitySpecialRenderer from String %s due to %s", specialRenderer, e.getClass().getSimpleName());
			e.printStackTrace();
		}
 	}
 	
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
		RenderingRegistry.registerEntityRenderingHandler(EntityHorseBase.class, new RenderLiving(new ModelHorse(),1.0f));

		/*Bird Rendering Register*/
		RenderingRegistry.registerEntityRenderingHandler(EntityEagle.class, new RenderLiving(new ModelEagle(),1.0f));
		RenderingRegistry.registerEntityRenderingHandler(EntityHornBill.class, new RenderLiving(new ModelHornBill(),1.0f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPelican.class, new RenderLiving(new ModelPelican(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMinotaur.class, new RenderLiving(new ModelMinotaur(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityHauntedArmor.class, new RenderLiving(new ModelHauntedArmor(),0.5f));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityCentipede.class, new RenderLiving(new ModelCentipede(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFollower.class, new RenderLiving(new ModelFollower(),0.5f));
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
