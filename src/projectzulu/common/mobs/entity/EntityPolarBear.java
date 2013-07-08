package projectzulu.common.mobs.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import cpw.mods.fml.common.Loader;

public class EntityPolarBear extends EntityBear {

    public EntityPolarBear(World par1World) {
        super(par1World);
        setSize(2.0f, 2.7f);
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false, 13f));
    }

    /**
     * Set Entity Attack Strength This is overriden by each Entity if deviations from default are desired
     **/
    @Override
    protected int getAttackStrength(World par1World) {
        switch (par1World.difficultySetting) {
        case 0:
            return 3;
        case 1:
            return 5;
        case 2:
            return 6;
        case 3:
            return 7;
        default:
            return 3;
        }
    }

    @Override
    public int getMaxHealth() {
        return 25;
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue() {
        return 6;
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID, 1, 5), 1);
        }
        super.dropRareDrop(par1);
    }
}
