package projectzulu.common.mobs.entitydefaults;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityRhino;
import projectzulu.common.mobs.models.ModelFinch;
import projectzulu.common.mobs.models.ModelRhino;
import projectzulu.common.mobs.renders.RenderGenericLiving;

public class RhinoDeclaration extends SpawnableDeclaration {

    public RhinoDeclaration() {
        super("Rhino", EntityRhino.class, EnumCreatureType.creature);
        setSpawnProperties(10, 100, 1, 2);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 3);

        eggColor1 = (95 << 16) + (93 << 8) + 94;
        eggColor2 = (173 << 16) + (170 << 8) + 172;
        defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
        defaultBiomesToSpawn.add("Savanna");
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.LargeHeart.meta(), 4);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Tusk.meta(), 8);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderLiving getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelRhino(), 0.5f, DefaultProps.mobDiretory + "rhino.png");
    }
}
