package projectzulu.common.core;

public class CustomMobData {
	public String mobName = "";
	public int secondarySpawnRate = 0;
	public boolean reportSpawningInLog = false;
	
	
	public CustomMobData(String mobName, int secondarySpawnRate, boolean reportSpawningInLog){
		this.mobName = mobName;
		this.secondarySpawnRate = secondarySpawnRate;
		this.mobName = mobName;
	}
	public CustomMobData(String mobName, boolean reportSpawningInLog){
		this(mobName, 0, reportSpawningInLog);
	}
	public CustomMobData(String mobName, int secondarySpawnRate){
		this(mobName, secondarySpawnRate, false);
	}
	public CustomMobData(String mobName){
		this(mobName, 0);
	}

}
