package projectzulu.common.mobs.entity;

import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityHorseGrey extends EntityHorseBase {

    public EntityHorseGrey(World par1World) {
        super(par1World);
    }

    @Override
    public String getTexture() {
        if (getSaddled()) {
            this.texture = DefaultProps.mobDiretory + "Horse/horse_grey_saddled.png";
        } else {
            this.texture = DefaultProps.mobDiretory + "Horse/horse_grey.png";
        }
        return super.getTexture();
    }
}
