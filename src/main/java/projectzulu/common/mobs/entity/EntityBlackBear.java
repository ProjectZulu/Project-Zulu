package projectzulu.common.mobs.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import cpw.mods.fml.common.Loader;

public class EntityBlackBear extends EntityBear {

    public EntityBlackBear(World par1World) {
        super(par1World);
        setSize(1.0f, 1.35f);
        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false));
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return super.isValidLocation(world, xCoord, yCoord, zCoord)
                && worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue() {
        return 2;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 3), 1);
        }
        super.dropRareDrop(par1);
    }
}
