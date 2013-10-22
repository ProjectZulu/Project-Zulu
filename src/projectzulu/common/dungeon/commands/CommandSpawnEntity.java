package projectzulu.common.dungeon.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandSpawnEntity extends CommandBase{
	@Override
    public String getCommandName(){
        return "spawnentity";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel(){
        return 2;
    }
    
    @Override
    public String getCommandUsage(ICommandSender par1ICommandSender){
        return "commands.spawnentity.usage";
    }
    
	/**
	 * Command stringArgs     : /spawnentity [targetPlayer] [entityName] <yaw> <pitch> <x> <y> <z>
	 * Command stringArgs == 2: /spawnentity @p entityName
	 * Command stringArgs == 5: /spawnentity @p entityName <xDouble, yDouble, zDouble>
	 * Command stringArgs == 7: /spawnentity @p entityName <yaw> <pitch> <xDouble, yDouble, zDouble> 
	 */
    @Override
	public void processCommand(ICommandSender commandSender, String[] stringArgs){
    	if(stringArgs.length < 2){
			 throw new WrongUsageException("commands.spawnentity.usage", new Object[0]);
		}else{
			double spawnTargetX = 0;
			double spawnTargetY = 0;
			double spawnTargetZ = 0;
			float rotYaw = 0;
			float rotPitch = 0;

			EntityPlayerMP targetPlayer = getPlayer(commandSender, stringArgs[0]);
			if(targetPlayer == null){
				throw new PlayerNotFoundException();
			}
			
			Entity spawnableEntity = EntityList.createEntityByName(stringArgs[1], targetPlayer.worldObj);
			if(spawnableEntity == null){
				System.out.println("Entity is Null");
				 throw new WrongUsageException("commands.spawnentity.noentity", new Object[0]);
			}
			
			if(stringArgs.length == 2){
				spawnTargetX = (int)targetPlayer.posX;
				spawnTargetY = (int)targetPlayer.posY;
				spawnTargetZ = (int)targetPlayer.posZ;
			}else if(stringArgs.length == 5 || stringArgs.length == 7){
				int indexOfPos = stringArgs.length - 3;
				spawnTargetX = (int)parsePosition(commandSender, targetPlayer.posX, stringArgs[indexOfPos++]);
				spawnTargetY = (int)parsePositionWithBounds(commandSender, targetPlayer.posY, stringArgs[indexOfPos++], 0, 0);
				spawnTargetZ = (int)parsePosition(commandSender, targetPlayer.posZ, stringArgs[indexOfPos++]);
			}
			
			if(stringArgs.length == 7){
				rotYaw = parseIntBounded(commandSender, stringArgs[2], 0, 360);
				rotPitch = parseIntBounded(commandSender, stringArgs[3], 0, 360);
			}
			
//			ProjectZuluLog.info("Placing Block at %s, %s, %s with Yaw %s and pitch %s", spawnTargetX, spawnTargetY, spawnTargetZ, rotYaw, rotPitch );
			spawnableEntity.setLocationAndAngles(spawnTargetX, spawnTargetY, spawnTargetZ, rotYaw, rotPitch);
			targetPlayer.worldObj.spawnEntityInWorld(spawnableEntity);
		}
	}
    
    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
	@Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr){
        return par2ArrayOfStr.length != 1 && par2ArrayOfStr.length != 2 ? null : getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
    }
	
	private double parsePosition(ICommandSender commandSender, double currentPos, String stringDouble){
		return this.parsePositionWithBounds(commandSender, currentPos, stringDouble, -30000000, 30000000);
	}

	private double parsePositionWithBounds(ICommandSender commandSender, double currentPos, String stringDouble, int lowerLimit, int upperLimit){
		boolean isRelativeCoords = stringDouble.startsWith("~");
		double targetPos = isRelativeCoords ? currentPos : 0.0D;

		if (!isRelativeCoords || stringDouble.length() > 1){
			boolean hasDecimal = stringDouble.contains(".");

			if (isRelativeCoords){
				stringDouble = stringDouble.substring(1);
			}

			targetPos += parseDouble(commandSender, stringDouble);

			if (!hasDecimal && !isRelativeCoords){
				targetPos += 0.5D;
			}
		}

		if (lowerLimit != 0 || upperLimit != 0){
			if (targetPos < (double)lowerLimit){
				throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[] {Double.valueOf(targetPos), Integer.valueOf(lowerLimit)});
			}

			if (targetPos > (double)upperLimit){
				throw new NumberInvalidException("commands.generic.double.tooBig", new Object[] {Double.valueOf(targetPos), Integer.valueOf(upperLimit)});
			}
		}
		return targetPos;
	}    
}
