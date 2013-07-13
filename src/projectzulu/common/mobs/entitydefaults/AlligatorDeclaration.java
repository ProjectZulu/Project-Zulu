package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityCrocodile;
import projectzulu.common.mobs.models.ModelCrocodile;
import projectzulu.common.mobs.renders.RenderGenericLiving;
import projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlligatorDeclaration extends SpawnableDeclaration{
	
	public AlligatorDeclaration(){
		super("Alligator", EntityCrocodile.class, EnumCreatureType.creature);		
		setSpawnProperties(10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setDropAmount(0, 3);
		
		eggColor1 = (32 << 16) + (39 << 8) + 33;
		eggColor2 = (52 << 16) + (65 << 8) + 54;
		defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);	
		defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName);
		defaultBiomesToSpawn.add("Green Swamplands"); 	
		defaultBiomesToSpawn.add("Marsh");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.beefRaw, 0, 5);		
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scaleItem, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems, ItemGenerics.Properties.Gill.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems, ItemGenerics.Properties.LargeHeart.meta(), 4);
		super.outputDataToList(config, customMobData);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelCrocodile(), 0.5f, new ResourceLocation(DefaultProps.mobKey, "crocodile.png"));
    }
}