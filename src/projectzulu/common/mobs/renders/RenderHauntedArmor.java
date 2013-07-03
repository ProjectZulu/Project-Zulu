package projectzulu.common.mobs.renders;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderHauntedArmor extends RenderLiving {
    private static ResourceLocation goldArmor;
    private static ResourceLocation ironArmor;
    private static final Map<String, ResourceLocation> resourcelocations = Maps.newHashMap();

    public RenderHauntedArmor(ModelBase par1ModelBase, float shadowSize) {
        super(par1ModelBase, shadowSize);
    }
    
    /** 
     * @param entity Entity wearing the armor
     * @param stack ItemStack for the armor
     * @param slot Slot ID that the item is in
     * @param type Subtype, can be null or "overlay"
     * @return ResourceLocation pointing at the armor's texture
     */
    public static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
        ItemArmor item = (ItemArmor) stack.getItem();
        String textureLocation = String.format("textures/models/armor/%s_layer_%d%s.png",
                RenderBiped.bipedArmorFilenamePrefix[item.renderIndex], (slot == 2 ? 2 : 1),
                type == null ? "" : String.format("_%s", type));

        textureLocation = ForgeHooksClient.getArmorTexture(entity, stack, textureLocation, slot, (slot == 2 ? 2 : 1),
                type);
        ResourceLocation resourcelocation = (ResourceLocation) resourcelocations.get(textureLocation);

        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(textureLocation);
            resourcelocations.put(textureLocation, resourcelocation);
        }

        return resourcelocation;
    }
    
    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return null;
    }
}
