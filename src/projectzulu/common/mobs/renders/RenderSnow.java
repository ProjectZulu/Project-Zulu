package projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class RenderSnow extends RenderGenericLiving {
    public final ResourceLocation snowTexture;

    public RenderSnow(ModelBase modelBase, float shadowSize, ResourceLocation livingLocation,
            ResourceLocation snowTexture) {
        super(modelBase, shadowSize, livingLocation);
        this.snowTexture = snowTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (BiomeDictionary.isBiomeOfType(entity.worldObj.getBiomeGenForCoords((int) entity.posX, (int) entity.posZ),
                Type.FROZEN)) {
            return snowTexture;
        } else {
            return livingTexture;
        }
    }
}
