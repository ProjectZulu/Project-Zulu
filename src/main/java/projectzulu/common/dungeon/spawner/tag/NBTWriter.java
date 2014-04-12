package projectzulu.common.dungeon.spawner.tag;

import java.util.IllegalFormatException;
import java.util.List;

import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.core.ProjectZuluLog;

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
                int listIndex = ParsingHelper.parseFilteredInteger(operations[0], 0, "listIndex|" + tagOperation);
                int childtag = ParsingHelper.parseFilteredInteger(operations[1], 0, "childTag|" + tagOperation);
                while (tag.tagCount() < listIndex + 1) {
                    String[] values = new String[operations.length - 2];
                    for (int i = 2; i < values.length; i++) {
                        values[i - 2] = operations[i];
                    }
                    NBTBase child = createChildTag((byte) childtag, values);
                    tag.appendTag(child);
                }
                if (childtag == 9 || childtag == 10) {
                    List tagList = ObfuscationHelper
                            .getFieldFromReflection("field_74747_a", "tagList", tag, List.class);
                    return (NBTBase) tagList.get(listIndex);
                } else {
                    return tag;
                }
            }
        },
        TAG_COMPOUND(10) {
            @Override
            public NBTBase process(NBTBase curTag, String tagOperation) {
                NBTTagCompound tag = (NBTTagCompound) curTag;
                String[] operations = tagOperation.split("/");
                try {
                    int childtag = ParsingHelper.parseFilteredInteger(operations[1], 0, "childTag|" + tagOperation);
                    if (tag.hasKey(operations[0])) {
                        return tag.getTag(operations[0]);
                    } else {
                        String[] values = new String[operations.length - 2];
                        for (int i = 2; i < values.length; i++) {
                            values[i - 2] = operations[i];
                        }
                        NBTBase child = createChildTag((byte) childtag, values);
                        tag.setTag(operations[0], child);
                        return child;
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

    private static NBTBase createChildTag(byte childtag, String... values) {
        switch (childtag) {
        /* Container Tags */
        case 9:
            return new NBTTagList();
        case 10:
            return new NBTTagCompound();
            /* Value Tags: operations[2] should contain value */
        case 1:
            return new NBTTagByte(Byte.parseByte(values[0].trim()));
        case 2:
            return new NBTTagShort(Short.parseShort(values[0].trim()));
        case 3:
            return new NBTTagInt(Integer.parseInt(values[0].trim()));
        case 4:
            return new NBTTagLong(Long.parseLong(values[0].trim()));
        case 5:
            return new NBTTagFloat(Float.parseFloat(values[0].trim()));
        case 6:
            return new NBTTagDouble(Double.parseDouble(values[0].trim()));
        case 7:
            byte[] byteArray = new byte[values.length];
            for (int i = 2; i < values.length; i++) {
                byteArray[i - 2] = (byte) ParsingHelper.parseFilteredInteger(values[i], 0, "ByteArray");
            }
            return new NBTTagByteArray(byteArray);
        case 8:
            return new NBTTagString("");
        case 11:
            int[] intArray = new int[values.length - 2];
            for (int i = 2; i < values.length; i++) {
                intArray[i - 2] = (int) ParsingHelper.parseFilteredInteger(values[i], 0, "ByteArray");
            }
            return new NBTTagIntArray(intArray);
        default:
            ProjectZuluLog.severe("Unrecognised childtag tagId %s", childtag);
            throw new IllegalArgumentException();
        }
    }
}
