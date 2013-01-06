package projectzulu.common.blocks.heads;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.ModelFinch;

public class TileEntityMobHeadsRenderer extends TileEntitySpecialRenderer{
	
	List modelHeads = new ArrayList<ModelBase>();
	
	public TileEntityMobHeadsRenderer(){		
//		modelHeads.add( new ModelBirdHead() );
		modelHeads.add( new ModelFinch() );

		modelHeads.add( new ModelCrocodileHead() );
		modelHeads.add( new ModelArmadilloHead() );
		
		modelHeads.add( new ModelBearHead() );
		modelHeads.add( new ModelBearHead() );
		modelHeads.add( new ModelBearHead() );
		
		modelHeads.add( new ModelBeaverHead() );
		modelHeads.add( new ModelBoarHead() );
		modelHeads.add( new ModelGiraffeHead() );
		
		modelHeads.add( new ModelGorillaHead() );
		modelHeads.add( new ModelLizardHead() );
		modelHeads.add( new ModelMammothHead() );
		
		modelHeads.add( new ModelOstrichHead() );
		modelHeads.add( new ModelPenguinHead() );
		modelHeads.add( new ModelRhinoHead() );
		
		modelHeads.add( new ModelTreeEntHead() );
		modelHeads.add( new ModelVultureHead() );
		modelHeads.add( new ModelElephantHead() );
	}

	public void renderAModelAt(TileEntityMobHeads tile, float par1, float par2, float par3, float f){
		
		/* Get Rotation */
		float rotation = (float)(tile.getRotation()*360)/16f;

		/* Get Meta */
		int meta = 0;
		if(tile.worldObj != null){
			meta = (tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) & 7 );
		}
		
		/* Get And Set Attributes Specific to Skull Type*/
		int skullType = tile.getSkullType();
		int skullState = 0;

		String textureLocation = "/subaraki/pillar3.png";
		Vec3[] tranOffset = new Vec3[6];
		float scale = 0.0625F;

		switch (skullType) {
		case 0:
			/* Finch */
			textureLocation = "/mods/finch_red.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.5F, 0.74F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.5F, 0.26F);
			tranOffset[4] = Vec3.createVectorHelper(0.74F, 1.5F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			break;
		case 1:
			/* Crocodile */
			textureLocation = "/mods/Crocodile.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 0.55F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.4F);
			tranOffset[4] = Vec3.createVectorHelper(0.55F, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.45F, 1.75F, 0.5F);
			break;
		case 2:
			/* Armadillo */
			textureLocation = "/mods/Armadillo.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 0.80F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.20F);
			tranOffset[4] = Vec3.createVectorHelper(0.80F, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.20F, 1.75F, 0.5F);
			break;
		case 3:
			/* Black Bear */
			scale = 0.0625F+0;
			textureLocation = "/mods/bearblack.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 0.85F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.15F);
			tranOffset[4] = Vec3.createVectorHelper(0.85F, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.15F, 1.75F, 0.5F);
			break;
		case 4:
			/* Brown Bear */
			scale = 0.08f;
			textureLocation = "/mods/bearbrown.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 2.0F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.90f, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  2.1F, 0.85F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  2.1F, 0.15F);
			tranOffset[4] = Vec3.createVectorHelper(0.85F, 2.1F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.15f, 2.1F, 0.5F);
			break;
		case 5:
			/* Polar Bear */
			scale = 0.1f;
			textureLocation = "/mods/bearpolar.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  2.4F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  2.5F, 0.80F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  2.5F, 0.20f);
			tranOffset[4] = Vec3.createVectorHelper(0.80f, 2.5F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.20f, 2.5F, 0.5F);
			break;
		case 6:
			/* Beaver */
			textureLocation = "/mods/Beaver.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 0.85f);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.15F);
			tranOffset[4] = Vec3.createVectorHelper(0.85f, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.15f, 1.75F, 0.5F);
			break;
		case 7:
			/* Boar */
			textureLocation = "/mods/boar.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 0.70f);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.30f);
			tranOffset[4] = Vec3.createVectorHelper(0.70f, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.30f, 1.75F, 0.5F);
			break;
		case 8:
			/* Giraffe */
			scale = 0.035f;
//			0.0625F
			textureLocation = "/mods/giraffe.png";
			if(meta == 1){
				skullState = 1;
			}else{
				skullState = 0;
			}
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F, 0.85F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F, 1.15F, 1.00f);
			tranOffset[3] = Vec3.createVectorHelper(0.5F, 1.15F, 0.00f);
			tranOffset[4] = Vec3.createVectorHelper(1.00F, 1.15F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.00F, 1.15F, 0.5F);

			break;
		case 9:
			/* Gorilla */
			textureLocation = "/mods/gorilla1.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 0.80f);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.20f);
			tranOffset[4] = Vec3.createVectorHelper(0.80f, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.20f, 1.75F, 0.5F);
			break;
		case 10:
			/* Lizard */
			textureLocation = "/mods/Lizard.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 0.80f);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.20f);
			tranOffset[4] = Vec3.createVectorHelper(0.80f, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.20f, 1.75F, 0.5F);
			break;
		case 11:
			/* Mammoth */
//			scale = 0.0625F;
			scale = 0.0575F;
			textureLocation = "/mods/Mammoth.png";
			if(meta == 1){
				skullState = 1;
			}else {
				skullState = 0;
			}
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.40F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.56F, 0.70f);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.56F, 0.30f);
			tranOffset[4] = Vec3.createVectorHelper(0.70f, 1.56F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.30f, 1.56F, 0.5F);
			break;
		case 12:
			/* Ostrich */
			textureLocation = "/mods/ostrich.png";
			if(meta == 1){
				skullState = 1;
			}else {
				skullState = 0;
			}
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 1.2F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, -0.1F);
			tranOffset[4] = Vec3.createVectorHelper(1.15F, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(-0.15F, 1.75F, 0.5F);
			break;
		case 13:
			/* Penguin */
			textureLocation = "/mods/penguin.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.85F, 0.75f);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.85F, 0.25f);
			tranOffset[4] = Vec3.createVectorHelper(0.75f, 1.85F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.25f, 1.85F, 0.5F);
			break;
		case 14:
			/* Rhino */
			textureLocation = "/mods/Rhino.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 0.60F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.4F);
			tranOffset[4] = Vec3.createVectorHelper(0.60f, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.40f, 1.75F, 0.5F);
			break;
		case 15:
			/* TreeEnt */
			textureLocation = "/mods/Treeant.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75f, 0.55F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.4F);
			tranOffset[4] = Vec3.createVectorHelper(0.55F, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.45F, 1.75F, 0.5F);
			break;
		case 16:
			/* Vulture */
			textureLocation = "/mods/Vulture.png";
			if(meta == 1){
				skullState = 1;
			}else {
				skullState = 0;
			}
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.55F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  2.10F, 0.95F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  2.10F, 0.05F);
			tranOffset[4] = Vec3.createVectorHelper(0.95F, 2.10F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.05F, 2.10F, 0.5F);
			break;
		case 17:
			/* Elephant */
			textureLocation = "/mods/elephant.png";
			if(meta == 1){
				skullState = 1;
			}else {
				skullState = 0;
			}
			scale = 0.0505F;
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  0.9F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  0.9F, 0.59F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  0.9F, 0.41F);
			tranOffset[4] = Vec3.createVectorHelper(0.59F, 0.9F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.41F, 0.9F, 0.5F);
			break;
		default:
			/* Default */
			textureLocation = "/mods/Crocodile.png";
			tranOffset[0] = Vec3.createVectorHelper(0.26F, 1.5F, 0.5F);
			tranOffset[1] = Vec3.createVectorHelper(0.5F,  1.5F, 0.5F);
			tranOffset[2] = Vec3.createVectorHelper(0.5F,  1.75F, 0.55F);
			tranOffset[3] = Vec3.createVectorHelper(0.5F,  1.75F, 0.4F);
			tranOffset[4] = Vec3.createVectorHelper(0.55F, 1.75F, 0.5F);
			tranOffset[5] = Vec3.createVectorHelper(0.45F, 1.75F, 0.5F);
			break;
		}		

		/* Set Texture */
		bindTextureByName(textureLocation);
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);

		GL11.glTranslatef(par1 + (float)tranOffset[meta].xCoord, par2 + (float)tranOffset[meta].yCoord, par3 + (float)tranOffset[meta].zCoord);
		switch (meta){
		case 1:
			break;
		case 2:
			break;
		case 3:
			rotation = 180.0F;
			break;
		case 4:
			rotation = 270.0F;
			break;
		case 5:
		default:
			rotation = 90.0F;
		}
//		System.out.println(meta);
//		System.out.println("Skull: ".concat(Integer.toString(skullState)));

		GL11.glRotatef(0.0f, 0.0f, 1.0F, 0.0f);
		GL11.glScalef(-1.0f, -1.0F, 1.0F);
		((ModelBase) modelHeads.get(skullType)).render((Entity)null, 0.0F, 0.0F, 0.0F, rotation, skullState, scale);
		GL11.glPopMatrix();
	}


	public void renderTileEntityAt(TileEntity tileentity, double par2, double par4, double par6, float par8)
	{
		renderAModelAt((TileEntityMobHeads) tileentity, (float)par2, (float)par4, (float)par6, par8); //where to render
	}

}
