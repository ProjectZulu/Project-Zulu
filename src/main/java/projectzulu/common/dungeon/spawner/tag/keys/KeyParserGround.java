package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

public class KeyParserGround extends KeyParserBase {

    public KeyParserGround(Key key) {
        super(key, true, KeyType.CHAINABLE);
    }

    @Override
    public boolean parseChainable(String parseable, ArrayList<TypeValuePair> parsedChainable,
            ArrayList<Operand> operandvalue) {
        String[] pieces = parseable.split(",");
        Operand operand = parseOperand(pieces);
        if (pieces.length == 1) {
            parsedChainable.add(new TypeValuePair(key, isInverted(parseable)));
            operandvalue.add(operand);
            return true;
        } else {
            ProjectZuluLog.severe("Error Parsing Needs %s parameter. Invalid Argument Length.", key.key);
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
        boolean isInverted = (Boolean) typeValuePair.getValue();
        boolean canSeeSky = canBlockSeeTheSky(world, xCoord, yCoord, zCoord);
        return isInverted ? canSeeSky : !canSeeSky;
    }

    protected boolean canBlockSeeTheSky(World world, int xCoord, int yCoord, int zCoord) {
        int blockHeight = getTopSolidOrLiquidBlock(world, xCoord, zCoord);
        return blockHeight < 0 || blockHeight <= yCoord;
    }

    /**
     * Finds the highest block on the x, z coordinate that is solid and returns its y coord. Args x, z
     */
    private int getTopSolidOrLiquidBlock(World world, int par1, int par2) {
        Chunk chunk = world.getChunkFromBlockCoords(par1, par2);
        int k = chunk.getTopFilledSegment() + 15;
        par1 &= 15;

        for (par2 &= 15; k > 0; --k) {
            Block l = chunk.getBlock(par1, k, par2);

            if (l != null && l.getMaterial().blocksMovement() && l.getMaterial() != Material.leaves
                    && l.getMaterial() != Material.wood && l.getMaterial() != Material.glass
                    && !l.isFoliage(world, par1, k, par2)) {
                return k + 1;
            }
        }
        return -1;
    }
}