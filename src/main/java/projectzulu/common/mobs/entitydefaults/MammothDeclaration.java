package projectzulu.common.mobs.entitydefaults;

import java.util.HashSet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.EntityProperties;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityMammoth;
import projectzulu.common.mobs.models.ModelMammoth;
import projectzulu.common.mobs.renders.RenderMammoth;
import projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MammothDeclaration extends SpawnableDeclaration {

    public MammothDeclaration() {
        super("Mammoth", 8, EntityMammoth.class, EnumCreatureType.creature);
        setSpawnProperties(1, 7, 1, 3);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 4);

        eggColor1 = (20 << 16) + (12 << 8) + 0;
        eggColor2 = (69 << 16) + (42 << 8) + 0;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.furPelt, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.LargeHeart.meta(), 4);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Tusk.meta(), 4);
        customMobData.entityProperties = new EntityProperties(30f, 5.0f, 0.3f, 0.0f, 0.5f, 32.0f).createFromConfig(
                config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderMammoth(new ModelMammoth(), 0.5f);
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
        defaultBiomesToSpawn.add("Tundra");
        defaultBiomesToSpawn.add("Ice Wasteland");
        defaultBiomesToSpawn.add("Glacier");
        defaultBiomesToSpawn.addAll(typeToArray(Type.FROZEN));
        return defaultBiomesToSpawn;
    }
}
