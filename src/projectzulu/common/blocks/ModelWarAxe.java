package projectzulu.common.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWarAxe extends ModelBase
{
  //fields
    ModelRenderer Viking_Helm_front;
    ModelRenderer Viking_Helm_right;
    ModelRenderer Viking_Helm_top;
    ModelRenderer Viking_Helm_left;
    ModelRenderer Viking_Helm_back;
    ModelRenderer hornlef3;
    ModelRenderer hornlef1;
    ModelRenderer hornlef2;
  
  public ModelWarAxe()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Viking_Helm_front = new ModelRenderer(this, 0, 0);
      Viking_Helm_front.addBox(-5F, -1F, -1F, 10, 4, 1);
      Viking_Helm_front.setRotationPoint(0F, -8F, -4F);
      Viking_Helm_front.setTextureSize(64, 32);
      Viking_Helm_front.mirror = true;
      setRotation(Viking_Helm_front, 0F, 0F, 0F);
      Viking_Helm_right = new ModelRenderer(this, 0, 0);
      Viking_Helm_right.addBox(0F, -1F, -4F, 1, 4, 8);
      Viking_Helm_right.setRotationPoint(4F, -8F, 0F);
      Viking_Helm_right.setTextureSize(64, 32);
      Viking_Helm_right.mirror = true;
      setRotation(Viking_Helm_right, 0F, 0F, 0F);
      Viking_Helm_top = new ModelRenderer(this, 0, 0);
      Viking_Helm_top.addBox(-4F, -1F, -4F, 8, 1, 8);
      Viking_Helm_top.setRotationPoint(0F, -8F, 0F);
      Viking_Helm_top.setTextureSize(64, 32);
      Viking_Helm_top.mirror = true;
      setRotation(Viking_Helm_top, 0F, 0F, 0F);
      Viking_Helm_left = new ModelRenderer(this, 0, 0);
      Viking_Helm_left.addBox(-1F, -1F, -4F, 1, 4, 8);
      Viking_Helm_left.setRotationPoint(-4F, -8F, 0F);
      Viking_Helm_left.setTextureSize(64, 32);
      Viking_Helm_left.mirror = true;
      setRotation(Viking_Helm_left, 0F, 0F, 0F);
      Viking_Helm_back = new ModelRenderer(this, 0, 0);
      Viking_Helm_back.addBox(-5F, -1F, 0F, 10, 6, 1);
      Viking_Helm_back.setRotationPoint(0F, -8F, 4F);
      Viking_Helm_back.setTextureSize(64, 32);
      Viking_Helm_back.mirror = true;
      setRotation(Viking_Helm_back, 0F, 0F, 0F);
      hornlef3 = new ModelRenderer(this, 0, 0);
      hornlef3.addBox(-4F, -2F, -1F, 4, 2, 2);
      hornlef3.setRotationPoint(-10F, -8.5F, 0F);
      hornlef3.setTextureSize(64, 32);
      hornlef3.mirror = true;
      setRotation(hornlef3, 0F, 0F, 1.53589F);
      hornlef1 = new ModelRenderer(this, 0, 0);
      hornlef1.addBox(-2F, -1.5F, -1.5F, 2, 3, 3);
      hornlef1.setRotationPoint(-5F, -7F, 0F);
      hornlef1.setTextureSize(64, 32);
      hornlef1.mirror = true;
      setRotation(hornlef1, 0F, 0F, 0F);
      hornlef2 = new ModelRenderer(this, 0, 0);
      hornlef2.addBox(-4F, -2F, -1.5F, 4, 2, 3);
      hornlef2.setRotationPoint(-7F, -5.5F, 0F);
      hornlef2.setTextureSize(64, 32);
      hornlef2.mirror = true;
      setRotation(hornlef2, 0F, 0F, 0.7853982F);
      hornlef1 = new ModelRenderer(this, 0, 0);
      hornlef1.addBox(0F, -1.5F, -1.5F, 2, 3, 3);
      hornlef1.setRotationPoint(5F, -7F, 0F);
      hornlef1.setTextureSize(64, 32);
      hornlef1.mirror = true;
      setRotation(hornlef1, 0F, 0F, 0F);
      hornlef2 = new ModelRenderer(this, 0, 0);
      hornlef2.addBox(0F, -2F, -1.5F, 4, 2, 3);
      hornlef2.setRotationPoint(7F, -5.5F, 0F);
      hornlef2.setTextureSize(64, 32);
      hornlef2.mirror = true;
      setRotation(hornlef2, 0F, 0F, -0.7853982F);
      hornlef3 = new ModelRenderer(this, 0, 0);
      hornlef3.addBox(0F, -2F, -1F, 4, 2, 2);
      hornlef3.setRotationPoint(10F, -8.5F, 0F);
      hornlef3.setTextureSize(64, 32);
      hornlef3.mirror = true;
      setRotation(hornlef3, 0F, 0F, -1.53589F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Viking_Helm_front.render(f5);
    Viking_Helm_right.render(f5);
    Viking_Helm_top.render(f5);
    Viking_Helm_left.render(f5);
    Viking_Helm_back.render(f5);
    hornlef3.render(f5);
    hornlef1.render(f5);
    hornlef2.render(f5);
    hornlef1.render(f5);
    hornlef2.render(f5);
    hornlef3.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
  }

}
