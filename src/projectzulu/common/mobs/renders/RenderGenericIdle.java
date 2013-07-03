package projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import projectzulu.common.mobs.entity.EntityGenericAnimal;
import projectzulu.common.mobs.entity.EntityStates;

public class RenderGenericIdle extends RenderGenericLiving {

    public final ResourceLocation idleTexture;

    public RenderGenericIdle(ModelBase par1ModelBase, float shadowSize, String livingTexture, String idleTexture) {
        super(par1ModelBase, shadowSize, livingTexture);
        this.idleTexture = new ResourceLocation(idleTexture);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        if (((EntityGenericAnimal) entity).getEntityState() == EntityStates.idle) {
            return idleTexture;
        } else {
            return livingTexture;
        }
    }
}
