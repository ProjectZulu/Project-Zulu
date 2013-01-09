package projectzulu.common.mobs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.ItemBlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import cpw.mods.fml.common.Loader;

public class EntityMaster extends EntityGenericAnimal{
	
	List<EntityFollower> followerList = new ArrayList();
	int numberOfFollowers = 9;
	
	boolean spawnFollowers = true;
	
	/* Represents the increments between each slave-Piece, used to determine number of locations needed to track*/
	int distanceIncrements = 10;
	float distancePerIncrement;

	boolean isPositionListSetup = false;
	LinkedList<Vec3> positionList = new LinkedList<Vec3>();
	
	public EntityMaster(World par1World) {
		super(par1World);
		
		distancePerIncrement = width/distanceIncrements;
		while(followerList.size() < numberOfFollowers){
			followerList.add(null);
		}
	}
	
	@Override
	protected int getAttackStrength(World par1World){
		switch (par1World.difficultySetting) {
		case 0:
			return 3; 
		case 1:
			return 4; 
		case 2:
			return 5; 
		case 3:
			return 6; 
		default:
			return 3;
		}
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	/* Handling of Child Entities Position Must be Handled on Server and Client Side */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if(!isPositionListSetup){
			double xzOffset = 0;
			while(positionList.size() < numberOfFollowers*distanceIncrements+1){
				positionList.add(Vec3.createVectorHelper(posX+xzOffset, posY, posZ));
				xzOffset += distancePerIncrement;
			}
			isPositionListSetup = true;
		}
		
		/* Calculate Position Array, used to set the target position for Followers */
		double minDistanceToIterateList = distancePerIncrement;
		while( calcDistance(posX, posZ, positionList.getFirst().xCoord, positionList.getFirst().zCoord) > minDistanceToIterateList){
			
			double xDist = posX - positionList.getFirst().xCoord;
			double zDist = posZ - positionList.getFirst().zCoord;

			double scaleFactor = (minDistanceToIterateList/calcDistance(posX, posZ, positionList.getFirst().xCoord, positionList.getFirst().zCoord));
			double actualXCoord = positionList.getFirst().xCoord + xDist*scaleFactor;
			double actualZCoord = positionList.getFirst().zCoord + zDist*scaleFactor;
			
			positionList.addFirst(Vec3.createVectorHelper(actualXCoord, posY, actualZCoord));
			positionList.removeLast();
		}
		
		/* Store the Values Gotten from positionList.get from previous iteration 
		 * Used to Calculate TargetRotation */
		double prevX = posX;
		double prevZ = posZ;
		for (int i = 0; i < followerList.size(); i++) {
			
			if(followerList.get(i) != null){
				EntityFollower entityFollower = followerList.get(i);
				
				/* Get Position From Position List*/
				double setXAround = positionList.get( (int) ((i+1)*distanceIncrements) ).xCoord;
				double setZAround = positionList.get( (int) ((i+1)*distanceIncrements) ).zCoord;
				double setYAround = positionList.get( (int) ((i+1)*distanceIncrements) ).yCoord;
				entityFollower.setTargetPosition(Vec3.createVectorHelper(
						setXAround, 
						posY, 
						setZAround));

				entityFollower.setTargetRotation((float) (Math.atan2(setXAround - prevX, prevZ-setZAround)*(180.0/Math.PI)));
				prevX = setXAround;
				prevZ = setZAround;
				
				/* Check if Prev Point is Dead, is so, I should probably be killed to */
				if(i > 0 && followerList.get(i-1) != null){
					if( followerList.get(i-1).isDead ){
						entityFollower.shouldBeDying = true;
					}
				}
			}
		}
	}
	
	/* Creation of Child Entities should only be Done on Server */
	@Override
	public void updateAITick() {

		/* Count Number of Followers, used to determine if more followers should be spawned */
		int currentNumOfFollowers = 0;
		for (int i = 0; i < followerList.size(); i++) {
			/* Spawn new Followers if Neccesary */
			if(followerList.get(i) == null || (followerList.get(i).isDead && spawnFollowers)){
				followerList.set(i, new EntityFollower(worldObj, posX, posY, posZ, this, i));
				worldObj.spawnEntityInWorld(followerList.get(i));
			}
			EntityFollower entityFollower = followerList.get(i);

			/* Count Number of Followers, used to determine if more followers should be spawned */
			if(!entityFollower.isDead){
				currentNumOfFollowers +=1;
			}
		}
		
		/* Check if We should Spawn More Followers*/
		spawnFollowers = currentNumOfFollowers < numberOfFollowers ? true : false;
		super.updateAITick();
	}
	
	/** 
	 * Code to Assign body to Head, called by Body client Side to establish link
	 */
	public boolean linkFollowerWithMaster(EntityFollower entityFollower, int followerIndex){
		if(followerList.get(followerIndex) != null && !followerList.get(followerIndex).isDead ){
				return false;
		}
		followerList.set(followerIndex, entityFollower);
		return true;
	}
	
	/* Shares Damage with Followers */
	public boolean attackEntityFromChild(EntityFollower var1, DamageSource var2, int var3) {		
		if( super.attackEntityFrom(var2, var3) ){
			for (int i = 0; i < followerList.size(); i++) {
				EntityFollower entityFollower = followerList.get(i);
				if(entityFollower != null){
					entityFollower.hurtChildFromMaster(var2, var3);
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		boolean wasSuccesful = false;
		
		if (CustomEntityList.centipede.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
				&& worldObj.canBlockSeeTheSky(var1, var2, var3)){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.centipede.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}
	
    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
	@Override
    protected boolean isValidLightLevel(World world, int xCoord, int yCoord, int zCoord) {
        int var1 = xCoord;
        int var2 = yCoord;
        int var3 = zCoord;
        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > this.rand.nextInt(32)) {
            return false;
        }
        else{
            int var4 = this.worldObj.getBlockLightValue(var1, var2, var3);

            if (this.worldObj.isThundering()) {
                int var5 = this.worldObj.skylightSubtracted;
                this.worldObj.skylightSubtracted = 10;
                var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
                this.worldObj.skylightSubtracted = var5;
            }

            return var4 <= this.rand.nextInt(8);
        }
    }
	
    @Override
    protected boolean canDespawn() {
    	return true;
    }
	
	private double calcDistance(double x1, double z1, double x2, double z2){
		return Math.sqrt( (x2 - x1)*(x2 - x1) + (z2 - z1)*(z2 - z1) );
	}
	
	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = this.rand.nextInt(2 + par2)+2;

		if(Loader.isModLoaded(DefaultProps.BlocksModId)){
			for (int i = 0; i < var3; ++i){
				if(ItemBlockList.scrapMeat.isPresent()){
					this.dropItem(ItemBlockList.scrapMeat.get().shiftedIndex, 1);
				}
			}
		}
	}
}
