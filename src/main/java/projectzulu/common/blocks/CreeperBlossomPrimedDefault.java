package projectzulu.common.blocks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.config.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.entitydeclaration.CreatureDeclaration;
import projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreeperBlossomPrimedDefault extends CreatureDeclaration {

    public CreeperBlossomPrimedDefault() {
        super("CreeperBlossomPrimed", 43, EntityCreeperBlossomPrimed.class, null);
        setRegistrationProperties(128, 3, true);
    }

    @Override
    public void loadCreaturesFromConfig(Configuration config) {
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderCreeperBlossomPrimed(0.5f);
    }
}
