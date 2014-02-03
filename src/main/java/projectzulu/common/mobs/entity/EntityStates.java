package projectzulu.common.mobs.entity;

public enum EntityStates {
	/* Primary States : Practically used by Everything */
	idle(0), 
	looking(1),
	attacking(2),
	
	/* Secondary States : Common */
	fleeing(7),
	following(6),
	inLove(8),
	sitting(9),
	
	/* Tertiary States : Used Rarely */
	stayStill(11),
	posture(5),
	inCover(10),
	unknown(-1);

	public final int index;
	public int index() { return index; }

	EntityStates(int index) {
		this.index = index;
	}
	
	/* Return unknown if State Cannot be Found  */
	public static EntityStates getEntityByIndex(int index){		
		for (EntityStates entityState : EntityStates.values()) {
			if(entityState.index == index){
				return entityState;
			}
		}
		return unknown;
	}
}
