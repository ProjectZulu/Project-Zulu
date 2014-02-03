//package projectzulu.common.temperature;
//
//import java.util.ArrayList;
//import java.util.EnumSet;
//import java.util.HashMap;
//import java.util.Map;
//
//import net.minecraft.src.BiomeGenBase;
//import net.minecraft.src.Block;
//import net.minecraft.src.EntityLiving;
//import net.minecraft.src.EntityPlayer;
//import net.minecraft.src.EntityPlayerMP;
//import net.minecraft.src.Material;
//import net.minecraft.src.NBTTagCompound;
//import net.minecraft.src.Potion;
//import net.minecraft.src.PotionEffect;
//import net.minecraft.src.World;
//import cpw.mods.fml.common.IPlayerTracker;
//import cpw.mods.fml.common.ITickHandler;
//import cpw.mods.fml.common.TickType;
//
//public class TemperatureTickerAttempt2 implements ITickHandler, IPlayerTracker{
//	
//	public static final int tempDataWatcher = 18;
//	
//	/* Player Tempurature HashMaps */
//	private static float playerTemperature = 0;
////	private static Map<String, Float> temperatureMap = new HashMap();
//	private static String lastWorldName;
//	private static Map<String, String> lastWorldNameMap = new HashMap();
//	private static Map<String, Float> playerLocTempMap = new HashMap();
//
////	private static Map<String, Float> playerOuterTempModifierMap = new HashMap();
//
////	private static float[] blockTempMod = new float[4096];
//	
//	/* Variables for Saving and Writing */
//	public static long inGameTicks = 0;
//	private static float defaultTemperature = 0;
//
//	private short DirtyFlag = 0;
//	public TemperatureTickerAttempt2(){
//		lastWorldName = "";
//	}
//	
//	private static ArrayList<EntityPlayer> playerList = new ArrayList();
//	private static ArrayList<String> stringList = new ArrayList();
//	
//	
//	@Override
//	public void tickStart(EnumSet<TickType> type, Object... tickData) {
//		
//	}
//
//	@Override
//	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
//		for (TickType tickType : type) {
//			
//			/* Handle Calculation of Temperature by Client/Server for eacg Player, TickType = Player (Passes in Player) */
//			if( tickType.equals(TickType.PLAYER) ){
//				
//				/** 
//				 * Get Player and World
//				 */
//				EntityPlayer player = (EntityPlayer)tickData[0];
//				World worldObj = player.worldObj;
//				
//				/**
//				 *  Check if Player is In tempMap
//				 *  If Present, get playerTemperature and LastWorld
//				 *  Otherwise, addPlayer to Map 
//				 */
//				if( lastWorldNameMap.containsKey( player.getEntityName() ) ){
////					playerTemperature = temperatureMap.get( player.getEntityName() );
//					playerTemperature = getTempFromDataWatcher(player, tempDataWatcher);
//					lastWorldName = lastWorldNameMap.get( player.getEntityName() );
//				}else{
//					addPlayer(player);
//					playerTemperature = 0;
//					lastWorldName = "";
//				}
//
//				/* Check if We Should Read / Handle Reading (Only On Servers) */
//				if( player instanceof EntityPlayerMP){
//					String worldName = player.worldObj.getWorldInfo().getWorldName();
//					/** 
//					 * If World Name Has Changed (i.e. we have changed maps or just started Minecraft)
//					 * We Should Reset playerTemperature and then mark that we need a new value from file
//					 *  */
////					System.out.println("Should be Reading :".concat(worldName).concat(lastWorldName) );
//
//					if (lastWorldName != worldName ){
//						lastWorldName = worldName;
//						playerTemperature = 0;
//						SetPendingRead();
//						readNBTFromFile(player);
//
////						System.out.println( "SETUP" );
//						
//						/* Save WorldName to Map */
//						lastWorldNameMap.put(player.getEntityName(), worldName);
//					}
//
////					/* Read From File */
////					if ( HasPendingRead() ){
////						readNBTFromFile(player);
////						ClearPendingRead();
//////						System.out.println( "READ" );
////					}
//
//				}
//				
//				/* Get Outside Temperature */
//				Float playerLocationTemperature = worldObj.getBiomeGenForCoords( (int)player.posX, (int)player.posZ).getFloatTemperature();
//				
//				/* Untouched playerTemp and Location Temp used to hand to items/blocks to determine their contribution */
//				Float unTouchedPlayerLocationTemperature = playerLocationTemperature;
//				Float unTouchedPlayerTemperature = playerTemperature;
//				float heatTransferRate = 0.0001f;
//				boolean useFastHeatTransfer = false;
//				
//				/* Check Special Blocks that Effect Temperature (i.e. Water) */
//				if(player.isInWater()){
////					playerLocationTemperature -= 0.1f*Math.abs(worldObj.getBiomeGenForCoords( (int)player.posX, (int)player.posZ).getFloatTemperature());
//					playerLocationTemperature -= playerLocationTemperature > 1.5f ? 0.75f : playerLocationTemperature < -0.5f ? 1.0f : 0.5f;
//					useFastHeatTransfer = true;
//				}
//				if(player.isInsideOfMaterial(Material.lava)){
//					playerLocationTemperature += 2.0f;
//					useFastHeatTransfer = true;
//				}
//				if( !worldObj.canBlockSeeTheSky( (int)player.posX, (int)player.posY, (int)player.posZ) ){
//					playerLocationTemperature -= 0.02f;
//					
//				}
//				if( !worldObj.isDaytime() ){
//					playerLocationTemperature -= 0.03f;
//				}
//				
//				/* Check Item Being Held */
//				if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ITempItem ){
//					ITempItem item = (ITempItem) player.inventory.getCurrentItem().getItem();
//
//					/* Item in Hand */
////					playerLocationTemperature += item.getAddToPlayTempWithCurItem(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature);
////					playerTemperature += item.getAddToLocTempWithCurItem(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature);
//					
//					heatTransferRate += item.getAddToHeatTransferWithCurItem(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature, heatTransferRate);
//					useFastHeatTransfer = item.getBooleanCauseFastHeatTransferWithCurItem(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature, heatTransferRate);						
//					
//				}
//				
//				/* Check Armor That is Equipped */
//				for (int i = 0; i < player.inventory.armorInventory.length; i++) {
//					if(player.inventory.armorInventory[i] != null && player.inventory.armorInventory[i].getItem() instanceof ITempArmor ){
//						ITempArmor item = (ITempArmor) player.inventory.armorInventory[i].getItem();
//
//						playerLocationTemperature += item.getAddToPlayTempOnEquip(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature);
//						playerTemperature += item.getAddToLocTempOnEquip(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature);
//						heatTransferRate += item.getAddToHeatTransferOnEquip(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature, heatTransferRate);
//						useFastHeatTransfer = item.getBooleanCauseFastHeatTransferOnEquip(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature, heatTransferRate);						
//					}
//				}
//				
//				/* Check Nearby Blocks in 8x8x8 Square*/
//				int innerBlockRadius = 4;
//				for (int i = -innerBlockRadius; i <= innerBlockRadius; i++) {
//					for (int k = -innerBlockRadius; k <= innerBlockRadius; k++) {
//						for (int j = -innerBlockRadius; j <= innerBlockRadius; j++) {
//							int curX = (int) (player.posX+i);
//							int curY = (int) (player.posY+j);
//							int curZ = (int) (player.posZ+k);
//							if( Block.blocksList[worldObj.getBlockId(curX, curY, curZ)] instanceof ITempBlock ){
//								ITempBlock currentBlock = (ITempBlock)Block.blocksList[worldObj.getBlockId(curX, curY, curZ)];
//
//								playerLocationTemperature += currentBlock.getAddToLocTempByNearbyBlock(player, curX, curY, curZ, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature);
//								playerTemperature += currentBlock.getAddToPlayTempByNearbyBlock(player, curX, curY, curZ, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature);
//								heatTransferRate += currentBlock.getAddToHeatTransferByBlock(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature,heatTransferRate);
//								useFastHeatTransfer = currentBlock.getBooleanCauseFastHeatTransferByBlock(player, unTouchedPlayerTemperature, unTouchedPlayerLocationTemperature, heatTransferRate);
//							}else{
//								playerLocationTemperature += evaluateBlockAtLocForLocation(worldObj, curX, curY, curZ);
//								playerTemperature += evaluateBlockAtLocForPlayer(worldObj, curX, curY, curZ);
//
//							}
//						}
//					}
//				}
//				
//				/* Perform Heat Transfer From Environment to Player*/
//				if(useFastHeatTransfer){
//					playerTemperature += 0.01f * (playerLocationTemperature - playerTemperature);
//				}else{
//					playerTemperature += heatTransferRate * (playerLocationTemperature - playerTemperature);
//				}
//				
//				/* Convert to Human Readable Number that we can work with for assigning effects*/
//				float playerTemperatureDegrees = decimalToDegrees(playerTemperature);
//				if(!worldObj.getBiomeGenForCoords((int)player.posX, (int)player.posZ).equals(BiomeGenBase.hell)){
//					/* Handle Effect Of Temperature on Player */
//					if( playerTemperatureDegrees < 20){
//						/* Handle Very Cold */
//	                	((EntityLiving)player).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1, 100 - (int)playerTemperatureDegrees));
//	                	
//					}else if( playerTemperatureDegrees < 30 ){
//						/* Handle Medium Cold */
//
//					}else if ( playerTemperatureDegrees < 40 ){
//						/* Handle Little Cold */
//
//					}else if( playerTemperatureDegrees > 100 ){
//						/* Handle High Heat */
//						if(inGameTicks % 80 > 8){
//			            	((EntityLiving)player).addPotionEffect(new PotionEffect(Potion.confusion.id, 62,100));
//						}
//
//					}else if( playerTemperatureDegrees > 90 ){
//						/* Handle Medium Heat */
//
//					}else if( playerTemperatureDegrees > 80 ){
//						/* Handle Little Heat */
//					}
//
//				}else{
//					playerTemperature = 3.0f;
//				}
//				
//				/* Set Temperature To Datawatcher on Server Side */
//				if(player instanceof EntityPlayerMP){
//					updateTempInDataWatcher(player, tempDataWatcher, playerTemperature);
//				}
//								
//				/* Save Temperature To Map*/
////				temperatureMap.put( player.getEntityName(), playerTemperature);
//				playerLocTempMap.put( player.getEntityName(), playerLocationTemperature);
//				/* Set Data to Be Written to Disk*/
//				SetPendingWrite();
//				
//				/*Debugging Shit, Make Sure Crap Actually Works*/
//				if( player instanceof EntityPlayerMP ){
////					System.out.println( player.getEntityName().concat("File ").concat(  player.worldObj.getWorldInfo().getWorldName() ));
////					System.out.println( player.getEntityName().concat("Loc ").concat(  Float.toString(playerLocationTemperature*100) ));
////					System.out.println( player.getEntityName().concat("PlayServ ").concat(  Float.toString(playerTemperature*100)  ));
////					System.out.println( player.getEntityName().concat("Serv Loops ").concat(  Long.toString(inGameTicks) ));
//				}else{
////					System.out.println( player.getEntityName().concat("PlayClie ").concat(  Float.toString(playerTemperature*100)  ));
//				}
//				
////				/* Handle Sending Packet With Updated Temperature From Server To Client (Only on Server, by definition)*/
////				if(player instanceof EntityPlayerMP){
////					
////	                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
////	                DataOutputStream data = new DataOutputStream(bytes);
//////					System.out.println( player.getEntityName().concat("Preparing To Send:  ").concat(  Float.toString(playerTemperature*100)  ));
////	                try {
////	                	data.writeFloat(playerTemperature);
////					} catch (Exception e) {
////						e.printStackTrace();
////					}
////	                
////					Packet250CustomPayload packet = new Packet250CustomPayload();
////					packet.channel = "Channel_Zulu"; // CHANNEL MAX 16 CHARS
////	                packet.data = bytes.toByteArray();
////	                packet.length = packet.data.length;
////					PacketDispatcher.sendPacketToPlayer(packet, (Player) player);
//////					System.out.println( player.getEntityName().concat("Sent Packet "));
////				}
//				
//				/* Handle Writing (Only On Servers) */
//				if(player instanceof EntityPlayerMP){//) && HasPendingWrite() ){
//						writeNBTToFile(player);
//						ClearPendingWrite();
////						System.out.println( "WRITE" );
//				}
//				
//				
//			}
//		}
//		
//		
//		inGameTicks++;
//	}
//	
//	
//	@Override
//	public EnumSet<TickType> ticks() {
//		return EnumSet.of(TickType.PLAYER);
//	}
//
//	@Override
//	public String getLabel() {
//		return "TempTicker";
//	}	
//
//	private void SetPendingRead(){
//		DirtyFlag |= 1;
//	}
//
//	private void SetPendingWrite(){
//		DirtyFlag |= 2;
//	}
//
//	private void ClearPendingRead(){
//		DirtyFlag &= ~1;
//	}
//
//	private void ClearPendingWrite(){
//		DirtyFlag &= ~2;
//	}
//
//	private boolean HasPendingRead(){
//		return (DirtyFlag & 1) == 1;
//	}
//
//	private boolean HasPendingWrite(){
//		return (DirtyFlag & 2) == 2;
//	}
//	
//	private void writeNBTToFile(EntityPlayer player){
//		try
//		{
//			
//////			/*Method 1: Custom NBT System */
////			File file = new File(System.getProperty("user.dir") + "/saves/" + player.worldObj.getSaveHandler().getSaveDirectoryName() + "", "any_name_you_want.dat");
////			File parentDir = file.getParentFile();
////			if(! parentDir.exists()){
////				parentDir.mkdirs();
////			}
////			//			File file = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/" + ModLoader.getMinecraftInstance().theWorld.saveHandler.getSaveDirectoryName() + "", "any_name_you_want.dat");
////			if(!file.exists())
////			{
////				file.createNewFile();
////			}
////			FileOutputStream fileoutputstream = new FileOutputStream(file);
////			NBTTagCompound nbttagcompound = new NBTTagCompound();
//			
//			/*Method 2: Forge has a NBT System for custom Data Already I guess */
//			NBTTagCompound nbttagcompound = player.getEntityData();
//
//			nbttagcompound.setFloat(player.getEntityName().concat("zulu_temperature_a"), getTempFromDataWatcher(player, tempDataWatcher) );
//
////			nbttagcompound.setFloat(player.getEntityName().concat("zulu_temperature_a"), playerTemperature);
//			
////			CompressedStreamTools.writeCompressed(nbttagcompound, fileoutputstream);
////			fileoutputstream.close();
//		}
//		catch(Exception exception)
//		{
//			exception.printStackTrace();
//		}
//	}
//	
//	private void readNBTFromFile( EntityPlayer player){
//		try
//		{
////			/*Method 1: Custom NBT System */
////			File file = new File(System.getProperty("user.dir") + "/saves/" + player.worldObj.getSaveHandler().getSaveDirectoryName() + "", "any_name_you_want.dat");
////			File parentDir = file.getParentFile();
////			if(! parentDir.exists()){
////				parentDir.mkdirs();
////			}
////			
////			if(!file.exists())
////			{
////				return;
////			}
////			FileInputStream fileinputstream = new FileInputStream(file);
////			NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(fileinputstream);
//			
//			/*Method 2: Forge has a NBT System for custom Data Already I guess */
//			NBTTagCompound nbttagcompound = player.getEntityData();
//			
//			if (nbttagcompound.hasKey( player.getEntityName().concat("zulu_temperature_a") )){
//				updateTempInDataWatcher(player, tempDataWatcher, nbttagcompound.getFloat( player.getEntityName().concat("zulu_temperature_a") ));
//				
////				player.getDataWatcher().updateObject(tempDataWatcher, nbttagcompound.getFloat( player.getEntityName().concat("zulu_temperature_a") ));
////				this.playerTemperature = nbttagcompound.getFloat( player.getEntityName().concat("zulu_temperature_a") );
//			}
//			
////			fileinputstream.close();
//		}
//		catch(Exception exception)
//		{
//			exception.printStackTrace();
//		}
//	}
//	
////	public static void updateTemperatureFromPacket(DataInputStream data, EntityPlayer sender){
////		
////		/* Get Relevent Data From DataStream */
////		float tempTemp = playerTemperature;
////		try {
////			tempTemp = data.readFloat();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		
////		/* Make Sure Player Exists, then Assign data to map */
////		if(temperatureMap.containsKey(sender.getEntityName())){
////			temperatureMap.put(sender.getEntityName(), tempTemp);
//////			System.out.println( sender.getEntityName().concat("Received Packet "));
////
////		}
////		
////		
////	}
//	/**
//	 * Evaluates the block at the given Coordinate and return its effect on Player Temperature
//	 * @param posX,Y,Z of Block
//	 * @return
//	 */
//	private float evaluateBlockAtLocForPlayer(World worldObj, int posX, int posY, int posZ){
//		
//		return 0;
//	}
//	
//	/**
//	 * Evaluates the block at the given Coordinate and return its effect on Location Temperature
//	 * @param posX,Y,Z of Block
//	 * @return
//	 */
//	private float evaluateBlockAtLocForLocation(World worldObj, int posX, int posY, int posZ){
//		float tempModifier = 0f;
//		int blockID = worldObj.getBlockId(posX, posY, posZ);
//		if( blockID == Block.ice.blockID ){
//			tempModifier -= 0.02f;
//			
//		}else if( blockID == Block.snow.blockID ){
//			tempModifier -= 0.01f;
//			
//		}else if(blockID == Block.lavaMoving.blockID || blockID == Block.lavaStill.blockID){
//			tempModifier += 0.02f;
//		}
//		return tempModifier;
//	}
//	
//	/**
//	 * Add Player to temperatureMap and LastWorldNameMap
//	 * @param player
//	 */
//	public void addPlayer(EntityPlayer player){
////		temperatureMap.put(player.getEntityName(), 0.0f);
//
//		lastWorldNameMap.put(player.getEntityName(), "");
//		player.getDataWatcher().addObject(tempDataWatcher, (int)playerTemperature);
//	}
//	
//	/**
//	 * Remove Player from temperatureMap and LastWorldNameMap
//	 * @param player
//	 */
//	public void removePlayer(EntityPlayer player){
////		temperatureMap.remove(player.getEntityName());
//		lastWorldNameMap.remove(player.getEntityName());		
//	}
//
//	@Override
//	public void onPlayerLogin(EntityPlayer player) {
//		addPlayer(player);
//		
//	}
//
//	@Override
//	public void onPlayerLogout(EntityPlayer player) {
//		removePlayer(player);
//		
//	}
//
//	@Override
//	public void onPlayerChangedDimension(EntityPlayer player) {
//		
//		if(!player.worldObj.getBiomeGenForCoords((int)player.posX, (int)player.posZ).equals(BiomeGenBase.hell) && !player.worldObj.isRemote){
//			player.getDataWatcher().updateObject(tempDataWatcher, defaultTemperature);
////			temperatureMap.put(player.getEntityName(), defaultTemperature);
//		}
//		
//	}
//
//	@Override
//	public void onPlayerRespawn(EntityPlayer player) {
////		System.out.println("Player Respawned");
//		
//	}
//	
//	/**
//	 * Gets the Temperature for the Provided if player if it exists
//	 * @param player
//	 * @return temperature if player is present in map, null if player is not in map
//	 */
//	public static Float getPlayerTemperature(EntityPlayer player){
//		if(lastWorldNameMap.containsKey(player.getEntityName())){
//			return (float) getTempFromDataWatcher(player, tempDataWatcher);
////			return temperatureMap.get(player.getEntityName());
//		}
//		return null;
//	}
//	
//	/**
//	 * Helper Functions to set and read from PlayerDatawatcher while maintaining a scale
//	 */
//	private static void updateTempInDataWatcher(EntityPlayer player, int index, float value){
//		player.getDataWatcher().updateObject(index, (int)value);
//	}
//	/**
//	 * Helper Functions to set and read from PlayerDatawatcher while maintaining a scale
//	 */
//	private static Float getTempFromDataWatcher(EntityPlayer player, int index){
//		if(lastWorldNameMap.containsKey(player.getEntityName())){
//			return (float) player.getDataWatcher().getWatchableObjectInt(index);
//		}
//		return 0f;
//	}
//	/**
//	 * Gets the Temperature at the provided player if it exists
//	 * @param player
//	 * @return temperature if player is present in map, null if player is not in map
//	 */
//	public static Float getPlayerLocTemperature(EntityPlayer player){
//		if(playerLocTempMap.containsKey(player.getEntityName())){
//			return playerLocTempMap.get(player.getEntityName());
//		}
//		return null;
//	}
//	
//	/**
//	 * USed to Convert from Minecraft Decimal Temperatures to a human readable scale
//	 * @param value
//	 * @param set1min
//	 * @param set1max
//	 * @param set2min
//	 * @param set2max
//	 * @return
//	 */
//	private static float decimalToDegrees(float value){
//		
//		return 120 - mapValueofSet1ToSet2(value, 3.5f, -2.5f, 0, 120);
//	}
//	
//	private static float mapValueofSet1ToSet2(float value, float set1min, float set1max, float set2min, float set2max){
////		return (float) (Math.pow(set1max,-3f)/set2max*Math.pow(value, -3f));
//		return (value - set1min)*( (set2max - set2min) / (set1max - set1min) ) + set2min;
//	}
//	
//}
