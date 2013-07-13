package projectzulu.common.mobs.renders;

import net.minecraft.client.renderer.entity.Render;

/**
 * Interface to hide ClientSide Render in objects on Server when Side.Client is insufficient i.e. abstract methods
 */
public interface RenderWrapper {
    public Render getRender();
}
