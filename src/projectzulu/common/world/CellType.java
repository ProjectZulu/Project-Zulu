package projectzulu.common.world;

public enum CellType {
		Wall(8),
		NorthWall(0),
		SoutherWall(1),
		EastWall(2),
		WestWall(3),
		InnerWall(4),
		DeadEnd(5),
		RandomUnCarved(6),
		AirCell(7);
		
		public final int index;
		CellType(int index){
			this.index = index;
		}
		
		public int index(){ 
			return index; 
		}
}
