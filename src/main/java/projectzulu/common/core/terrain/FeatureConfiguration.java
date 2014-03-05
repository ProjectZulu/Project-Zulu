package projectzulu.common.core.terrain;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import projectzulu.common.core.DefaultProps;

public class FeatureConfiguration extends Configuration {

    public FeatureConfiguration(File configDirectory) {
        super(new File(configDirectory, DefaultProps.configDirectory + DefaultProps.structureBiomeConfigFile));
    }

    public Property getFeatureProperty(TerrainFeature terrainFeature, String subCategory, String propertyName,
            int defaultValue) {
        return get("Feature." + terrainFeature.getFeatureSize() + "." + terrainFeature.getFeatureName() + "."
                + subCategory, propertyName, defaultValue);
    }

    public Property getFeatureProperty(TerrainFeature terrainFeature, String subCategory, String propertyName,
            boolean defaultValue) {
        return get("Feature." + terrainFeature.getFeatureSize() + "." + terrainFeature.getFeatureName() + "."
                + subCategory, propertyName, defaultValue);
    }

    public Property getFeatureProperty(TerrainFeature terrainFeature, String subCategory, String propertyName,
            String defaultValue) {
        return get("Feature." + terrainFeature.getFeatureSize() + "." + terrainFeature.getFeatureName() + "."
                + subCategory, propertyName, defaultValue);
    }

    public Property getFeatureProperty(TerrainFeature terrainFeature, String subCategory, String propertyName,
            Double defaultValue) {
        return get("Feature." + terrainFeature.getFeatureSize() + "." + terrainFeature.getFeatureName() + "."
                + subCategory, propertyName, defaultValue);
    }

    public Property getFeatureProperty(TerrainFeature terrainFeature, String subCategory, String propertyName,
            int defaultValue, String comment) {
        return get("Feature." + terrainFeature.getFeatureSize() + "." + terrainFeature.getFeatureName() + "."
                + subCategory, propertyName, defaultValue, comment);
    }

    public Property getFeatureProperty(TerrainFeature terrainFeature, String subCategory, String propertyName,
            boolean defaultValue, String comment) {
        return get("Feature." + terrainFeature.getFeatureSize() + "." + terrainFeature.getFeatureName() + "."
                + subCategory, propertyName, defaultValue, comment);
    }

    public Property getFeatureProperty(TerrainFeature terrainFeature, String subCategory, String propertyName,
            String defaultValue, String comment) {
        return get("Feature." + terrainFeature.getFeatureSize() + "." + terrainFeature.getFeatureName() + "."
                + subCategory, propertyName, defaultValue, comment);
    }

    public Property getFeatureProperty(TerrainFeature terrainFeature, String subCategory, String propertyName,
            Double defaultValue, String comment) {
        return get("Feature." + terrainFeature.getFeatureSize() + "." + terrainFeature.getFeatureName() + "."
                + subCategory, propertyName, defaultValue, comment);
    }
}
