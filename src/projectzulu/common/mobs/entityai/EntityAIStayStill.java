package projectzulu.common.mobs.entityai;

import net.minecraft.entity.ai.EntityAIBase;
import projectzulu.common.mobs.entity.EntityGenericAnimal;
import projectzulu.common.mobs.entity.EntityStates;

public class EntityAIStayStill extends EntityAIBase {
    private EntityGenericAnimal theEntity;
    EntityStates triggeringState;
    public EntityAIStayStill(EntityGenericAnimal par1EntityTameable, EntityStates triggeringState) {
        this.theEntity = par1EntityTameable;
        this.setMutexBits(5);
        this.triggeringState = triggeringState;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (this.theEntity.isInWater()){
            return false;
        }
        else if (!this.theEntity.onGround){
            return false;
        }
        else{
            return theEntity.getEntityState() == triggeringState;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting(){
        this.theEntity.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    public void resetTask(){
    	
    }
}

