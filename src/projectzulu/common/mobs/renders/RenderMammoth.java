package projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.mobs.entity.EntityGenericRideable;

public class RenderMammoth extends RenderLiving {
    public final ResourceLocation snowSaddle;
    public final ResourceLocation snowWild;
    public final ResourceLocation wild;
    public final ResourceLocation wildSaddle;

    public RenderMammoth(ModelBase par1ModelBase, float shadowSize) {
        super(par1ModelBase, shadowSize);
        wild = new ResourceLocation("mammoth.png");
        snowWild = new ResourceLocation("mammoth_snow.png");
        wildSaddle = new ResourceLocation("mammoth_saddle.png");
        snowSaddle = new ResourceLocation("mammoth_snow_saddle.png");
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
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
                return wild;
            }
        }
    }
}
