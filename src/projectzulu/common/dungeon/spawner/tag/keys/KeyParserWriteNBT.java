package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.dungeon.spawner.tag.NBTWriter;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

public class KeyParserWriteNBT extends KeyParserBase {

    public KeyParserWriteNBT(Key key) {
        super(key, false, KeyType.CHAINABLE);
    }

    @Override
    public boolean parseChainable(String parseable, ArrayList<TypeValuePair> parsedChainable,
            ArrayList<Operand> operandvalue) {
        String[] pieces = parseable.split(",", 2);
        Operand operand = parseOperand(pieces);

        if (pieces.length > 1) {
            TypeValuePair typeValue = new TypeValuePair(key, pieces[1]);
            parsedChainable.add(typeValue);
            operandvalue.add(operand);
            return true;
        } else {
            ProjectZuluLog.severe("Error Parsing %s Parameter. Invalid Argument Length.", key.key);
            return false;
        }
    }

    @Override
    public boolean parseValue(String parseable, HashMap<String, Object> valueCache) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isValidLocation(World world, EntityLiving entity, int xCoord, int yCoord, int zCoord,
            TypeValuePair typeValuePair, HashMap<String, Object> valueCache) {
        String nbtOperation = (String) typeValuePair.getValue();
        try {
            NBTTagCompound entityNBT = new NBTTagCompound();
            entity.writeToNBT(entityNBT);
            new NBTWriter(nbtOperation).writeToNBT(entityNBT);
            entity.readFromNBT(entityNBT);
            return false;
        } catch (IllegalFormatException e) {
            ProjectZuluLog.severe("Skipping NBT Write due to %s", e.getMessage());
            return true;
        } catch (IllegalArgumentException e) {
            ProjectZuluLog.severe("Skipping NBT Write due to %s", e.getMessage());
            return true;
        }
    }
}
