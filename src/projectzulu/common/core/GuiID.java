package projectzulu.common.core;

import projectzulu.common.world.BlockDataObjects.MobSpawnerWithMeta;

public enum GuiID {
	Tombstone(0),
	FlowerPot(1),
	AnimalName(2),
	MobSpawner(3),
	Unknown(-1);
	
	private int iD;
	private GuiID(int iD) {
		this.iD = iD;
	}
	
	public int getID(){
		return iD;
	}
	
	public static GuiID getGuiIDByID(int iD){
		for (GuiID guiID : GuiID.values()) {
			if(guiID.iD == iD){
				return guiID;
			}
		}
		return Unknown;
	}	
}
