package projectzulu.common.mobs;

import java.util.EnumSet;

import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityCentipede extends EntityMaster implements IMob{

	public EntityCentipede(World par1World) {
		super(par1World);
		moveSpeed = 0.25f;
		setSize(0.65f, 0.5f);
		
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false, 2.5f*2.5f));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

//		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
//		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Block.tallGrass.blockID, false));
//		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 20));

		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
	}

	@Override
	public String getTexture() {
		texture = DefaultProps.mobDiretory + "serpent.png";
		return texture;
	}
	
	public int getMaxHealth(){
		return 20;
	}
	
	@Override
	public int getTotalArmorValue() {
		return 2;
	}
	
	@Override
	public void updateAITick() {
		super.updateAITick();
		/* Become Hostile at Night */
		float var3 = this.getBrightness(1.0F);
		if(var3 < 0.5){
			angerLevel = 120;
		}
	}
	
	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(2 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.centipede.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}
}
