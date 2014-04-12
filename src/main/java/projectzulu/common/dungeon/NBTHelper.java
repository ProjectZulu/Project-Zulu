package projectzulu.common.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.dungeon.spawner.tag.ParsingHelper;
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

/**
 * Helper NBT Enum for proccessing NBTTagCompounds. Functions are recursive, due to the linked nature of NBTtags, they
 * will call eachother as they go through the NBT tree. The Functions are stateless, depending on the provided input.
 */
public enum NBTHelper {
    TAG_END(0) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
        }

        @Override
        String getValue(NBTBase currentTag) {
            return "";
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            return null;
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
        }
    },
    TAG_BYTE(1) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagByte nbtTag = (NBTTagByte) currentNode.getData();
            nbtTagCompound.setByte(currentNode.getTagName(), nbtTag.func_150290_f());
        }

        @Override
        String getValue(NBTBase currentTag) {
            return Byte.toString(((NBTTagByte) currentTag).func_150290_f());
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagByte nbtTag = (NBTTagByte) currentNBT;
            return new NBTTagByte(Byte.parseByte(newValue));
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            nbtTagList.appendTag(currentNode.getData());
        }
    },
    TAG_SHORT(2) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagShort nbtTag = (NBTTagShort) currentNode.getData();
            nbtTagCompound.setShort(currentNode.getTagName(), nbtTag.func_150289_e());
        }

        @Override
        String getValue(NBTBase currentTag) {
            return Short.toString(((NBTTagShort) currentTag).func_150289_e());
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagShort nbtTag = (NBTTagShort) currentNBT;
            return new NBTTagShort(Short.parseShort(newValue));
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            nbtTagList.appendTag(currentNode.getData());
        }
    },
    TAG_INT(3) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagInt nbtTag = (NBTTagInt) currentNode.getData();
            nbtTagCompound.setInteger(currentNode.getTagName(), nbtTag.func_150287_d());
        }

        @Override
        String getValue(NBTBase currentTag) {
            return Integer.toString(((NBTTagInt) currentTag).func_150287_d());
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagInt nbtTag = (NBTTagInt) currentNBT;
            return new NBTTagInt(Integer.parseInt(newValue));
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            nbtTagList.appendTag(currentNode.getData());
        }
    },
    TAG_LONG(4) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagLong nbtTag = (NBTTagLong) currentNode.getData();
            nbtTagCompound.setLong(currentNode.getTagName(), nbtTag.func_150291_c());
        }

        @Override
        String getValue(NBTBase currentTag) {
            return Long.toString(((NBTTagLong) currentTag).func_150291_c());
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagLong nbtTag = (NBTTagLong) currentNBT;
            return new NBTTagLong(Long.parseLong(newValue));
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            nbtTagList.appendTag(currentNode.getData());
        }
    },
    TAG_FLOAT(5) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagFloat nbtTag = (NBTTagFloat) currentNode.getData();
            nbtTagCompound.setFloat(currentNode.getTagName(), nbtTag.func_150288_h());
        }

        @Override
        String getValue(NBTBase currentTag) {
            return Float.toString(((NBTTagFloat) currentTag).func_150288_h());
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagFloat nbtTag = (NBTTagFloat) currentNBT;
            return new NBTTagFloat(Float.parseFloat(newValue));
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            nbtTagList.appendTag(currentNode.getData());
        }
    },
    TAG_DOUBLE(6) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagDouble nbtTag = (NBTTagDouble) currentNode.getData();
            nbtTagCompound.setDouble(currentNode.getTagName(), nbtTag.func_150286_g());
        }

        @Override
        String getValue(NBTBase currentTag) {
            return Double.toString(((NBTTagDouble) currentTag).func_150286_g());
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagDouble nbtTag = (NBTTagDouble) currentNBT;
            return new NBTTagDouble(Double.parseDouble(newValue));
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            nbtTagList.appendTag(currentNode.getData());
        }
    },
    TAG_BYTE_ARRAY(7) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagByteArray nbtTag = (NBTTagByteArray) currentNode.getData();
            nbtTagCompound.setByteArray(currentNode.getTagName(), nbtTag.func_150292_c());
        }

        @Override
        String getValue(NBTBase currentTag) {
            byte[] byteArray = ((NBTTagByteArray) currentTag).func_150292_c();
            String value = "{";
            for (int i = 0; i < byteArray.length; i++) {
                value = value.concat(Byte.toString(byteArray[i])).concat(",");
            }
            value = value.concat("}");
            return value;
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagByteArray nbtTag = (NBTTagByteArray) currentNBT;
            ArrayList<Byte> fillerByteArray = new ArrayList();
            Scanner scanner = new Scanner(newValue);
            while (scanner.hasNextByte()) {
                fillerByteArray.add(scanner.nextByte());
            }
            scanner.close();
            byte[] resultByte = new byte[fillerByteArray.size()];
            for (int i = 0; i < fillerByteArray.size(); i++) {
                resultByte[i] = fillerByteArray.get(i);
            }
            return new NBTTagByteArray(resultByte);
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            nbtTagList.appendTag(currentNode.getData());
        }
    },
    TAG_STRING(8) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagString nbtTag = (NBTTagString) currentNode.getData();
            nbtTagCompound.setString(currentNode.getTagName(),
                    nbtTag.func_150285_a_() != null ? nbtTag.func_150285_a_() : "");
        }

        @Override
        String getValue(NBTBase currentTag) {
            String string = ((NBTTagString) currentTag).func_150285_a_();
            return string != null ? string : "";
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagString nbtTag = (NBTTagString) currentNBT;
            return new NBTTagString(newValue);
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            nbtTagList.appendTag(currentNode.getData());
        }
    },
    TAG_LIST(9) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            ArrayList children = new ArrayList();
            NBTTagList nbtTagList = (NBTTagList) currentTag;
            List listOtags = ObfuscationHelper.getFieldFromReflection("field_74747_a", "tagList", nbtTagList, List.class);
            for (int i = 0; i < nbtTagList.tagCount(); i++) {
                children.add(new NBTNode((NBTBase) listOtags.get(i), currentNode, ""));
            }
            return children;
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagList oldTagList = (NBTTagList) currentNode.getData();
            NBTTagList newTagList = new NBTTagList();
            for (NBTNode childNode : currentNode.getChildren()) {
                NBTHelper helper = getByID(childNode.getData().getId());
                helper.writeToTagList(newTagList, childNode);
            }
            nbtTagCompound.setTag(currentNode.getTagName(), newTagList);
        }

        @Override
        String getValue(NBTBase currentTag) {
            NBTTagList tagList = (NBTTagList) currentTag;
            String stringBuilder = "";
            List listOtags = ObfuscationHelper.getFieldFromReflection("field_74747_a", "tagList", tagList, List.class);
            for (int i = 0; i < tagList.tagCount(); i++) {
                stringBuilder = stringBuilder + "" + ":" + ((NBTBase) listOtags.get(i)).getId() + ",";
            }
            return stringBuilder;
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagList oldTagList = (NBTTagList) currentNBT;
            List listOtags = ObfuscationHelper.getFieldFromReflection("field_74747_a", "tagList", oldTagList, List.class);
            String[] newValueParts = newValue.split(",");
            NBTTagList newTagList = new NBTTagList();
            for (int i = 0; i < newValueParts.length; i++) {
                String[] newValuePieces = newValueParts[i].split(":");
                if (i < oldTagList.tagCount()) {
                    newTagList.appendTag((NBTBase) listOtags.get(i));
                } else {
                    String[] values = new String[newValuePieces.length - 2];
                    for (int j = 2; j < values.length; j++) {
                        values[j - 2] = newValuePieces[j];
                    }
                    newTagList.appendTag(createChildTag(Byte.parseByte(newValuePieces[1]), values));
                }
            }
            return newTagList;
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            NBTTagList oldTagList = (NBTTagList) currentNode.getData();
            NBTTagList newTagList = new NBTTagList();
            for (NBTNode childNode : currentNode.getChildren()) {
                NBTHelper helper = getByID(childNode.getData().getId());
                helper.writeToTagList(newTagList, childNode);
            }
            nbtTagList.appendTag(newTagList);
        }
    },
    TAG_COMPOUND(10) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            ArrayList children = new ArrayList();
            NBTTagCompound nbtTagList = (NBTTagCompound) currentTag;

            for (Object keyObject : nbtTagList.func_150296_c()) {
                String tagKey = (String) keyObject;
                NBTBase nbtBase = nbtTagList.getTag(tagKey);
                children.add(new NBTNode(nbtBase, currentNode, tagKey));
            }
            return children;
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagCompound oldNBTTagCompound = (NBTTagCompound) currentNode.getData();
            NBTTagCompound newNBTTagCompound = new NBTTagCompound();
            for (NBTNode childNode : currentNode.getChildren()) {
                NBTHelper helper = getByID(childNode.getData().getId());
                helper.writeToNBT(newNBTTagCompound, childNode);
            }
            nbtTagCompound.setTag(currentNode.getTagName(), newNBTTagCompound);
        }

        @Override
        String getValue(NBTBase currentTag) {
            NBTTagCompound tagCompound = (NBTTagCompound) currentTag;
            String stringBuilder = "";
            for (Object keyObject : tagCompound.func_150296_c()) {
                String tagKey = (String) keyObject;
                NBTBase nbtBase = tagCompound.getTag(tagKey);
                stringBuilder = stringBuilder + tagKey + ":" + nbtBase.getId() + ",";
            }
            return stringBuilder;
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagCompound oldTagCompound = (NBTTagCompound) currentNBT;

            String[] newValueParts = newValue.split(",");
            NBTTagCompound newTagCompound = new NBTTagCompound();
            int numOldEntries = oldTagCompound.func_150296_c().size();
            ArrayList<NBTBase> oldNBT = new ArrayList<NBTBase>(numOldEntries);
            for (Object keyObject : oldTagCompound.func_150296_c()) {
                String tagKey = (String) keyObject;
                oldNBT.add(oldTagCompound.getTag(tagKey));
            }

            for (int i = 0; i < newValueParts.length; i++) {
                String[] newValuePieces = newValueParts[i].split(":");
                if (i < numOldEntries) {
                    newTagCompound.setTag(newValuePieces[0], oldNBT.get(i));
                } else {
                    String[] values = new String[newValuePieces.length - 2];
                    for (int j = 2; j < values.length; j++) {
                        values[j - 2] = newValuePieces[j];
                    }
                    newTagCompound.setTag(newValuePieces[0], createChildTag(Byte.parseByte(newValuePieces[1]), values));
                }
            }
            return newTagCompound;
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            NBTTagCompound oldTagCompound = (NBTTagCompound) currentNode.getData();
            NBTTagCompound newTagCompound = new NBTTagCompound();
            for (NBTNode childNode : currentNode.getChildren()) {
                NBTHelper helper = getByID(childNode.getData().getId());
                helper.writeToNBT(newTagCompound, childNode);
            }
            nbtTagList.appendTag(newTagCompound);
        }
    },
    TAG_INT_ARRAY(11) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
            NBTTagIntArray nbtTag = (NBTTagIntArray) currentNode.getData();
            nbtTagCompound.setIntArray(currentNode.getTagName(), nbtTag.func_150302_c());
        }

        @Override
        String getValue(NBTBase currentTag) {
            int[] intArray = ((NBTTagIntArray) currentTag).func_150302_c();
            String value = "{";
            for (int i = 0; i < intArray.length; i++) {
                value = value.concat(Integer.toString(intArray[i])).concat(",");
            }
            value = value.concat("}");
            return value;
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            NBTTagIntArray nbtTag = (NBTTagIntArray) currentNBT;
            ArrayList<Integer> fillerIntArray = new ArrayList();
            Scanner scanner = new Scanner(newValue);
            while (scanner.hasNextInt()) {
                fillerIntArray.add(scanner.nextInt());
            }
            scanner.close();
            int[] resultInt = new int[fillerIntArray.size()];
            for (int i = 0; i < fillerIntArray.size(); i++) {
                resultInt[i] = fillerIntArray.get(i);
            }
            return new NBTTagIntArray(resultInt);
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
            nbtTagList.appendTag(currentNode.getData());
        }
    },
    UNKNOWN(12) {
        @Override
        ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode) {
            return new ArrayList();
        }

        @Override
        void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
        }

        @Override
        String getValue(NBTBase currentTag) {
            return "";
        }

        @Override
        NBTBase getNBTFromString(NBTBase currentNBT, String newValue) {
            return null;
        }

        @Override
        void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode) {
        }
    };

    public int iD;

    private NBTHelper(int iD) {
        this.iD = iD;
    }

    abstract ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode);

    abstract void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode);

    /**
     * Return Value of Tag in the Form of a String. For Container tags, such as @link{NBTTagList} it returns List Size
     * TODO: Should return list of Tags
     */
    abstract String getValue(NBTBase currentTag);

    /**
     * Sets Value of Value in the Form of a String. For Container tags, such as @link{NBTTagList} Does Nothing TODO: it
     * sets the List Size
     */
    abstract NBTBase getNBTFromString(NBTBase currentNBT, String newValue);

    abstract void writeToTagList(NBTTagList nbtTagList, NBTNode currentNode);

    public static NBTHelper getByID(int iD) {
        for (NBTHelper nbtHelper : NBTHelper.values()) {
            if (nbtHelper.iD == iD) {
                return nbtHelper;
            }
        }
        return UNKNOWN;
    }

    public static NBTBase createChildTag(byte childtag, String... values) {
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
