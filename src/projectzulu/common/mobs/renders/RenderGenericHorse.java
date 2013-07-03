package projectzulu.common.mobs.renders;

import projectzulu.common.mobs.entity.EntityGenericAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;

public class RenderGenericHorse extends RenderLiving {
    public final ResourceLocation saddledTexture;
    public final ResourceLocation wildTexture;
    public RenderGenericHorse(ModelBase modelBase, float shadowSize, String wildTexture, String saddledTexture) {
        super(modelBase, shadowSize);
        this.wildTexture = new ResourceLocation(wildTexture);
        this.saddledTexture = new ResourceLocation(saddledTexture);
    }
    
    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        if (entity instanceof EntityGenericAnimal) {
            EntityGenericAnimal animal = (EntityGenericAnimal) entity;
            if (animal.getSaddled()) {
                return saddledTexture;
            }
        }
        return wildTexture;
    }
}
