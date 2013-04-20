package projectzulu.common;

public class PotionParser {

    public static int readID(int value) {
        byte lowByte = (byte) (value & getLeastBitMask(8));
        return lowByte & 0xFF;
    }

    public static int readLevel(int value) {
        byte lowByte = (byte) ((value >> 8) & getLeastBitMask(3));
        return lowByte & 0xFF;
    }

    public static int readDuration(int value) {
        byte lowByte = (byte) ((value >> 11) & getLeastBitMask(2));
        return lowByte & 0xFF;
    }

    public static int readPower(int value) {
        byte lowByte = (byte) ((value >> 14) & getLeastBitMask(2));
        return lowByte & 0xFF;
    }

    public static int setID(int value, int iD) {
        if (iD < 0 || iD > 255) {
            iD = iD < 0 ? 0 : 255;
        }
        return setBitRange(value, iD, 0, 8);
    }

    public static int setLevel(int value, int level) {
        if (level < 0 || level > 15) {
            level = level < 0 ? 0 : 15;
        }
        return setBitRange(value, level, 8, 4);
    }

    public static boolean isSplash(int value) {
        return isBitSet(value, 13);
    }

    public static int setDuration(int value, int duration) {
        if (duration < 0 || duration > 15) {
            duration = duration < 0 ? 0 : 15;
        }
        return setBitRange(value, duration, 12, 4);
    }

    public static int setPower(int value, int power) {
        if (power < 0 || power > 15) {
            power = power < 0 ? 0 : 15;
        }
        return setBitRange(value, power, 16, 4);
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
