package projectzulu.common.mobs.entitydefaults;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.EggableDeclaration;
import projectzulu.common.mobs.entity.EntityMimic;
import projectzulu.common.mobs.models.ModelMammoth;
import projectzulu.common.mobs.models.ModelMimic;
import projectzulu.common.mobs.renders.RenderGenericLiving;
import projectzulu.common.mobs.renders.RenderMammoth;
import projectzulu.common.mobs.renders.RenderMimic;

public class MimicDeclaration extends EggableDeclaration {

    public MimicDeclaration() {
        super("Mimic", EntityMimic.class, null);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 1);

        eggColor1 = (171 << 16) + (121 << 8) + 45;
        eggColor2 = (143 << 16) + (105 << 8) + 29;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Ectoplasm.meta(), 5);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderLiving getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderMimic(new ModelMimic(), 0.5f, new ResourceLocation(DefaultProps.mobKey, "mimicchest.png"));
    }
}