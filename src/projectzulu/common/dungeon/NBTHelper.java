package projectzulu.common.dungeon;

import java.util.ArrayList;
import java.util.Collections;

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
	},
	UNKNOWN(12) {
		@Override
		ArrayList getChildTags(NBTBase currentTag, NBTNode currentNode) {
			return new ArrayList();
		}

		@Override
		void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode) {}
	};	
	
	public int iD;
	private NBTHelper(int iD) {
		this.iD = iD;
	}
	
	abstract ArrayList<NBTNode> getChildTags(NBTBase currentTag, NBTNode currentNode);
	abstract void writeToNBT(NBTTagCompound nbtTagCompound, NBTNode currentNode);
	
	public static NBTHelper getByID(int iD){
		for (NBTHelper nbtHelper : NBTHelper.values()) {
			if(nbtHelper.iD == iD){
				return nbtHelper;
			}
		}
		return UNKNOWN;
	}
}
