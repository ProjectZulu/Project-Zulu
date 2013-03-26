package projectzulu.common.mobs.entity;

import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityHorseBrown extends EntityHorseBase{

	public EntityHorseBrown(World par1World) {
		super(par1World);
	}
	
    @Override
    public String getTexture() {
        if (getSaddled()) {
            this.texture = DefaultProps.mobDiretory + "Horse/horse_brown_saddled.png";
        } else {
            this.texture = DefaultProps.mobDiretory + "Horse/horse_brown.png";
        }
        return super.getTexture();
    }
}
