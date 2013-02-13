package projectzulu.common.mobs.entityai;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.world.World;
import projectzulu.common.mobs.entity.EntityGenericBreedable;

public class EntityAIMate extends EntityAIBase
{
    private EntityGenericBreedable theAnimal;
    World theWorld;
    private EntityGenericBreedable targetMate;

    /**
     * Delay preventing a baby from spawning immediately when two mate-able animals find each other.
     */
    int spawnBabyDelay = 0;

    /** The speed the creature moves at during mating behavior. */
    float moveSpeed;

    boolean shouldHop = false;
    int slimeJumpDelay = 0;
    
    public EntityAIMate(EntityGenericBreedable par1EntityAnimal, float par2) {
        this.theAnimal = par1EntityAnimal;
        this.theWorld = par1EntityAnimal.worldObj;
        this.moveSpeed = par2;
        this.setMutexBits(3);
    }
    public EntityAIMate(EntityGenericBreedable par1EntityAnimal, float par2, boolean shouldHop) {
    	this(par1EntityAnimal, par2);
    	this.shouldHop = shouldHop;
    }
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (!this.theAnimal.isInLove()) {
            return false;
        }
        else {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
    	if(shouldHop){
            tryToHop();
        }
        return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
        this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;
        
        if(shouldHop){
            tryToHop();
        }
        
        if (this.spawnBabyDelay == 60)
        {
            this.spawnBaby();
        }
    }

    /**
     * Loops through nearby animals and finds another animal of the same type that can be mated with. Returns the first
     * valid mate found.
     */
    private EntityGenericBreedable getNearbyMate() {
        float var1 = 8.0F;
        List var2 = this.theWorld.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.boundingBox.expand((double)var1, (double)var1, (double)var1));
        Iterator var3 = var2.iterator();
        EntityGenericBreedable var4;

        do {
            if (!var3.hasNext()) {
                return null;
            }

            var4 = (EntityGenericBreedable)var3.next();
        }
        while (!this.theAnimal.canMateWith(var4));

        return var4;
    }

    /**
     * Spawns a baby animal of the same type.
     */
    private void spawnBaby() {
    	EntityGenericBreedable var1 = this.theAnimal.getBabyAnimalEntity(this.targetMate);

        if (var1 != null) {
            this.theAnimal.setGrowingAge(6000);
            this.targetMate.setGrowingAge(6000);
            this.theAnimal.resetInLove();
            this.targetMate.resetInLove();
            var1.setGrowingAge(-24000);
            var1.setLocationAndAngles(this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, 0.0F, 0.0F);
            this.theWorld.spawnEntityInWorld(var1);
            Random var2 = this.theAnimal.getRNG();

            for (int var3 = 0; var3 < 7; ++var3) {
                double var4 = var2.nextGaussian() * 0.02D;
                double var6 = var2.nextGaussian() * 0.02D;
                double var8 = var2.nextGaussian() * 0.02D;
                this.theWorld.spawnParticle("heart", this.theAnimal.posX + (double)(var2.nextFloat() * this.theAnimal.width * 2.0F) - (double)this.theAnimal.width, this.theAnimal.posY + 0.5D + (double)(var2.nextFloat() * this.theAnimal.height), this.theAnimal.posZ + (double)(var2.nextFloat() * this.theAnimal.width * 2.0F) - (double)this.theAnimal.width, var4, var6, var8);
            }

            this.theWorld.spawnEntityInWorld(new EntityXPOrb(this.theWorld, this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, var2.nextInt(4) + 1));
        }
    }
    public void tryToHop(){
		if (theAnimal.onGround && this.slimeJumpDelay-- <= 0){
			this.slimeJumpDelay = this.getJumpDelay();

			theAnimal.getJumpHelper().setJumping();
			theAnimal.getNavigator().setSpeed(moveSpeed);
		}
		else{
			theAnimal.getNavigator().setSpeed(0);
		}
    }
    
	/**
	 * Gets the amount of time the slime needs to wait between jumps.
	 */
	protected int getJumpDelay(){
		return theAnimal.getRNG().nextInt(20) + 10;
	}
}