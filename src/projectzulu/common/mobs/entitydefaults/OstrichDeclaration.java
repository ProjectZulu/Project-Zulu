package projectzulu.common.mobs.entitydefaults;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityOstrich;
import projectzulu.common.mobs.models.ModelMummy;
import projectzulu.common.mobs.models.ModelOstrich;
import projectzulu.common.mobs.renders.RenderGenericLiving;

public class OstrichDeclaration extends SpawnableDeclaration {

    public OstrichDeclaration() {
        super("Ostrich", EntityOstrich.class, EnumCreatureType.creature);
        setSpawnProperties(10, 100, 1, 2);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (25 << 16) + (18 << 8) + 14;
        eggColor2 = (232 << 16) + (107 << 8) + 101;

        defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
        defaultBiomesToSpawn.add("Savanna");
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Item.beefRaw, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Item.feather, 0, 8);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Talon.meta(), 4);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 4);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderLiving getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelOstrich(), 0.5f, DefaultProps.mobDiretory + "ostrich.png");
    }
}
