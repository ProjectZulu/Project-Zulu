package projectzulu.common.mobs.entitydefaults;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityGreenFinch;
import projectzulu.common.mobs.models.ModelFinch;
import projectzulu.common.mobs.renders.RenderGenericLiving;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GreenFinchDeclaration extends SpawnableDeclaration {

    public GreenFinchDeclaration() {
        super("Green Finch", EntityGreenFinch.class, EnumCreatureType.ambient);
        setSpawnProperties(10, 5, 1, 1);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 1);

        eggColor1 = (30 << 16) + (130 << 8) + 0;
        eggColor2 = (164 << 16) + (234 << 8) + 143;

        defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
        defaultBiomesToSpawn.add("Autumn Woods");
        defaultBiomesToSpawn.add("Birch Forest");
        defaultBiomesToSpawn.add("Forested Hills");
        defaultBiomesToSpawn.add("Forested Island");
        defaultBiomesToSpawn.add("Green Hills");
        defaultBiomesToSpawn.add("Redwood Forest");
        defaultBiomesToSpawn.add("Lush Redwoods");
        defaultBiomesToSpawn.add("Temperate Rainforest");
        defaultBiomesToSpawn.add("Woodlands");
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Item.feather, 0, 8);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderLiving getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelFinch(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "finch_green.png"));
    }
}
