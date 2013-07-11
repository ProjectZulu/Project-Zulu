package projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGenericLiving extends RenderLiving {

    public final ResourceLocation livingTexture;

    public RenderGenericLiving(ModelBase modelBase, float shadowSize, ResourceLocation livingTexture) {
        super(modelBase, shadowSize);
        this.livingTexture = livingTexture;
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return livingTexture;
    }
}
