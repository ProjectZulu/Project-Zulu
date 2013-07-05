package projectzulu.common.mobs.renders;

import projectzulu.common.mobs.entity.EntityGenericAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;

public class RenderGenericHorse extends RenderGenericLiving {
    
    public final ResourceLocation saddledTexture;

    public RenderGenericHorse(ModelBase modelBase, float shadowSize, ResourceLocation wildTexture,
            ResourceLocation saddledTexture) {
        super(modelBase, shadowSize, wildTexture);
        this.saddledTexture = saddledTexture;
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        if (entity instanceof EntityGenericAnimal) {
            EntityGenericAnimal animal = (EntityGenericAnimal) entity;
            if (animal.getSaddled()) {
                return saddledTexture;
            }
        }
        return livingTexture;
    }
}
