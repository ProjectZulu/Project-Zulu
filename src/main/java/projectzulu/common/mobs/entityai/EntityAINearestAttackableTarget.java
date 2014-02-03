package projectzulu.common.mobs.entityai;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import projectzulu.common.mobs.entity.EntityGenericCreature;
import projectzulu.common.mobs.entity.EntityStates;

public class EntityAINearestAttackableTarget extends EntityAITarget{
	EntityLivingBase targetEntity;
    Class targetClass;
    int targetChance;
    private final IEntitySelector entitySelector;
    EnumSet<EntityStates> setOfValidStates;
    
    /** Instance of EntityAINearestAttackableTargetSorter. */
    private EntityAINearestAttackableTargetSorter theNearestAttackableTargetSorter;

    public EntityAINearestAttackableTarget(EntityGenericCreature par1EntityLiving, EnumSet<EntityStates> setOfValidStates, Class par2Class, float par3, int par4, boolean par5){
        this(par1EntityLiving, setOfValidStates, par2Class, par3, par4, par5, false);
    }

    public EntityAINearestAttackableTarget(EntityGenericCreature par1EntityLiving, EnumSet<EntityStates> setOfValidStates, Class par2Class, float par3, int par4, boolean par5, boolean par6){
        this(par1EntityLiving, setOfValidStates, par2Class, par3, par4, par5, par6, (IEntitySelector)null);
    }

    public EntityAINearestAttackableTarget(EntityLiving par1, EnumSet<EntityStates> setOfValidStates, Class par2, float par3, int par4, boolean par5, boolean par6, IEntitySelector par7IEntitySelector){
        super(par1, par3, par5, par6);
        this.setOfValidStates = setOfValidStates;
        this.targetClass = par2;
        this.targetDistance = par3;
        this.targetChance = par4;
        this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTargetSorter(this, par1);
        this.entitySelector = par7IEntitySelector;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute(){
    	if(setOfValidStates != null && !setOfValidStates.contains(((EntityGenericCreature)taskOwner).getEntityState())){
    		return false;
    	}
    	
        if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0){
            return false;
        }
        else{
            if (this.targetClass == EntityPlayer.class){
                EntityPlayer var1 = this.taskOwner.worldObj.getClosestVulnerablePlayerToEntity(this.taskOwner, (double)this.targetDistance);

                if (this.isSuitableTarget(var1, false)){
                    this.targetEntity = var1;
                    return true;
                }
            }
            else{
                List var5 = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand((double)this.targetDistance, 4.0D, (double)this.targetDistance), this.entitySelector);
                Collections.sort(var5, this.theNearestAttackableTargetSorter);
                Iterator var2 = var5.iterator();

                while (var2.hasNext()){
                    Entity var3 = (Entity)var2.next();
                    EntityLiving var4 = (EntityLiving)var3;

                    if (this.isSuitableTarget(var4, false)){

                        this.targetEntity = var4;
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    public boolean continueExecuting() {
    	if( ((EntityGenericCreature)taskOwner).getEntityState() != EntityStates.attacking && ((EntityGenericCreature)taskOwner).getEntityState() != EntityStates.looking){
    		return false;
    	}
    	return super.continueExecuting();
    }
    
    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting(){
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }
}
