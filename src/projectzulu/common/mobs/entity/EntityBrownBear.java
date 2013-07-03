package projectzulu.common.mobs.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import cpw.mods.fml.common.Loader;

public class EntityBrownBear extends EntityBear{

	public EntityBrownBear(World par1World) {
		super(par1World);
		setSize(1.5f, 2.1f);
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
	}
	
    /**
     * Set Entity Attack Strength This is overriden by each Entity if deviations from default are desired
     */
    @Override
    protected int getAttackStrength(World par1World) {
        switch (par1World.difficultySetting) {
        case 0:
            return 3;
        case 1:
            return 4;
        case 2:
            return 5;
        case 3:
            return 7;
        default:
            return 3;
        }
    }

    @Override
    public int getMaxHealth() {
        return 20;
    }

    @Override
    public int getTotalArmorValue() {
        return 4;
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID, 1, 4), 1);
        }
        super.dropRareDrop(par1);
    }
}
