package projectzulu.common.temperature;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Interface which marks object as a Temperature Armor
 * Methods will automatically be called in TemperatureTicker by every nearby player
 */
public interface ITempBlock {
		
	/**
	 * Factor that adds to the current Player Temp for when the Block is Nearby
	 * Applied in the form TempFinal = TempInitial + TempAddition
	 * Design Directive: Should be used for non-environment/short term effects: i.e. eating items that would instantly effect temperature
	 * @param player
	 * @param playerTemp
	 * @param playerLocationTemp
	 * @return Float of 0 if no effect is desired, Float TempAddition otherwise.
	 */
	public float getAddToPlayTempByNearbyBlock(EntityPlayer player, int blockPosX, int blockPosY, int blockPosZ, Float playerTemp, float playerLocationTemp);	
	
	/**
	 * Factor that adds to the current Location Temp for when the Block is Nearby
	 * Applied in the form TempFinal = TempInitial + TempAddition3
	 * Design Directive: Should be used for environment/long term effects: i.e. sitting in a sauna would increase temperature over time
	 * @param player
	 * @param playerTemp
	 * @param playerLocationTemp
	 * @return Float of 0 if no effect is desired, Float TempAddition otherwise
	 */
	public float getAddToLocTempByNearbyBlock(EntityPlayer player, int blockPosX, int blockPosY, int blockPosZ, Float playerTemp, float playerLocationTemp);

	/**
	 * Factor that adds to the current Heat Transfer Rate for when the Block is Nearby
	 * Applied in the form HeatTransferRate += RateAddition
	 * @param player
	 * @param playerTemp
	 * @param playerLocationTemp
	 * @param currentHeatRate
	 * @return Float of 0 if no effect is desired, Float RateAddition otherwise
	 */
	public float getAddToHeatTransferByBlock(EntityPlayer player, float playerTemp, float playerLocationTemp, float currentHeatRate);
	
	/**
	 * Boolean that sets whether the "fast" heat transfer is used. If true (or set to true by other item), will override AddToHeatTransfer Effects
	 * Applied in the form HeatTransferRate += RateAddition
	 * Design Directive: Should be used to sparingly. Always 
	 * @param player
	 * @param playerTemp
	 * @param playerLocationTemp
	 * @param currentHeatRate
	 * @return false for normal speed, true for fast
	 */
	public boolean getBooleanCauseFastHeatTransferByBlock(EntityPlayer player, float playerTemp, float playerLocationTemp, float currentHeatRate);
}
