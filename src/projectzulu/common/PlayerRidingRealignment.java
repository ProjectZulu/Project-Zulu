//package projectzulu.common;
//
//import java.util.EnumSet;
//
//import net.minecraft.entity.player.EntityPlayer;
//import projectzulu.common.mobs.EntityHorseBase;
//import cpw.mods.fml.common.ITickHandler;
//import cpw.mods.fml.common.TickType;
//
//public class PlayerRidingRealignment implements ITickHandler{
//
//	float tempRotationPitch = 0;
//	float tempRotationYaw = 0;
//	
//	@Override
//	public void tickStart(EnumSet<TickType> type, Object... tickData) {
//		if(tickData[0] != null && tickData[0] instanceof EntityPlayer && ((EntityPlayer)tickData[0]).ridingEntity != null 
//				&& ((EntityPlayer)tickData[0]).ridingEntity instanceof EntityHorseBase){
////			EntityPlayer player = (EntityPlayer)tickData[0];
////			tempRotationPitch = player.prevRotationPitch;
////			tempRotationYaw = player.prevRotationYaw;
//		}
//	}
//
//	@Override
//	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
//		if(tickData[0] != null && tickData[0] instanceof EntityPlayer && ((EntityPlayer)tickData[0]).ridingEntity != null 
//				&& ((EntityPlayer)tickData[0]).ridingEntity instanceof EntityHorseBase){
////			EntityPlayer player = (EntityPlayer)tickData[0];
////			player.prevRotationPitch = tempRotationPitch;
////			player.prevRotationYaw = tempRotationYaw;
////			player.renderYawOffset = ((EntityHorseBase)player.ridingEntity).renderYawOffset;
//		}
//	}
//
//	@Override
//	public EnumSet<TickType> ticks() {
//		return EnumSet.of(TickType.PLAYER);
//
//	}
//
//	@Override
//	public String getLabel() {
//		return null;
//	}
//
//}
