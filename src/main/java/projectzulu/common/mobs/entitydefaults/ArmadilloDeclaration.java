package projectzulu.common.mobs.entitydefaults;

import java.util.HashSet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.EntityProperties;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.models.ModelArmadillo;
import projectzulu.common.mobs.renders.RenderGenericLiving;
import projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmadilloDeclaration extends SpawnableDeclaration {

    public ArmadilloDeclaration() {
        super("Armadillo", 0, EntityArmadillo.class, EnumCreatureType.creature);
        setSpawnProperties(10, 100, 2, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (116 << 16) + (64 << 8) + 33;
        eggColor2 = (60 << 16) + (51 << 8) + 10;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scaleItem, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 4);
        customMobData.entityProperties = new EntityProperties(12f, 3.0f, 0.3f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelArmadillo(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "armadillo.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
        defaultBiomesToSpawn.add("Mountainous Desert");
        defaultBiomesToSpawn.add("Savanna");
        defaultBiomesToSpawn.add("Mountain Ridge");
        defaultBiomesToSpawn.addAll(typeToArray(Type.DESERT));
        return defaultBiomesToSpawn;
    }
}
