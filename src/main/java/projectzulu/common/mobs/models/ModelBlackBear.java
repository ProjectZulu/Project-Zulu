package projectzulu.common.mobs.models;

import net.minecraft.entity.Entity;

public class ModelBlackBear extends ModelBear {

    public ModelBlackBear() {
	super(0);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, 1.0f * f5);
    }
}
