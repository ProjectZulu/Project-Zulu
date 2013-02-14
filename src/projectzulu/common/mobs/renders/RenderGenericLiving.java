package projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;

/**
 * Identical Substitute to Renderliving so that string references can be made to RenderLiving while ignoreing obfuscation
 */
public class RenderGenericLiving extends RenderLiving{

	public RenderGenericLiving(ModelBase modelBase, float shadowSize) {
		super(modelBase, shadowSize);
	}
}
