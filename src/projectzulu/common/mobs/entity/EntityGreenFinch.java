package projectzulu.common.mobs.entity;

import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityGreenFinch extends EntityFinch{

	public EntityGreenFinch(World par1World) {
		super(par1World);
	}
	
	@Override
	public String getTexture() {
		this.texture = DefaultProps.mobDiretory + "finch_green.png";
		return this.texture;
	}
}
