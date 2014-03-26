package projectzulu.common;

import java.io.File;

import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemBlockManager;
import projectzulu.common.core.terrain.FeatureGenerator;
import projectzulu.common.mobs.entitydefaults.AlligatorDeclaration;
import projectzulu.common.mobs.entitydefaults.ArmadilloDeclaration;
import projectzulu.common.mobs.entitydefaults.BearBlackDeclaration;
import projectzulu.common.mobs.entitydefaults.BearBrownDeclaration;
import projectzulu.common.mobs.entitydefaults.BearPolarDeclaration;
import projectzulu.common.mobs.entitydefaults.BeaverDeclaration;
import projectzulu.common.mobs.entitydefaults.BlueFinchDeclaration;
import projectzulu.common.mobs.entitydefaults.BoarDeclaration;
import projectzulu.common.mobs.entitydefaults.CentipedeDeclaration;
import projectzulu.common.mobs.entitydefaults.EagleDeclaration;
import projectzulu.common.mobs.entitydefaults.ElephantDeclaration;
import projectzulu.common.mobs.entitydefaults.FollowerDeclaration;
import projectzulu.common.mobs.entitydefaults.FoxDeclaration;
import projectzulu.common.mobs.entitydefaults.FrogDeclaration;
import projectzulu.common.mobs.entitydefaults.GiraffeDeclaration;
import projectzulu.common.mobs.entitydefaults.GorillaDeclaration;
import projectzulu.common.mobs.entitydefaults.GreenFinchDeclaration;
import projectzulu.common.mobs.entitydefaults.HauntedArmorDeclaration;
import projectzulu.common.mobs.entitydefaults.HornbillDeclaration;
import projectzulu.common.mobs.entitydefaults.HorseBeigeDeclaration;
import projectzulu.common.mobs.entitydefaults.HorseBlackDeclaration;
import projectzulu.common.mobs.entitydefaults.HorseBrownDeclaration;
import projectzulu.common.mobs.entitydefaults.HorseDarkBlackDeclaration;
import projectzulu.common.mobs.entitydefaults.HorseDarkBrownDeclaration;
import projectzulu.common.mobs.entitydefaults.HorseGreyDeclaration;
import projectzulu.common.mobs.entitydefaults.HorseRandomDeclaration;
import projectzulu.common.mobs.entitydefaults.HorseWhiteDeclaration;
import projectzulu.common.mobs.entitydefaults.LizardDeclaration;
import projectzulu.common.mobs.entitydefaults.LizardSpitDeclaration;
import projectzulu.common.mobs.entitydefaults.MammothDeclaration;
import projectzulu.common.mobs.entitydefaults.MimicDeclaration;
import projectzulu.common.mobs.entitydefaults.MinotaurDeclaration;
import projectzulu.common.mobs.entitydefaults.MummyDeclaration;
import projectzulu.common.mobs.entitydefaults.OstrichDeclaration;
import projectzulu.common.mobs.entitydefaults.PelicanDeclaration;
import projectzulu.common.mobs.entitydefaults.PenguinDeclaration;
import projectzulu.common.mobs.entitydefaults.PharaohDeclaration;
import projectzulu.common.mobs.entitydefaults.RabbitDeclaration;
import projectzulu.common.mobs.entitydefaults.RedFinchDeclaration;
import projectzulu.common.mobs.entitydefaults.RhinoDeclaration;
import projectzulu.common.mobs.entitydefaults.SandwormDeclaration;
import projectzulu.common.mobs.entitydefaults.TreeEntDeclaration;
import projectzulu.common.mobs.entitydefaults.VultureDeclaration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ProjectZulu_Mobs extends BaseModule {

    @Override
    public String getIdentifier() {
        return DefaultProps.MobsModId;
    }

    @Override
    public void registration(CustomEntityManager manager) {
        manager.addEntity(new ArmadilloDeclaration(), new SandwormDeclaration(), new LizardDeclaration(),
                new LizardSpitDeclaration(), new PharaohDeclaration(), new MummyDeclaration(),
                new VultureDeclaration(), new TreeEntDeclaration(), new MammothDeclaration(), new FoxDeclaration(),
                new BoarDeclaration(), new MimicDeclaration(), new AlligatorDeclaration(), new FrogDeclaration(),
                new PenguinDeclaration(), new BeaverDeclaration(), new BearBlackDeclaration(),
                new BearBrownDeclaration(), new BearPolarDeclaration(), new OstrichDeclaration(),
                new RhinoDeclaration(), new RabbitDeclaration(), new RedFinchDeclaration(),
                new GreenFinchDeclaration(), new BlueFinchDeclaration(), new GorillaDeclaration(),
                new GiraffeDeclaration(), new ElephantDeclaration(), new HorseBeigeDeclaration(),
                new HorseBlackDeclaration(), new HorseBrownDeclaration(), new HorseDarkBlackDeclaration(),
                new HorseDarkBrownDeclaration(), new HorseGreyDeclaration(), new HorseWhiteDeclaration(),
                new EagleDeclaration(), new HornbillDeclaration(), new PelicanDeclaration(), new MinotaurDeclaration(),
                new HauntedArmorDeclaration(), new CentipedeDeclaration(), new FollowerDeclaration(),
                new HorseRandomDeclaration());
    }
}
