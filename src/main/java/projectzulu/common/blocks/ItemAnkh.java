package projectzulu.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;

public class ItemAnkh extends Item {

    /**
     * 
     * @param i
     * @param name Name to use as base for Unlocalized name
     */
    public ItemAnkh(String name) {
        super();
        maxStackSize = 1;
        setMaxDamage(200);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = true;
        setUnlocalizedName(name.toLowerCase());
        setTextureName(DefaultProps.blockKey + ":" + name.toLowerCase());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        if (player.getHealth() > 1f) {
            shootFireball(world, player);
            if (!player.capabilities.isCreativeMode) {
                player.heal(-1);
            }
        }
        return itemstack;
    }

    public void shootFireball(World world, EntityPlayer player) {

        if (!world.isRemote) {
            int holditemRand = itemRand.nextInt(10) - 5;
            double sourcePositionX = player.posX + holditemRand;
            // double sourcePositionY = par3EntityPlayer.posY+30;
            double sourcePositionY = world.getActualHeight() - 15;
            holditemRand = itemRand.nextInt(10) - 5;
            double sourcePositionZ = player.posZ + holditemRand;

            double var11 = player.posX - sourcePositionX;
            double var13 = player.boundingBox.minY + (double) (player.height / 2.0F)
                    - (sourcePositionY + (double) (player.height / 2.0F));
            double var15 = player.posZ - sourcePositionZ;
            player.renderYawOffset = player.rotationYaw = -((float) Math.atan2(var11, var15)) * 180.0F
                    / (float) Math.PI;

            world.playAuxSFXAtEntity((EntityPlayer) null, 1008, (int) player.posX, (int) player.posY,
                    (int) player.posZ, 0);
            EntityLargeFireball var17 = new EntityLargeFireball(world, player, var11, var13, var15);
            double var18 = 1.0D;
            Vec3 var20 = player.getLook(1.0F);
            var17.posX = sourcePositionX + var20.xCoord * var18;
            var17.posY = sourcePositionY + (double) (player.height / 2.0F) + 0.5D;
            var17.posZ = sourcePositionZ + var20.zCoord * var18;
            world.spawnEntityInWorld(var17);
        }
    }
}
