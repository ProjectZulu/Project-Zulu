package projectzulu.common.dungeon.spawner.tag;

import java.util.IllegalFormatException;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTWriter {

    private String toWrite;

    public NBTWriter(String toWrite) {
        this.toWrite = toWrite;
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) throws IllegalFormatException, IllegalArgumentException {
        String[] tagParts = toWrite.split(",");

        NBTBase currentTag = nbtTagCompound;
        byte currentTagID = nbtTagCompound.getId();
        for (int i = 0; i < tagParts.length; i++) {
            String tagOperation = tagParts[i];
            if (tagOperation.trim().equals("")) {
                continue;
            }
            TagParser parser = TagParser.getByID(currentTagID);
            currentTag = parser.process(currentTag, tagOperation);
            currentTagID = currentTag.getId();
        }
    }

    private enum TagParser {
        TAG_LIST(9) {
            @Override
            public NBTBase process(NBTBase curTag, String tagOperation) {
                NBTTagList tag = (NBTTagList) curTag;
                String[] operations = tagOperation.split("/");
                int listIndex = ParsingHelper.parseFilteredInteger(operations[0], 0, "listIndex|" + curTag.getName());
                int childtag = ParsingHelper.parseFilteredInteger(operations[1], 0, "childTag|" + curTag.getName());
                while (tag.tagCount() < listIndex + 1) {
                    tag.appendTag(NBTBase.newTag((byte) childtag, ""));
                }
                return tag.tagAt(listIndex);
            }
        },
        TAG_COMPOUND(10) {
            @Override
            public NBTBase process(NBTBase curTag, String tagOperation) {
                NBTTagCompound tag = (NBTTagCompound) curTag;
                String[] operations = tagOperation.split("/");
                try {
                    int childtag = ParsingHelper.parseFilteredInteger(operations[1], 0, "childTag|" + curTag.getName());
                    switch (childtag) {
                    /* Container Tags */
                    case 9:
                    case 10:
                        if (tag.hasKey(operations[0])) {
                            return tag.getTag(operations[0]);
                        } else {
                            NBTBase newTag = NBTBase.newTag((byte) childtag, operations[0]);
                            tag.setTag(newTag.getName(), newTag);
                            return newTag;
                        }
                        /* Value Tags */
                    case 1:
                        tag.setByte(
                                operations[0],
                                (byte) ParsingHelper.parseFilteredInteger(operations[2].trim(), 0,
                                        "Byte|" + curTag.getName()));
                        return tag;
                    case 2:
                        tag.setShort(
                                operations[0],
                                (short) ParsingHelper.parseFilteredInteger(operations[2].trim(), 0,
                                        "Short|" + curTag.getName()));
                        return tag;
                    case 3:
                        tag.setInteger(operations[0],
                                ParsingHelper.parseFilteredInteger(operations[2].trim(), 0, "Int|" + curTag.getName()));
                        return tag;
                    case 4:
                        tag.setLong(operations[0],
                                ParsingHelper.parseFilteredLong(operations[2].trim(), 0L, "Long|" + curTag.getName()));
                        return tag;
                    case 5:
                        tag.setDouble(operations[0],
                                ParsingHelper.parseFilteredFloat(operations[2].trim(), 0, "Float|" + curTag.getName()));
                        return tag;
                    case 6:
                        tag.setDouble(operations[0], ParsingHelper.parseFilteredDouble(operations[2].trim(), 0,
                                "Double|" + curTag.getName()));
                        return tag;
                    case 8:
                        tag.setString(operations[0], operations[2].trim());
                        return tag;
                    case 7:
                        byte[] byteArray = new byte[operations.length - 2];
                        for (int i = 2; i < operations.length; i++) {
                            byteArray[i - 2] = (byte) ParsingHelper.parseFilteredInteger(operations[i - 2], 0,
                                    "ByteArray|" + curTag.getName());
                        }
                        tag.setByteArray(operations[0], byteArray);
                        return tag;
                    case 11:
                        int[] intArray = new int[operations.length - 2];
                        for (int i = 2; i < operations.length; i++) {
                            intArray[i - 2] = ParsingHelper.parseFilteredInteger(operations[i - 2], 0, "IntArray|"
                                    + curTag.getName());
                        }
                        tag.setIntArray(operations[0], intArray);
                        return tag;
                    default:
                        throw new IllegalArgumentException("Invalid Child ID " + childtag + " in " + tagOperation);
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Illegal NBT Length when processing " + tagOperation);
                }
            }
        },
        UNKNOWN(12) {
            @Override
            public NBTBase process(NBTBase curTag, String tagOperation) {
                return curTag;
            }
        };

        public final int tagID;

        private TagParser(int tagID) {
            this.tagID = tagID;
        }

        public static TagParser getByID(int iD) {
            for (TagParser parser : TagParser.values()) {
                if (parser.tagID == iD) {
                    return parser;
                }
            }
            return UNKNOWN;
        }

        /**
         * Tacks the Current NBTBase and the user-input tagOperation and process them
         * 
         * All operations, setting values and creating tags are done to curTag to maintain the NBT hierarchy
         * 
         * Container tags return the searched for child. Value tags curTag.
         */
        public abstract NBTBase process(NBTBase curTag, String tagOperation);
    }
}
