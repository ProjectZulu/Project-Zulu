package projectzulu.common.potion;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPZPotion extends EntityPotion {

    private ItemStack potionStack;

    public EntityPZPotion(World par1World) {
        super(par1World);
    }

    public EntityPZPotion(World world, EntityLivingBase living, ItemStack itemStack) {
        super(world, living, itemStack);
        potionStack = itemStack;
    }

    public EntityPZPotion(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
        super(par1World, par2, par4, par6, par8ItemStack);
        potionStack = par8ItemStack;
    }

    @Override
    public int getPotionDamage() {
        return potionStack != null ? potionStack.getItemDamage() : 0;
    }

    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        if (!this.worldObj.isRemote && potionStack.getItem() != null && potionStack.getItem() instanceof ItemPotion) {
            @SuppressWarnings("unchecked")
            List<PotionEffect> effectList = ((ItemPotion) potionStack.getItem()).getEffects(potionStack);

            if (effectList != null && !effectList.isEmpty()) {
                AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
                @SuppressWarnings("unchecked")
                List<EntityLivingBase> entityList = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
                        axisalignedbb);

                if (entityList != null && !entityList.isEmpty()) {
                    Iterator<EntityLivingBase> iterator = entityList.iterator();

                    while (iterator.hasNext()) {
                        EntityLivingBase entityliving = iterator.next();
                        double squareDistance = this.getDistanceSqToEntity(entityliving);

                        if (squareDistance < 16.0D) {
                            double distanceFactor = 1.0D - Math.sqrt(squareDistance) / 4.0D;

                            if (entityliving == par1MovingObjectPosition.entityHit) {
                                distanceFactor = 1.0D;
                            }

                            Iterator<PotionEffect> iterator1 = effectList.iterator();

                            while (iterator1.hasNext()) {
                                PotionEffect potioneffect = iterator1.next();
                                int potionID = potioneffect.getPotionID();

                                if (Potion.potionTypes[potionID].isInstant()) {
                                    Potion.potionTypes[potionID].affectEntity(this.getThrower(), entityliving,
                                            potioneffect.getAmplifier(), distanceFactor);
                                } else {
                                    int potionDuration = (int) (distanceFactor * potioneffect.getDuration() + 0.5D);

                                    if (potionDuration > 20) {
                                        entityliving.addPotionEffect(new PotionEffect(potionID, potionDuration,
                                                potioneffect.getAmplifier()));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            this.worldObj.playAuxSFX(2002, (int) Math.round(this.posX), (int) Math.round(this.posY),
                    (int) Math.round(this.posZ), potionStack.getItemDamage());
            this.setDead();
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("Potion")) {
            potionStack = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("Potion"));
        }

        if (potionStack == null) {
            this.setDead();
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);

        if (potionStack != null) {
            par1NBTTagCompound.setTag("Potion", potionStack.writeToNBT(new NBTTagCompound()));
        }
    }
}
