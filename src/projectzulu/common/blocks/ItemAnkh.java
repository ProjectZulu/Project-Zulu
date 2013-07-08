package projectzulu.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAnkh extends Item {

    /**
     * 
     * @param i
     * @param name Name to use as base for Unlocalized name
     */
    public ItemAnkh(int i, String name) {
        super(i);
        maxStackSize = 1;
        setMaxDamage(200);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = true;
        setUnlocalizedName(DefaultProps.blockKey + ":" + name.toLowerCase());
        func_111206_d(DefaultProps.blockKey + ":" + name.toLowerCase());
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        if (par3EntityPlayer.func_110143_aJ() > 1f) {
            shootFireball(par2World, par3EntityPlayer);
            par3EntityPlayer.heal(-1);
        }
        return par1ItemStack;
    }

    public void shootFireball(World par2World, EntityPlayer par3EntityPlayer) {

        if (!par2World.isRemote) {
            int holditemRand = itemRand.nextInt(10) - 5;
            double sourcePositionX = par3EntityPlayer.posX + holditemRand;
            // double sourcePositionY = par3EntityPlayer.posY+30;
            double sourcePositionY = par2World.getActualHeight() - 15;
            holditemRand = itemRand.nextInt(10) - 5;
            double sourcePositionZ = par3EntityPlayer.posZ + holditemRand;

            double var11 = par3EntityPlayer.posX - sourcePositionX;
            double var13 = par3EntityPlayer.boundingBox.minY + (double) (par3EntityPlayer.height / 2.0F)
                    - (sourcePositionY + (double) (par3EntityPlayer.height / 2.0F));
            double var15 = par3EntityPlayer.posZ - sourcePositionZ;
            par3EntityPlayer.renderYawOffset = par3EntityPlayer.rotationYaw = -((float) Math.atan2(var11, var15))
                    * 180.0F / (float) Math.PI;

            par2World.playAuxSFXAtEntity((EntityPlayer) null, 1008, (int) par3EntityPlayer.posX,
                    (int) par3EntityPlayer.posY, (int) par3EntityPlayer.posZ, 0);
            EntityLargeFireball var17 = new EntityLargeFireball(par2World, par3EntityPlayer, var11, var13, var15);
            double var18 = 1.0D;
            Vec3 var20 = par3EntityPlayer.getLook(1.0F);
            var17.posX = sourcePositionX + var20.xCoord * var18;
            var17.posY = sourcePositionY + (double) (par3EntityPlayer.height / 2.0F) + 0.5D;
            var17.posZ = sourcePositionZ + var20.zCoord * var18;
            par2World.spawnEntityInWorld(var17);
        }
    }
}
