package projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import projectzulu.common.mobs.entity.EntityGenericAnimal;
import projectzulu.common.mobs.entity.EntityStates;

public class RenderGenericIdle extends RenderGenericLiving {

    public final ResourceLocation idleTexture;

    public RenderGenericIdle(ModelBase par1ModelBase, float shadowSize, ResourceLocation livingTexture,
            ResourceLocation idleTexture) {
        super(par1ModelBase, shadowSize, livingTexture);
        this.idleTexture = idleTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (((EntityGenericAnimal) entity).getEntityState() == EntityStates.idle) {
            return idleTexture;
        } else {
            return livingTexture;
        }
    }
}
