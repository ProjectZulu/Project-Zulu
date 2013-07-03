package projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;

public class RenderGenericLiving extends RenderLiving {

    public final ResourceLocation livingTexture;

    public RenderGenericLiving(ModelBase modelBase, float shadowSize, String textureLocation) {
        super(modelBase, shadowSize);
        livingTexture = new ResourceLocation(textureLocation);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return livingTexture;
    }
}
