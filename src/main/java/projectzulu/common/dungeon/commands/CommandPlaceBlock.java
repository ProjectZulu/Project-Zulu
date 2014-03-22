package projectzulu.common.dungeon.commands;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandPlaceBlock extends CommandBase {
    @Override
    public String getCommandName() {
        return "placeblock";
    }

    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    /**
     * Command stringArgs : /placeblock [targetPlayer] [blockID] <meta> <x> <y> <z> Command stringArgs == 2: /placeblock @p
     * blockID Command stringArgs == 3: /placeblock @p blockID <meta> Command stringArgs == 5: /placeblock @p blockID
     * <xDouble, yDouble, zDouble> Command stringArgs == 6: /placeblock @p blockID <meta> <xDouble, yDouble, zDouble>
     */
    @Override
    public void processCommand(ICommandSender commandSender, String[] stringArgs) {
        if (stringArgs.length < 2) {
            throw new WrongUsageException("commands.placeblock.usage", new Object[0]);
        } else {
            int targetX = 0;
            int targetY = 0;
            int targetZ = 0;
            int blockMeta = 0;
            EntityPlayerMP targetPlayer = getPlayer(commandSender, stringArgs[0]);
            if (targetPlayer == null) {
                throw new PlayerNotFoundException();
            }

            Block blockToPlace = Block.getBlockFromName(stringArgs[1]);
            if (blockToPlace == null) {
                throw new WrongUsageException("commands.placeblock.noblock", new Object[0]);
            }

            if (stringArgs.length == 3 || stringArgs.length == 6) {
                blockMeta = parseIntBounded(commandSender, stringArgs[2], 0, 4095);
            }

            if (stringArgs.length == 2 || stringArgs.length == 3) {
                targetX = (int) targetPlayer.posX;
                targetY = (int) targetPlayer.posY;
                targetZ = (int) targetPlayer.posZ;
            } else if (stringArgs.length == 5 || stringArgs.length == 6) {
                int indexOfPos = stringArgs.length - 3;
                targetX = (int) parsePosition(commandSender, targetPlayer.posX, stringArgs[indexOfPos++]);
                targetY = (int) parsePositionWithBounds(commandSender, targetPlayer.posY, stringArgs[indexOfPos++], 0,
                        0);
                targetZ = (int) parsePosition(commandSender, targetPlayer.posZ, stringArgs[indexOfPos++]);
            }

            targetPlayer.worldObj.setBlock(targetX, targetY, targetZ, blockToPlace, blockMeta, 3);
        }
    }

    @Override
    public String getCommandUsage(ICommandSender par1ICommandSender) {
        return "commands.spawnentity.usage";
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    @Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length != 1 && par2ArrayOfStr.length != 2 ? null : getListOfStringsMatchingLastWord(
                par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
    }

    private double parsePosition(ICommandSender commandSender, double currentPos, String stringDouble) {
        return this.parsePositionWithBounds(commandSender, currentPos, stringDouble, -30000000, 30000000);
    }

    private double parsePositionWithBounds(ICommandSender commandSender, double currentPos, String stringDouble,
            int lowerLimit, int upperLimit) {
        boolean isRelativeCoords = stringDouble.startsWith("~");
        double targetPos = isRelativeCoords ? currentPos : 0.0D;

        if (!isRelativeCoords || stringDouble.length() > 1) {
            boolean hasDecimal = stringDouble.contains(".");

            if (isRelativeCoords) {
                stringDouble = stringDouble.substring(1);
            }

            targetPos += parseDouble(commandSender, stringDouble);

            if (!hasDecimal && !isRelativeCoords) {
                targetPos += 0.5D;
            }
        }

        if (lowerLimit != 0 || upperLimit != 0) {
            if (targetPos < lowerLimit) {
                throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[] {
                        Double.valueOf(targetPos), Integer.valueOf(lowerLimit) });
            }

            if (targetPos > upperLimit) {
                throw new NumberInvalidException("commands.generic.double.tooBig", new Object[] {
                        Double.valueOf(targetPos), Integer.valueOf(upperLimit) });
            }
        }
        return targetPos;
    }
}
