package projectzulu.common.core;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import projectzulu.common.api.ItemList;
import projectzulu.common.mobs.entity.EntityTreeEnt;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHookContainerClass {
    // zLevel is protected float copied from GUI along with drawTexturedModelRect
    protected float zLevel = 0.0F;
    boolean nearBossTriggered = false;

    Random classRand = new Random();

    @SubscribeEvent
    public void onPlayerUpdateStarve(LivingUpdateEvent event) {
        World worldObj = event.entity.worldObj;
        if (worldObj != null && event.entity != null && event.entity instanceof EntityPlayer) {

            EntityPlayer thePlayer = (EntityPlayer) event.entity;

            int var1 = MathHelper.floor_double(thePlayer.posX);
            int var2 = MathHelper.floor_double(thePlayer.boundingBox.minY);
            int var3 = MathHelper.floor_double(thePlayer.posZ);
            BiomeGenBase currentBiome = worldObj.getBiomeGenForCoords(var1, var3);
            boolean isDesertSun = worldObj.canBlockSeeTheSky(var1, var2, var3) && worldObj.isDaytime() == true
                    && (currentBiome == BiomeGenBase.desert || currentBiome == BiomeGenBase.desertHills);

            /* Armor Effect Only Occurs When Block/Item Package is Installed */
            if (!thePlayer.capabilities.isCreativeMode && isDesertSun == true
                    && Loader.isModLoaded(DefaultProps.BlocksModId)) {
                float exhaustion = 0.0032f;
                switch (worldObj.difficultySetting) {
                case PEACEFUL:
                    exhaustion = 0.0f;
                    break;
                case EASY:
                    exhaustion = 0.0032f * 1;
                    break;
                case NORMAL:
                    exhaustion = 0.0032f * 2;
                    break;
                case HARD:
                    exhaustion = 0.0032f * 3;
                default:
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    if (thePlayer.inventory.armorInventory[i] == null) {
                        exhaustion -= (exhaustion - exhaustion * 0.4) / 4f;
                        break;
                    }
                }
                thePlayer.addExhaustion(Math.max(exhaustion, 0));
            }
        }
    }

    /* Armor Effect Only Occurs When Block/Item Package is Installed */
    @SubscribeEvent
    public void cactusArmorDamage(LivingHurtEvent event) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && event.entity != null
                && event.entity instanceof EntityPlayer && event.source.getSourceOfDamage() instanceof EntityLiving) {
            EntityPlayer hurtEntity = (EntityPlayer) event.entity;
            EntityLiving attackingEntity = (EntityLiving) event.source.getSourceOfDamage();
            if (attackingEntity != null && event.source.getDamageType() == "mob") {

                double cactusDamage = 0;
                if (hurtEntity.inventory.armorInventory[3] != null && ItemList.cactusArmorHead.isPresent()
                        && hurtEntity.inventory.armorInventory[3].getItem() == ItemList.cactusArmorHead.get()) {
                    cactusDamage += 0.5;
                }
                if (hurtEntity.inventory.armorInventory[2] != null && ItemList.cactusArmorChest.isPresent()
                        && hurtEntity.inventory.armorInventory[2].getItem() == ItemList.cactusArmorChest.get()) {
                    cactusDamage += 0.5;
                }
                if (hurtEntity.inventory.armorInventory[1] != null && ItemList.cactusArmorLeg.isPresent()
                        && hurtEntity.inventory.armorInventory[1].getItem() == ItemList.cactusArmorLeg.get()) {
                    cactusDamage += 0.5;
                }
                if (hurtEntity.inventory.armorInventory[0] != null && ItemList.cactusArmorBoots.isPresent()
                        && hurtEntity.inventory.armorInventory[0].getItem() == ItemList.cactusArmorBoots.get()) {
                    cactusDamage += 0.5;
                }

                if (cactusDamage > 0) {
                    attackingEntity.attackEntityFrom(DamageSource.causeThornsDamage(hurtEntity),
                            MathHelper.ceiling_double_int(cactusDamage));
                }

            }
        }
    }

    /**
     * Used to Notify nearby Treeents they should be attacking this Player. Triggered by the Player breaking Wood Only
     * notifies TreeEnts that are looking at the Player
     */
    @SubscribeEvent
    public void treeEntDefendForest(BreakSpeed event) {
        if (Loader.isModLoaded(DefaultProps.MobsModId)) {
            if (event.entity != null && event.entity instanceof EntityPlayer
                    && (event.block == Blocks.log || event.block == Blocks.log2)) {
                EntityPlayer player = (EntityPlayer) (event.entity);
                World worldObj = player.worldObj;
                AxisAlignedBB playerBounding = player.boundingBox.copy();
                playerBounding = playerBounding.expand(24, 24, 24);
                List<Entity> listOfTreeEnts = player.worldObj
                        .getEntitiesWithinAABB(EntityTreeEnt.class, playerBounding);
                if (!listOfTreeEnts.isEmpty()) {
                    Iterator entIterator = listOfTreeEnts.iterator();
                    while (entIterator.hasNext()) {
                        Entity entity = (Entity) entIterator.next();
                        if (((EntityTreeEnt) entity).getAngerLevel() <= 0
                                && worldObj.rayTraceBlocks(
                                        worldObj.getWorldVec3Pool().getVecFromPool(player.posX,
                                                player.posY + player.getEyeHeight(), player.posZ),
                                        worldObj.getWorldVec3Pool().getVecFromPool(entity.posX, entity.posY,
                                                entity.posZ)) == null) {
                            if (!worldObj.isRemote) {
                                ((EntityTreeEnt) entity).setAttackTarget(player);
                            }
                            ((EntityTreeEnt) entity).setAngerLevel(60);
                        }
                    }
                }
            }
        }

    }

}
