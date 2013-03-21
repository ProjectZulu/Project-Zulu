package projectzulu.common.mobs.entity;

import net.minecraft.client.model.ModelRenderer;
/**
 * Used for Models that need to Track their Rotation 
 * i.e. Rotate based on their previous rotation angle
 * See {@link #ModelArmadillo}
 */
public class EntityModelRotation {
	public float rotateAngleX = 0;
	public float rotateAngleY = 0;
	public float rotateAngleZ = 0;
	
    public float rotationPointX;
    public float rotationPointY;
    public float rotationPointZ;

	public Boolean isSetup = false;
	public String assignedPiece = "nothing";
	
	private int xAngleDirec = 1;
	private int yAngleDirec = 1;
	public int zAngleDirec = 1;

	public EntityModelRotation() {}

	public void setup(ModelRenderer modelRenderer){
		rotateAngleX = modelRenderer.rotateAngleX;
		rotateAngleY = modelRenderer.rotateAngleY;
		rotateAngleZ = modelRenderer.rotateAngleZ;
	    rotationPointX = modelRenderer.rotationPointX;
	    rotationPointY = modelRenderer.rotationPointY;
	    rotationPointZ = modelRenderer.rotationPointZ;

		isSetup = true;    	
		assignedPiece = modelRenderer.boxName;
	}
	
	public float rotateX(float increment, float min, float max){
			rotateAngleX = clamp(rotateAngleX+increment, min, max);
			return rotateAngleX;
	}
	
	public float rotateY(float increment, float min, float max){
		rotateAngleY = clamp(rotateAngleY+increment, min, max);
		return rotateAngleY;
	}
	public float rotateZ(float increment, float min, float max){
		rotateAngleZ = clamp(rotateAngleZ+increment, min, max);
		return rotateAngleZ;
	}
	
	/**
	 * rotateZ Angle.
	 * par4 = 0 = Default, clamp(min,max)
	 * par4 = 1, loop min--> max, max = min,  min -->max (Not Implemented)
	 * par4 = 2, continuous min --> max --> min
	 */
	public float rotateZ(float increment, float min, float max, int par4){
		switch (par4) {
		case 1:
			//
			return rotateAngleZ;
			
		case 2:
			if(rotateAngleZ+increment*zAngleDirec >= max){
				zAngleDirec = zAngleDirec*-1;
				rotateAngleZ = max;
				return rotateAngleZ;
			}
			
			if(rotateAngleZ+increment*zAngleDirec <= min){
				zAngleDirec = zAngleDirec*-1;
				rotateAngleZ = min;
				return rotateAngleZ;
			}
				
			rotateAngleZ = rotateAngleZ+increment*zAngleDirec;
			return rotateAngleZ;

		default:
			rotateAngleZ = clamp(rotateAngleZ+increment, min, max);
			return rotateAngleZ;
		}
	}

	/*
	 * rotateY Angle.
	 * par4 = Default, clamp(min,max)
	 * par4 = 1, loop min--> max, max = min,  min -->max (Not Implemented)
	 * par4 = 2, continuous min --> max --> min (Uses Cosine)
	 */
	public float rotateY(float increment, float min, float max, int par4){
		switch (par4) {
		case 1:
			//
			return rotateAngleY;
			
		case 2:
			if(rotateAngleY+increment*zAngleDirec >= max){
				zAngleDirec = zAngleDirec*-1;
				rotateAngleY = max;
				return rotateAngleY;
			}
			
			if(rotateAngleY+increment*zAngleDirec <= min){
				zAngleDirec = zAngleDirec*-1;
				rotateAngleY = min;
				return rotateAngleY;
			}
				
			rotateAngleY = rotateAngleY+increment*zAngleDirec;
			return rotateAngleY;

		default:
			rotateAngleY = clamp(rotateAngleY+increment, min, max);
			return rotateAngleY;
		}
	}
	
	
	
	private float clamp(float number, float min, float max){
		return Math.max(Math.min(number, max), min);
	}
	private float loop(float number, float min, float max){
		if(number>max){
			return min+number-max;
		}
		if(number<min){
			return max-(min-number);
		}
		return number;
	}
	
	
	public float translateX(float increment, float min, float max){

		rotationPointX = clamp(rotationPointX+increment, min, max);
		return rotationPointX;
	}
	public float translateY(float increment, float min, float max){

		rotationPointY = clamp(rotationPointY+increment, min, max);
		return rotationPointY;
	}
	public float translateZ(float increment, float min, float max){

		rotationPointZ = clamp(rotationPointZ+increment, min, max);
		return rotationPointZ;
	}
}
