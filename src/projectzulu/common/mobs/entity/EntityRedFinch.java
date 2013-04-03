package projectzulu.common.mobs.entity;

import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityRedFinch extends EntityFinch{
	
	public EntityRedFinch(World par1World) {
		super(par1World);
	}
	
	@Override
	public String getTexture() {
		this.texture = DefaultProps.mobDiretory + "finch_red.png";
		return super.getTexture();
	}
}
