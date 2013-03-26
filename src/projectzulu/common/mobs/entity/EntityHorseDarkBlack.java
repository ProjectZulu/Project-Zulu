package projectzulu.common.mobs.entity;

import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityHorseDarkBlack extends EntityHorseBase{

	public EntityHorseDarkBlack(World par1World) {
		super(par1World);
	}
	
	@Override
	public String getTexture() {
		if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_black_saddled.png";
		}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_dark_black.png";
		}
		return super.getTexture();
	}
}
