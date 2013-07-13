package projectzulu.common.mobs.entitydefaults;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityTreeEnt;
import projectzulu.common.mobs.models.ModelTreeEnt;
import projectzulu.common.mobs.renders.RenderSnow;
import projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TreeEntDeclaration extends SpawnableDeclaration {

    public TreeEntDeclaration() {
        super("TreeEnt", EntityTreeEnt.class, EnumCreatureType.creature);
        setSpawnProperties(1, 7, 1, 1);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (17 << 16) + (6 << 8) + 3;
        eggColor2 = (83 << 16) + (56 << 8) + 29;

        defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
        defaultBiomesToSpawn.add("Alpine");
        defaultBiomesToSpawn.add("Mountain Taiga");
        defaultBiomesToSpawn.add("Snow Forest");
        defaultBiomesToSpawn.add("Snowy Rainforest");
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Block.wood, 1, 8);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Bark.meta(), 4);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.BlackLichen.meta(), 4);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderSnow(new ModelTreeEnt(), 0.5f, new ResourceLocation(DefaultProps.mobKey, "treeent.png"),
                new ResourceLocation(DefaultProps.mobKey, "treeent_snow.png"));
    }
}