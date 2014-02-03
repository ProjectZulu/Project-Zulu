package projectzulu.common.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import projectzulu.experimental.ModelWarAxe;

public class ItemRendererWarAxe implements IItemRenderer{
	
	ModelWarAxe model = new ModelWarAxe();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		boolean answer = type == ItemRenderType.EQUIPPED ? true : false;
//		System.out.println(answer);

		return answer;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
//		System.out.println("shouldUseRenderHelper?");

		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
//		System.out.println("renderItem?");
		switch (type)
		{
//		  case ENTITY: renderHelmet(-0.5F, 0.5F, -0.5F); break;
		  case EQUIPPED: renderHelmet(0F, 0.4F, 0F); break;
//		  case INVENTORY: renderHelmet(1F, 0.65F, 1F); break;
		  default: break;
		}		
	}
	
	public void renderHelmet(float x, float y, float z){
		String textureLocation = "";
		GL11.glPushMatrix(); //start
//		ForgeHooksClient.bindTexture("/mods/vikinghelmet.png", 0); //TODO: Commented bindTexture
		GL11.glTranslatef(x, y, z); //size
		float var10 = 0.0625F;
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, var10);
		GL11.glPopMatrix(); //end
		  
		  
//		bindTextureByName(textureLocation);
//		GL11.glPushMatrix();
//		GL11.glDisable(GL11.GL_CULL_FACE);
//
//		GL11.glTranslatef(par1 + (float)tranOffset[meta].xCoord, par2 + (float)tranOffset[meta].yCoord, par3 + (float)tranOffset[meta].zCoord);
//		switch (meta){
//		case 1:
//			break;
//		case 2:
//			break;
//		case 3:
//			rotation = 180.0F;
//			break;
//		case 4:
//			rotation = 270.0F;
//			break;
//		case 5:
//		default:
//			rotation = 90.0F;
//		}
//		System.out.println(meta);
//		System.out.println("Skull: ".concat(Integer.toString(skullState)));
//
//		GL11.glRotatef(0.0f, 0.0f, 1.0F, 0.0f);
//		GL11.glScalef(-1.0f, -1.0F, 1.0F);
//		((ModelBase) modelHeads.get(skullType)).render((Entity)null, 0.0F, 0.0F, 0.0F, rotation, skullState, scale);
//		GL11.glPopMatrix();

//		model.render((entity)null, f, f1, f2, f3, f4, f5);
	}
}
