package projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entity.EntityGenericRideable;

public class RenderMammoth extends RenderGenericLiving {
    public final ResourceLocation snowSaddle;
    public final ResourceLocation snowWild;
    public final ResourceLocation wildSaddle;

    public RenderMammoth(ModelBase par1ModelBase, float shadowSize) {
        super(par1ModelBase, shadowSize, new ResourceLocation(DefaultProps.mobKey, "mammoth.png"));
        snowWild = new ResourceLocation(DefaultProps.mobKey, "mammoth_snow.png");
        wildSaddle = new ResourceLocation(DefaultProps.mobKey, "mammoth_saddle.png");
        snowSaddle = new ResourceLocation(DefaultProps.mobKey, "mammoth_snow_saddle.png");
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (((EntityGenericRideable) entity).getSaddled()) {
            if (entity.worldObj.getBiomeGenForCoords((int) entity.posX, (int) entity.posZ) == BiomeGenBase.taiga
                    || entity.worldObj.getBiomeGenForCoords((int) entity.posX, (int) entity.posZ) == BiomeGenBase.taigaHills) {
                return snowSaddle;
            } else {
                return wildSaddle;
            }

        } else {
            if (entity.worldObj.getBiomeGenForCoords((int) entity.posX, (int) entity.posZ) == BiomeGenBase.taiga
                    || entity.worldObj.getBiomeGenForCoords((int) entity.posX, (int) entity.posZ) == BiomeGenBase.taigaHills) {
                return snowWild;
            } else {
                return livingTexture;
            }
        }
    }
}
