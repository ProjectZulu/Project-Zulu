package projectzulu.common.mobs.entity;

import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityHorseDarkBrown extends EntityHorseBase{

	public EntityHorseDarkBrown(World par1World) {
		super(par1World);
	}
	
	@Override
	public String getTexture() {
		if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_brown_saddled.png";
		}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_brown.png";
		}
		return super.getTexture();
	}
}
