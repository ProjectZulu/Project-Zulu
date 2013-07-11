package projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public class RenderMimic extends RenderGenericLiving {
    public static final ResourceLocation normalChest = new ResourceLocation("textures/entity/chest/normal.png");

    public RenderMimic(ModelBase modelBase, float shadowSize, ResourceLocation textureLocation) {
        super(modelBase, shadowSize, textureLocation);
    }
}
