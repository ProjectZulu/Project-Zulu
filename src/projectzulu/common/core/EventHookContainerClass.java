package projectzulu.common.core;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemBlockList;
import projectzulu.common.blocks.TileEntityTombstone;
import projectzulu.common.mobs.EntityTreeEnt;
import cpw.mods.fml.common.Loader;

public class EventHookContainerClass {
	//zLevel is protected float copied from GUI along with drawTexturedModelRect
	protected float zLevel = 0.0F;
	boolean nearBossTriggered = false;

	Random classRand = new Random();
	
	@ForgeSubscribe
	public void onPlayerUpdateStarve(LivingUpdateEvent event){		
		World worldObj = event.entity.worldObj;
		if(worldObj != null && event.entity != null && event.entity instanceof EntityPlayer){

			EntityPlayer thePlayer = (EntityPlayer)event.entity;	
			
			int var1 = MathHelper.floor_double(thePlayer.posX);
			int var2 = MathHelper.floor_double(thePlayer.boundingBox.minY);
			int var3 = MathHelper.floor_double(thePlayer.posZ);
			BiomeGenBase currentBiome = worldObj.getBiomeGenForCoords(var1, var3);
			boolean isDesertSun = worldObj.canBlockSeeTheSky(var1, var2, var3) && worldObj.isDaytime() == true && (currentBiome == BiomeGenBase.desert || currentBiome == BiomeGenBase.desertHills);

			/* Armor Effect Only Occurs When Block/Item Package is Installed*/
			if(!thePlayer.capabilities.isCreativeMode && isDesertSun == true && Loader.isModLoaded(DefaultProps.BlocksModId) ){
				float exhaustion = 0.0032f;
				switch (worldObj.difficultySetting) {
					case 0:
						exhaustion = 0.0f;
						break;
					case 1:
						exhaustion = 0.0032f*1;
						break;
					case 2:
						exhaustion = 0.0032f*2;
						break;
					case 3:
						exhaustion = 0.0032f*3;
					default:
						break;
				}

					for(int i = 0;i<4;i++){
						if(thePlayer.inventory.armorInventory[i] == null){
							exhaustion-=(exhaustion-exhaustion*0.4)/4f;
							break;
						}

						for (ItemStack tierarmor : ProjectZulu_Core.tier5DesertArmor) {
							if(thePlayer.inventory.armorInventory[i].itemID == tierarmor.itemID){
								exhaustion-=(exhaustion-exhaustion*0.35)/4f;
							}
						}

						for (ItemStack tierarmor : ProjectZulu_Core.tier4DesertArmor) {
							if(thePlayer.inventory.armorInventory[i].itemID == tierarmor.itemID){
								exhaustion-=(exhaustion-exhaustion*0.3)/4f;
							}
						}

						for (ItemStack tierarmor : ProjectZulu_Core.tier3DesertArmor) {
							if(thePlayer.inventory.armorInventory[i].itemID == tierarmor.itemID){
								exhaustion-=(exhaustion-exhaustion*0.25)/4f;
							}
						}

						for (ItemStack tierarmor : ProjectZulu_Core.tier2DesertArmor) {
							if(thePlayer.inventory.armorInventory[i].itemID == tierarmor.itemID){
								exhaustion-=(exhaustion-exhaustion*0.2)/4f;
							}
						}

						for (ItemStack tierarmor : ProjectZulu_Core.tier1DesertArmor) {
							if(thePlayer.inventory.armorInventory[i].itemID == tierarmor.itemID){
								exhaustion-=exhaustion/4;
							}
						}
					}
				thePlayer.addExhaustion(Math.max(exhaustion, 0));
			}
		}
	}	

	@ForgeSubscribe
	public void OnPlayerUpdateFreeze(LivingUpdateEvent event){
		World worldObj = event.entity.worldObj;
		if(worldObj != null && event.entity != null && event.entity instanceof EntityPlayer){


			EntityPlayer thePlayer = (EntityPlayer)event.entity;

			int var1 = MathHelper.floor_double(thePlayer.posX);
			int var2 = MathHelper.floor_double(thePlayer.boundingBox.minY);
			int var3 = MathHelper.floor_double(thePlayer.posZ);
			BiomeGenBase currentBiome = worldObj.getBiomeGenForCoords(var1, var3);
			boolean isSnowArea = currentBiome.getEnableSnow();

			/* Armor Effect Only Occurs When Block/Item Package is Installed*/
			if(!thePlayer.capabilities.isCreativeMode && isSnowArea && Loader.isModLoaded(DefaultProps.BlocksModId) ){
				float velocityModifier = 0;

				/* Slow Player if on snow */
				if( worldObj.getBlockMaterial( (int)thePlayer.posX, (int)thePlayer.posY-1, (int)thePlayer.posZ) == Material.snow
						|| worldObj.getBlockMaterial( (int)thePlayer.posX, (int)thePlayer.posY, (int)thePlayer.posZ) == Material.snow){
					velocityModifier -= 0.01;
				}

				/* Slow Player if it is Snowing */
				if(worldObj.isRaining() && worldObj.canBlockSeeTheSky((int)thePlayer.posX, (int)thePlayer.posY, (int)thePlayer.posZ)){
					velocityModifier -= 0.01;
				}

				/* Increase Speed if Wearing Fur */
				if(thePlayer.inventory.armorInventory[0] != null && ItemBlockList.furArmorBoot.isPresent() && thePlayer.inventory.armorInventory[0].itemID == ItemBlockList.furArmorBoot.get().shiftedIndex ){
					velocityModifier += 0.005;
				}
				if(thePlayer.inventory.armorInventory[1] != null && ItemBlockList.furArmorLeg.isPresent() && thePlayer.inventory.armorInventory[1].itemID == ItemBlockList.furArmorLeg.get().shiftedIndex){
					velocityModifier += 0.005;
				}
				if(thePlayer.inventory.armorInventory[2] != null && ItemBlockList.furArmorChest.isPresent() && thePlayer.inventory.armorInventory[2].itemID == ItemBlockList.furArmorChest.get().shiftedIndex){
					velocityModifier += 0.005;
				}
				if(thePlayer.inventory.armorInventory[3] != null && ItemBlockList.furArmorHead.isPresent() && thePlayer.inventory.armorInventory[3].itemID == ItemBlockList.furArmorHead.get().shiftedIndex){
					velocityModifier += 0.005;
				}

				/*Apply VelocityModifier to landMovementFactor*/
				if(thePlayer.isSprinting() ){
					thePlayer.landMovementFactor = 0.13f+velocityModifier ;
				}else{
					thePlayer.landMovementFactor = 0.1f+velocityModifier ;
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void PlayerDeathTest(LivingDeathEvent event){
//		if( (Loader.isModLoaded(DefaultProps.MobsModId) && event.entity instanceof EntityCreeper) ){
//			World worldObj = event.entity.worldObj;
//			EntityCreeper Creeper = (EntityCreeper)event.entity;
//
//			List<Vec3> centroids = new ArrayList<Vec3>();
//
//			AxisAlignedBB var15 = Creeper.boundingBox.copy();
//			var15 = var15.expand(40, 30, 40);
//			List nearbyEntities = worldObj.getEntitiesWithinAABB(EntityMammoth.class, var15);
//			if (nearbyEntities != null && !nearbyEntities.isEmpty()){
//				Iterator var10 = nearbyEntities.iterator();
//				while (var10.hasNext()){
//					Entity var4 = (Entity)var10.next();
//
//					if (var4 instanceof EntityMammoth){
//						if(((EntityMammoth) var4).timeLeftStampeding == 0){
//							((EntityMammoth) var4).causeStampede(Vec3.createVectorHelper(Creeper.posX, Creeper.posY, Creeper.posZ));
//						}
//					}
//				}
//			}
//		}
		
		if( (Loader.isModLoaded(DefaultProps.MobsModId) && ProjectZulu_Core.tombstoneOnDeath 
				&& event.entity instanceof EntityPlayer && ItemBlockList.tombstone.isPresent() )){
			EntityPlayer player = (EntityPlayer)event.entity;
			World worldObj = player.worldObj;
			
			/* Check if Location is Valid for Tombstone */
			if(worldObj.isAirBlock((int)player.posX, (int)player.posY, (int)player.posZ)){
				/* Place a Tombstone */
				worldObj.setBlock((int)player.posX, (int)player.posY, (int)player.posZ, ItemBlockList.tombstone.get().blockID);
				TileEntity tileEntity = worldObj.getBlockTileEntity((int)player.posX, (int)player.posY, (int)player.posZ);
				if(tileEntity != null && tileEntity instanceof TileEntityTombstone ){
					((TileEntityTombstone)tileEntity).setSignString(event.source.getDeathMessage((EntityPlayer)event.entity));
				}
			}
		}
	}

	
	/* Armor Effect Only Occurs When Block/Item Package is Installed*/
	@ForgeSubscribe
	public void cactusArmorDamage(LivingHurtEvent event){
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && event.entity != null && event.entity instanceof EntityPlayer && event.source.getSourceOfDamage() instanceof EntityLiving){
			EntityPlayer hurtEntity = (EntityPlayer)event.entity;
			EntityLiving attackingEntity = (EntityLiving)event.source.getSourceOfDamage();
			if(attackingEntity != null && event.source.getDamageType() == "mob"){
				
				double cactusDamage = 0;
				if(hurtEntity.inventory.armorInventory[3] != null && ItemBlockList.cactusArmorHead.isPresent() && hurtEntity.inventory.armorInventory[3].itemID == ItemBlockList.cactusArmorHead.get().shiftedIndex){
					cactusDamage+=0.5;
				}
				if(hurtEntity.inventory.armorInventory[2] != null && ItemBlockList.cactusArmorChest.isPresent() && hurtEntity.inventory.armorInventory[2].itemID == ItemBlockList.cactusArmorChest.get().shiftedIndex){
					cactusDamage+=0.5;
				}
				if(hurtEntity.inventory.armorInventory[1] != null && ItemBlockList.cactusArmorLeg.isPresent() && hurtEntity.inventory.armorInventory[1].itemID == ItemBlockList.cactusArmorLeg.get().shiftedIndex){
					cactusDamage+=0.5;
				}
				if(hurtEntity.inventory.armorInventory[0] != null && ItemBlockList.cactusArmorBoot.isPresent() && hurtEntity.inventory.armorInventory[0].itemID == ItemBlockList.cactusArmorBoot.get().shiftedIndex){
					cactusDamage+=0.5;
				}
				
				if(cactusDamage > 0){
					attackingEntity.attackEntityFrom(DamageSource.causeMobDamage(hurtEntity), MathHelper.ceiling_double_int(cactusDamage));
				}
				
			}
		}
	}
	
	/**
	 * Used to Notify nearby Treeents they should be attacking this Player. 
	 * Triggered by the Player breaking Wood
	 * Only notifies TreeEnts that are looking at the Player
	 */
	@ForgeSubscribe
	public void treeEntDefendForest(BreakSpeed event){
		if(Loader.isModLoaded(DefaultProps.MobsModId)){
			if(event.entity != null && event.entity instanceof EntityPlayer && event.block.blockID == Block.wood.blockID){
				EntityPlayer player = (EntityPlayer)(event.entity);
				World worldObj = player.worldObj;
				AxisAlignedBB playerBounding = player.boundingBox.copy();
				playerBounding = playerBounding.expand(24, 24, 24);
				List<Entity> listOfTreeEnts = player.worldObj.getEntitiesWithinAABB(EntityTreeEnt.class, playerBounding);
				if(!listOfTreeEnts.isEmpty()){
					Iterator entIterator = listOfTreeEnts.iterator();
					while(entIterator.hasNext()){
						Entity entity = (Entity)entIterator.next();
						if( ((EntityTreeEnt)entity).getAngerLevel() <= 0 && worldObj.rayTraceBlocks(worldObj.getWorldVec3Pool().getVecFromPool(player.posX, player.posY+(double)player.getEyeHeight(), player.posZ),
								worldObj.getWorldVec3Pool().getVecFromPool(entity.posX, entity.posY, entity.posZ)) == null  ){
							
							if(!worldObj.isRemote){
								((EntityTreeEnt)entity).setAttackTarget(player);
							}
							((EntityTreeEnt)entity).setAngerLevel(60);
						}
					}
				}
			}
		}
		
	}
	
	
}
