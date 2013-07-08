package projectzulu.common.potion;

/**
 * Helper Classes to Parse Generic Potion properties form damage.
 * 
 * Note the only damage values Guaranteed to work for any given SubItemPotion is subID and Splash. Any other bits are up
 * to individual subItem to parse and may not conform to standard.
 */
public class PotionParser {

    public static int readID(int value) {
        byte lowByte = (byte) (value & getLeastBitMask(4));
        return lowByte & 0xFF;
    }

    public static int setID(int iD, int value) {
        if (iD < 0 || iD > 15) {
            iD = iD < 0 ? 0 : 15;
        }
        return setBitRange(value, iD, 0, 4);
    }
    
    public static int readLevel(int value) {
        byte lowByte = (byte) ((value >> 4) & getLeastBitMask(2));
        return lowByte & 0xFF;
    }
    
    public static int setLevel(int level, int value) {
        if (level < 0 || level > 3) {
            level = level < 0 ? 0 : 3;
        }
        return setBitRange(value, level, 4, 2);
    }

    public static int readPower(int value) {
        byte lowByte = (byte) ((value >> 6) & getLeastBitMask(2));
        return lowByte & 0xFF;
    }
    
    public static int setPower(int power, int value) {
        if (power < 0 || power > 3) {
            power = power < 0 ? 0 : 3;
        }
        return setBitRange(value, power, 6, 2);
    }

    public static int readDuration(int value) {
        byte lowByte = (byte) ((value >> 8) & getLeastBitMask(2));
        return lowByte & 0xFF;
    }

    public static int setDuration(int duration, int value) {
        if (duration < 0 || duration > 3) {
            duration = duration < 0 ? 0 : 3;
        }
        return setBitRange(value, duration, 8, 2);
    }
    
    public static int readContainer(int value) {
        byte lowByte = (byte) ((value >> 10) & getLeastBitMask(2));
        return lowByte & 0xFF;
    }

    public static int setContainer(int container, int value) {
        if (container < 0 || container > 3) {
            container = container < 0 ? 0 : 3;
        }
        return setBitRange(value, container, 10, 2);
    }

    public static boolean isSplash(int value) {
        return isBitSet(value, 14);
    }

    public static int setSplash(int value) {
        return setBit(value, 14);
    }

    /**
     * Sets Range of Bits in origValue to bits in ValueToSet starting from staringBitToSet for bitsToSet number of bits
     * 
     * @param origValue
     * @param valueToSet
     * @param startingBitToSet
     * @param bitsToSet
     * @return
     */
    private static int setBitRange(int origValue, int valueToSet, int startingBitToSet, int bitsToSet) {
        for (int i = 0; i < bitsToSet; i++) {
            if (isBitSet(origValue, startingBitToSet + i) == !isBitSet(valueToSet, i)) {
                origValue = flipBit(origValue, startingBitToSet + i);
            }
        }
        return origValue;
    }

    private static boolean isBitSet(int value, int index) {
        return (value & (1L << index)) != 0;
    }

    private static int setBit(int value, int index) {
        return value | (1 << index);
    }

    private static int unSetBit(int value, int index) {
        return value & ~(1 << index);
    }

    private static int flipBit(int value, int index) {
        return value ^ (1 << index);
    }

    /**
     * Returns a Mask to be & with a value to get the specified number of least significant bits
     * 
     * @param value
     * @param numBits Number of Least Significant bits desired
     * @return
     */
    private static int getLeastBitMask(int numBits) {
        return ((1 << numBits) - 1);
    }
}
