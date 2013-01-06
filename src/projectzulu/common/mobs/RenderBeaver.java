package projectzulu.common.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderBeaver extends RenderLiving
{
    private ModelBeaver holdModel;
	
    public RenderBeaver(ModelBase modelBase, float par1){
        super(modelBase, par1);
        holdModel = (ModelBeaver)modelBase;
    }

    public void renderBeaver(EntityBeaver par1EntityBeaver, double par2, double par4, double par6, float par8, float par9){
        super.doRenderLiving(par1EntityBeaver, par2, par4, par6, par8, par9);
    }    

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9){
        this.renderBeaver((EntityBeaver)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9){
        this.renderBeaver((EntityBeaver)par1Entity, par2, par4, par6, par8, par9);
    }
}


