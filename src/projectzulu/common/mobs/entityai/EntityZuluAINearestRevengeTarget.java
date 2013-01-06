//package projectzulu.common.mobs.entityai;
//
//import net.minecraft.command.IEntitySelector;
//import net.minecraft.entity.EntityLiving;
//import projectzulu.common.mobs.EntityGenericCreature;
//import projectzulu.common.mobs.EntityStates;
//
//public class EntityZuluAINearestRevengeTarget extends EntityAINearestAttackableTarget{
//
//	public EntityZuluAINearestRevengeTarget(EntityLiving par1EntityLiving, Class par2Class, float par3, int par4, boolean par5){
//		super(par1EntityLiving, par2Class, par3, par4, par5);
//	}
//
//	public EntityZuluAINearestRevengeTarget(EntityLiving par1EntityLiving, Class par2Class, float par3, int par4, boolean par5, boolean par6){
//		super(par1EntityLiving, par2Class, par3, par4, par5, par6);
//	}
//
//	public EntityZuluAINearestRevengeTarget(EntityLiving par1, Class par2, float par3, int par4, boolean par5, boolean par6, IEntitySelector par7IEntitySelector){
//		super(par1, par2, par3, par4, par5, par6, par7IEntitySelector);
//	}
//
//	/**
//	 * Returns whether the EntityAIBase should begin execution.
//	 */
//	public boolean shouldExecute() {
//		/* getAITarget acts as Tempory target timer, as it only lasts a few seconds, Reminder: getAttackTarget is the persistent form */
//		if( taskOwner.getAITarget() != null && (((EntityGenericCreature)taskOwner).getEntityState() == EntityStates.attacking || ((EntityGenericCreature)taskOwner).getEntityState() == EntityStates.looking)){
//			return super.shouldExecute();
//		}
//		return false;
//	}
//	
//	@Override
//	public boolean continueExecuting() {
//		if( taskOwner.getAITarget() != null && (((EntityGenericCreature)taskOwner).getEntityState() == EntityStates.attacking || ((EntityGenericCreature)taskOwner).getEntityState() == EntityStates.looking)){
//			return super.continueExecuting();
//		}
//		return false;
//	}
//	
//	
//}
