package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.EggableDeclaration;
import projectzulu.common.core.entitydeclaration.EntityProperties;
import projectzulu.common.mobs.entity.EntityMimic;
import projectzulu.common.mobs.models.ModelMimic;
import projectzulu.common.mobs.renders.RenderMimic;
import projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MimicDeclaration extends EggableDeclaration {

    public MimicDeclaration() {
        super("Mimic", 11, EntityMimic.class, null);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 1);
        shouldDespawn = false;

        eggColor1 = (171 << 16) + (121 << 8) + 45;
        eggColor2 = (143 << 16) + (105 << 8) + 29;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Ectoplasm.meta(), 5);
        customMobData.entityProperties = new EntityProperties(20f, 4.0f, 0.4f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderMimic(new ModelMimic(), 0.5f, new ResourceLocation(DefaultProps.mobKey, "mimicchest.png"));
    }
}