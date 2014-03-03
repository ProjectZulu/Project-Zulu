package projectzulu.common.core.entitydeclaration;

import java.io.File;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.config.Configuration;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.renders.RenderWrapper;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class CreatureDeclaration implements EntityDeclaration {
    public final String mobName;
    public final int entityID;

    protected Class mobClass;
    protected EnumCreatureType enumCreatureType;
    protected boolean shouldExist = true;
    protected boolean reportSpawningInLog = false;
    protected int maxSpawnInChunk = 4;

    protected int trackingRange;
    protected int updateFrequency;
    protected boolean sendsVelocityUpdates;
    protected boolean shouldDespawn;

    protected int minDropNum = 0;
    protected int maxDropNum = 0;

    protected CreatureDeclaration(String mobName, int entityID, Class mobClass, EnumCreatureType creatureType) {
        this.mobName = mobName;
        this.entityID = entityID;
        this.mobClass = mobClass;
        this.enumCreatureType = creatureType;
        shouldDespawn = enumCreatureType == EnumCreatureType.creature ? false : true;
    }

    @Override
    public String getIdentifier() {
        return mobName;
    }

    protected void setDropAmount(int minDropNum, int maxDropNum) {
        this.minDropNum = minDropNum;
        this.maxDropNum = maxDropNum;
    }

    protected void setRegistrationProperties(int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        this.trackingRange = trackingRange;
        this.updateFrequency = updateFrequency;
        this.sendsVelocityUpdates = sendsVelocityUpdates;
    }

    @Override
    public boolean shouldExist() {
        return shouldExist;
    }

    @Override
    public void loadCreaturesFromConfig(Configuration config) {
        shouldExist = config.get("MOB CONTROLS." + mobName, mobName.toLowerCase() + " shouldExist", shouldExist)
                .getBoolean(shouldExist);
        reportSpawningInLog = config.get("MOB CONTROLS." + mobName, mobName.toLowerCase() + " reportSpawningInLog",
                reportSpawningInLog).getBoolean(reportSpawningInLog);
        updateFrequency = config.get("MOB CONTROLS." + mobName, mobName.toLowerCase() + " UpdateFrequency",
                updateFrequency).getInt(updateFrequency);
    }

    @Override
    public void loadBiomesFromConfig(Configuration config) {
    }

    @Override
    public void registerEntity() {
        EntityRegistry.registerModEntity(mobClass, mobName, entityID, ProjectZulu_Core.modInstance, trackingRange,
                updateFrequency, true);
    }

    @Override
    public void loadCustomMobData(File configDirectory) {
        Configuration config = new Configuration(new File(configDirectory, DefaultProps.configDirectory
                + DefaultProps.mobBiomeSpawnConfigFile));
        config.load();
        CustomMobData customMobData = new CustomMobData(mobName);
        outputDataToList(config, customMobData);
        CustomEntityList customEntity = CustomEntityList.getByName(mobName);
        if (customEntity != null)
            customEntity.modData = Optional.of(customMobData);
        else
            ProjectZuluLog.severe("Entity %s does not have an Entry in the CustomEntityList", mobName);

        config.save();
    }

    /*
     * Create loadCustomMobData() method which calls outputData to List. loadCustom contains calls that are the same for
     * all creatures
     */
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        customMobData.reportSpawningInLog = reportSpawningInLog;
        customMobData.creatureType = ConfigHelper.configGetCreatureType(config, "MOB CONTROLS." + mobName,
                "Creature Type", enumCreatureType);
        customMobData.shouldDespawn = config.get("MOB CONTROLS." + mobName, mobName + " Should Despawn", shouldDespawn)
                .getBoolean(true);
        ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS." + mobName, customMobData);
        customMobData.maxSpawnInChunk = maxSpawnInChunk;
        customMobData.minDropNum = config.get("MOB CONTROLS." + mobName, "Items to Drop Min", minDropNum).getInt(
                minDropNum);
        customMobData.maxDropNum = config.get("MOB CONTROLS." + mobName, "Items to Drop Max", maxDropNum).getInt(
                maxDropNum);
    }

    @Override
    public void registerEgg() {
    }

    @Override
    public void addSpawn() {
    }

    @SideOnly(Side.CLIENT)
    public abstract RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass);

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModelAndRender() {
        RenderWrapper wrapper = getEntityrender(mobClass);
        if (wrapper != null && wrapper.getRender() != null) {
            RenderingRegistry.registerEntityRenderingHandler(mobClass, wrapper.getRender());
        } else {
            throw new IllegalStateException("Entity Renderer for " + mobClass.getSimpleName() + " cannot be null");
        }
    }
}
