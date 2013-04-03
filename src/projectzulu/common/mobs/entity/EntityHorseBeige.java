package projectzulu.common.mobs.entity;

import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityHorseBeige extends EntityHorseBase{

	public EntityHorseBeige(World par1World) {
		super(par1World);
	}
	
	@Override
	public String getTexture() {
		if(getSaddled()){
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige_saddled.png";
		}else{
				this.texture = DefaultProps.mobDiretory + "Horse/horse_beige.png";
		}
		return super.getTexture();
	}
}
