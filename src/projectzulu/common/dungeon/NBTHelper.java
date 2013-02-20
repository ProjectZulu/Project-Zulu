package projectzulu.common.dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.print.DocFlavor.BYTE_ARRAY;

import org.objectweb.asm.Type;

import projectzulu.common.core.ProjectZuluLog;

import com.google.common.base.CharMatcher;

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
 * Helper NBT Enum for proccessing NBTTagCompounds. 
 * Functions are recursive, due to the linked nature of NBTtags, they will call eachother as they go through the NBT tree.
 * The Functions are stateless, depending on the provided input.
 */
public enum NBTHelper {
	TAG_END(0) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {}
		@Override
		String getValue(NBTBase currentTag) { return ""; }
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) { return null; }
	},
	TAG_BYTE(1) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagByte nbtTag = (NBTTagByte)currentNode.getData();
			nbtTagCompound.setByte(nbtTag.getName(), nbtTag.data);
		}
		@Override
		String getValue(NBTBase currentTag) {
			return Byte.toString(((NBTTagByte)currentTag).data);
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			ProjectZuluLog.info("Setting Byte Value");
			NBTTagByte nbtTag = (NBTTagByte)currentNode.getData();
			return new NBTTagByte(nbtTag.getName(), Byte.parseByte(newValue)); 
		}
	},
	TAG_SHORT(2) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}

		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagShort nbtTag = (NBTTagShort)currentNode.getData();
			nbtTagCompound.setShort(nbtTag.getName(), nbtTag.data);
		}
		@Override
		String getValue(NBTBase currentTag) {
			return Short.toString(((NBTTagShort)currentTag).data);
		}

		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			ProjectZuluLog.info("Setting Short Value");
			NBTTagShort nbtTag = (NBTTagShort)currentNode.getData();
			return new NBTTagShort(nbtTag.getName(), Short.parseShort(newValue)); 
		}
	},
	TAG_INT(3) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}

		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagInt nbtTag = (NBTTagInt)currentNode.getData();
			nbtTagCompound.setInteger(nbtTag.getName(), nbtTag.data);
		}
		@Override
		String getValue(NBTBase currentTag) {
			return Integer.toString(((NBTTagInt)currentTag).data);
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			ProjectZuluLog.info("Setting Int Value");
			NBTTagInt nbtTag = (NBTTagInt)currentNode.getData();
			return new NBTTagInt(nbtTag.getName(), Integer.parseInt(newValue)); 
		}
	},
	TAG_LONG(4) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}
		
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagLong nbtTag = (NBTTagLong)currentNode.getData();
			nbtTagCompound.setLong(nbtTag.getName(), nbtTag.data);
		}
		@Override
		String getValue(NBTBase currentTag) {
			return Long.toString(((NBTTagLong)currentTag).data);
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			ProjectZuluLog.info("Setting Long Value");
			NBTTagLong nbtTag = (NBTTagLong)currentNode.getData();
			return new NBTTagLong(nbtTag.getName(), Long.parseLong(newValue)); 

		}
	},
	TAG_FLOAT(5) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}
		
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagFloat nbtTag = (NBTTagFloat)currentNode.getData();
			nbtTagCompound.setFloat(nbtTag.getName(), nbtTag.data);
		}
		@Override
		String getValue(NBTBase currentTag) {
			return Float.toString(((NBTTagFloat)currentTag).data);
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			ProjectZuluLog.info("Setting Float Value");
			NBTTagFloat nbtTag = (NBTTagFloat)currentNode.getData();
			return new NBTTagFloat(nbtTag.getName(), Float.parseFloat(newValue)); 
		}
	},
	TAG_DOUBLE(6) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagDouble nbtTag = (NBTTagDouble)currentNode.getData();
			nbtTagCompound.setDouble(nbtTag.getName(), nbtTag.data);
		}
		@Override
		String getValue(NBTBase currentTag) {
			return Double.toString(((NBTTagDouble)currentTag).data);
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			ProjectZuluLog.info("Setting Double Value");
			NBTTagDouble nbtTag = (NBTTagDouble)currentNode.getData();
			return new NBTTagDouble(nbtTag.getName(), Double.parseDouble(newValue)); 
		}
	},
	TAG_BYTE_ARRAY(7) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}
		
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagByteArray nbtTag = (NBTTagByteArray)currentNode.getData();
			nbtTagCompound.setByteArray(nbtTag.getName(), nbtTag.byteArray);
		}
		@Override
		String getValue(NBTBase currentTag) {
			byte[] byteArray = ((NBTTagByteArray)currentTag).byteArray;
			String value = "{";
			for (int i = 0; i < byteArray.length; i++) {
				value = value.concat(Byte.toString(byteArray[i])).concat(",");
			}
			value = value.concat("}");
			return value;
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			ProjectZuluLog.info("Setting Byte Array Value");
			NBTTagByteArray nbtTag = (NBTTagByteArray)currentNode.getData();
			ArrayList<Byte> fillerByteArray = new ArrayList();
			Scanner scanner = new Scanner(newValue);
			while(scanner.hasNextByte()){
				fillerByteArray.add(scanner.nextByte());
			}
			scanner.close();
			byte[] resultByte = new byte[fillerByteArray.size()];
			for (int i = 0; i < fillerByteArray.size(); i++) {
				resultByte[i] = fillerByteArray.get(i);
			}
			return new NBTTagByteArray(nbtTag.getName(), resultByte); 
		}
	},
	TAG_STRING(8) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagString nbtTag = (NBTTagString)currentNode.getData();
			nbtTagCompound.setString(nbtTag.getName(), nbtTag.data);
		}
		@Override
		String getValue(NBTBase currentTag) {
			return ((NBTTagString)currentTag).data;
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			ProjectZuluLog.info("Setting String Value");
			NBTTagString nbtTag = (NBTTagString)currentNode.getData();
			return new NBTTagString(nbtTag.getName(), newValue); 
		}
	},
	TAG_LIST(9) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			ArrayList children = new ArrayList();
			NBTTagList nbtTagList = (NBTTagList)currentTag;
			for (int i = 0; i < nbtTagList.tagCount(); i++) {
				children.add(new NBTNode(nbtTagList.tagAt(i), currentNode) );
			}
			return children;
		}
		
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagList oldTagList = (NBTTagList)currentNode.getData();
			NBTTagList newTagList = new NBTTagList(oldTagList.getName());
			for (NBTNode childNode : currentNode.getChildren()) {
				newTagList.appendTag(childNode.getData());
			}
			nbtTagCompound.setTag(newTagList.getName(), newTagList);
		}
		/** Return Number of Tags */
		@Override
		String getValue(NBTBase currentTag) {
			return Integer.toString(((NBTTagList)currentTag).tagCount());
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			return null; //TODO: Add Tag Name
		}
	},
	TAG_COMPOUND(10) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			ArrayList children = new ArrayList();
			NBTTagCompound nbtTagList = (NBTTagCompound)currentTag;
			
			for (Object object : nbtTagList.getTags()) {
				NBTBase nbtBase = (NBTBase)object;
				children.add(new NBTNode(nbtBase, currentNode));
			}
			return children;
		}
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagCompound oldNBTTagCompound = (NBTTagCompound)currentNode.getData();
			NBTTagCompound newNBTTagCompound = new NBTTagCompound(oldNBTTagCompound.getName());
			for (NBTNode childNode : currentNode.getChildren()) {
				NBTHelper helper = getByID(childNode.getData().getId());
				helper.writeToNBT(newNBTTagCompound, childNode);
			}
			nbtTagCompound.setTag(newNBTTagCompound.getName(), newNBTTagCompound);
		}
		/** Return Number of Tags */
		@Override
		String getValue(NBTBase currentTag) {
			return Integer.toString(((NBTTagCompound)currentTag).getTags().size());
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			return null; //TODO: Add Tag Name
		}
	},
	TAG_INT_ARRAY(11) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}

		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {
			NBTTagIntArray nbtTag = (NBTTagIntArray)currentNode.getData();
			nbtTagCompound.setIntArray(nbtTag.getName(), nbtTag.intArray);
		}
		@Override
		String getValue(NBTBase currentTag) {
			int[] intArray = ((NBTTagIntArray)currentTag).intArray;
			String value = "{";
			for (int i = 0; i < intArray.length; i++) {
				value = value.concat(Integer.toString(intArray[i])).concat(",");
			}
			value = value.concat("}");
			return value;
		}
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) {
			ProjectZuluLog.info("Setting Int Array Value");
			NBTTagIntArray nbtTag = (NBTTagIntArray)currentNode.getData();
			ArrayList<Integer> fillerIntArray = new ArrayList();
			Scanner scanner = new Scanner(newValue);
			while(scanner.hasNextInt()){
				fillerIntArray.add(scanner.nextInt());
			}
			scanner.close();
			int[] resultInt = new int[fillerIntArray.size()];
			for (int i = 0; i < fillerIntArray.size(); i++) {
				resultInt[i] = fillerIntArray.get(i);
			}
			return new NBTTagIntArray(nbtTag.getName(), resultInt); 
		}
	},
	UNKNOWN(12) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}
		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {}
		@Override
		String getValue(NBTBase currentTag) { return ""; }
		@Override
		NBTBase getNBTFromString(NBTNode currentNode, String newValue) { return null; }
	};	
	
	public int iD;
	private NBTHelper(int iD) {
		this.iD = iD;
	}
	
	abstract ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode);
	abstract void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode);
	/** Return Value of Tag in the Form of a String. For Container tags, such as @link{NBTTagList} it returns List Size TODO: Should return list of Tags */
	abstract String getValue(NBTBase currentTag);
	/** Sets Value of Value in the Form of a String. For Container tags, such as @link{NBTTagList} Does Nothing TODO: it sets the List Size*/
	abstract NBTBase getNBTFromString(NBTNode currentNode, String newValue);

	public static NBTHelper getByID(int iD){
		for (NBTHelper nbtHelper : NBTHelper.values()) {
			if(nbtHelper.iD == iD){
				return nbtHelper;
			}
		}
		return UNKNOWN;
	}
}
