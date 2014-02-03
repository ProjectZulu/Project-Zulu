package projectzulu.common.mobs.entityai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;
import projectzulu.common.mobs.entity.EntityGenericCreature;
import projectzulu.common.mobs.entity.EntityStates;

public class EntityAIPanic extends EntityAIBase {
    private EntityGenericCreature theEntityCreature;
    private float speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;

    boolean shouldHop = false;
    int slimeJumpDelay = 0;   
    
    public EntityAIPanic(EntityGenericCreature par1EntityCreature, float par2) {
        this.theEntityCreature = par1EntityCreature;
        this.speed = par2;
        this.setMutexBits(1);
    }
    
    public EntityAIPanic(EntityGenericCreature par1EntityCreature, float par2, boolean shouldHop) {
    	this(par1EntityCreature, par2);
    	this.shouldHop = shouldHop;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (this.theEntityCreature.getEntityState() != EntityStates.fleeing) {
            return false;
        }
        else {
            Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 5, 4);

            if (var1 == null) {
                return false;
            }
            else {
                this.randPosX = var1.xCoord;
                this.randPosY = var1.yCoord;
                this.randPosZ = var1.zCoord;
                return true;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.theEntityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
        if(shouldHop){
            tryToHop();
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
    	if(shouldHop){
            tryToHop();
        }
        return !this.theEntityCreature.getNavigator().noPath();
    }
    
    @Override
    public void resetTask() {
    	super.resetTask();
    }
    
    public void tryToHop(){
 		if (theEntityCreature.onGround && this.slimeJumpDelay-- <= 0){
 			this.slimeJumpDelay = this.getJumpDelay();

 			theEntityCreature.getJumpHelper().setJumping();
 			theEntityCreature.getNavigator().setSpeed(speed);
 		}
 		else{
 			theEntityCreature.getNavigator().setSpeed(0);
 		}
     }
     
 	/**
 	 * Gets the amount of time the slime needs to wait between jumps.
 	 */
 	protected int getJumpDelay(){
 		return theEntityCreature.getRNG().nextInt(5) + 2;
 	}
}
