package projectzulu.common.mobs.entityai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;
import projectzulu.common.mobs.entity.EntityGenericCreature;

public class EntityAIWander extends EntityAIBase
{
    private EntityGenericCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private float speed;
    private int chanceToMove;
    
    boolean shouldHop = false;
    int slimeJumpDelay = 0;

    public EntityAIWander(EntityGenericCreature par1EntityCreature, float speed, int chanceToMove) {
        this.entity = par1EntityCreature;
        this.speed = speed;
        this.setMutexBits(1);
        this.chanceToMove = chanceToMove;
    }
    public EntityAIWander(EntityGenericCreature par1EntityCreature, float speed, int chanceToMove, boolean shouldHop ) {
    	this(par1EntityCreature, speed, chanceToMove);
    	this.shouldHop = shouldHop;
    }

    	
    
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
    	if (this.entity.getAge() >= 100 && !entity.isEntityGrounded()) {
			return false;
		}
        else if (this.entity.getRNG().nextInt(chanceToMove) != 0) {
            return false;
        }
        else {
            Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);

            if (var1 == null) {
                return false;
            }
            else {
                this.xPosition = var1.xCoord;
                this.yPosition = var1.yCoord;
                this.zPosition = var1.zCoord;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
    	if(shouldHop){
            tryToHop();
        }
        return !this.entity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
        if(shouldHop){
            tryToHop();
        }
    }
    
    
    public void tryToHop(){
		if (entity.onGround && this.slimeJumpDelay-- <= 0){
			this.slimeJumpDelay = this.getJumpDelay();

			entity.getJumpHelper().setJumping();
			entity.getNavigator().setSpeed(speed);
		}
		else{
			entity.getNavigator().setSpeed(0);
		}
    }
    
	/**
	 * Gets the amount of time the slime needs to wait between jumps.
	 */
	protected int getJumpDelay(){
		return entity.getRNG().nextInt(20) + 10;
	}
}
