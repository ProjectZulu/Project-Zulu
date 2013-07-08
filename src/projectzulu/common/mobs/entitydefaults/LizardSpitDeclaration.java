package projectzulu.common.mobs.entitydefaults;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.entitydeclaration.CreatureDeclaration;
import projectzulu.common.mobs.entity.EntityLizardSpit;
import projectzulu.common.mobs.renders.RenderLizardSpit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LizardSpitDeclaration extends CreatureDeclaration {

	public LizardSpitDeclaration() {
		super("Lizard Spit", EntityLizardSpit.class, null);
		setRegistrationProperties(128, 3, true);
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {}

	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {}
    
    @Override
    @SideOnly(Side.CLIENT)
    public Render getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderLizardSpit(0.5f);
    }
}