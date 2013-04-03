package projectzulu.common.core;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModelHelper {

    /**
     * Absolute Value alternative to {@link MathHelper#abs(float par0)} for Double values
     */
    public static double abs(double value) {
	if (value < 0) {
	    return 0f;
	} else {
	    return value;
	}
    }

    /**
     * Maps the value in one range to a value in a second using linear interpolation. Interpolated value may be outside
     * the range of values.
     * 
     * @param value: The Original Value
     * @param set1min, set1max: Min and Max of the First Range
     * @param set2min, set2max: Min and Max of the Second Range
     * @return The New Value
     */
    public static float mapValueofSet1ToSet2(float value, float set1min, float set1max, float set2min, float set2max) {
	return (value - set1min) * ((set2max - set2min) / (set1max - set1min)) + set2min;
    }

    /**
     * Maps the value in one range to a value in a second using linear interpolation. Interpolated value will be clamped
     * to the min.max values of the target range.
     * 
     * @param value: The Original Value
     * @param set1min, set1max: Min and Max of the First Range
     * @param set2min, set2max: Min and Max of the Second Range
     * @return The New Value
     */
    public static float mapValueWithClamp(float value, float set1min, float set1max, float set2min, float set2max) {
	float value2 = (value - set1min) * ((set2max - set2min) / (set1max - set1min)) + set2min;
	value2 = MathHelper.clamp_float(value2, set2min, set2max);
	return value2;
    }

    /**
     * Vanilla Function used for Rotations
     * 
     * @param par1
     * @param par2
     * @return
     */
    // TODO: Rename To Something Decent, deobfuscate parameters
    public static float func_78172_a(float par1, float par2) {
	return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }

    /**
     * Sets the RotationPoint of the provided Model using the value interpolated to the desired ranges foreach direction
     * 
     * @param modelRenderer Model on which the transformed rotations will be applied
     * @param mapValue Original Value Typically used as the 'time' or tempo of the animation
     * @param mapMin Min Initial Range
     * @param mapMax Max Initial Range
     * @param valueSetXMin, valueSetYMin X min and Max Value Range to Map to
     * @param valueSetZMin, valueSetXMax Y min and Max Value Range to Map to
     * @param valueSetYMax, valueSetZMax Z min and Max Value Range to Map to
     */
    public static void mapRotationPoint(ModelRenderer modelRenderer, float mapValue, float mapMin, float mapMax,
	    float valueSetXMin, float valueSetYMin, float valueSetZMin, float valueSetXMax, float valueSetYMax,
	    float valueSetZMax) {
	modelRenderer.rotationPointX = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetXMin, valueSetXMax);
	modelRenderer.rotationPointY = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetYMin, valueSetYMax);
	modelRenderer.rotationPointZ = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetZMin, valueSetZMax);
    }

    /**
     * Sets the Rotation of the provided Model using the value interpolated to the desired ranges for each direction
     * 
     * @param modelRenderer Model on which the transformed rotations will be applied
     * @param mapValue Original Value Typically used as the 'time' or tempo of the animation
     * @param mapMin Min Initial Range
     * @param mapMax Max Initial Range
     * @param valueSetXMin, valueSetYMin X min and Max Value Range to Map to
     * @param valueSetZMin, valueSetXMax Y min and Max Value Range to Map to
     * @param valueSetYMax, valueSetZMax Z min and Max Value Range to Map to
     */
    public static void mapRotation(ModelRenderer modelRenderer, float mapValue, float mapMin, float mapMax,
	    float valueSetXMin, float valueSetYMin, float valueSetZMin, float valueSetXMax, float valueSetYMax,
	    float valueSetZMax) {
	modelRenderer.rotateAngleX = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetXMin, valueSetXMax);
	modelRenderer.rotateAngleY = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetYMin, valueSetYMax);
	modelRenderer.rotateAngleZ = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetZMin, valueSetZMax);
    }
}
