package projectzulu.common.core.entitydeclaration;

import net.minecraftforge.common.config.Configuration;

public class EntityProperties {
    public final float maxHealth;
    public final float attackDamage;
    public final float moveSpeed;
    public final float followRange;
    public final float knockbackResistance;
    public final float flightChance;

    public EntityProperties(float health, float strength, float moveSpeed) {
        this(health, strength, moveSpeed, 0);
    }

    public EntityProperties(float health, float strength, float moveSpeed, float flightChance) {
        this(health, strength, moveSpeed, flightChance, 0.0f, 32.0f);
    }

    public EntityProperties(float health, float strength, float moveSpeed, float flightChance, float knockback, float followRange) {
        this.maxHealth = health;
        this.attackDamage = strength;
        this.moveSpeed = moveSpeed;
        this.flightChance = flightChance;
        this.knockbackResistance = knockback;
        this.followRange = followRange;
    }
    
    public EntityProperties createFromConfig(Configuration config, String entityName) {
        float maxHealth = (float) config.get("MOB CONTROLS." + entityName, "maxHealth", this.maxHealth).getDouble(
                this.maxHealth);
        float attackDamage = (float) config.get("MOB CONTROLS." + entityName, "attackDamage", this.attackDamage)
                .getDouble(this.attackDamage);
        float moveSpeed = (float) config.get("MOB CONTROLS." + entityName, "moveSpeed", this.moveSpeed).getDouble(
                this.moveSpeed);
        float flightChance = (float) config.get("MOB CONTROLS." + entityName, "flightChance", this.flightChance)
                .getDouble(this.flightChance);
        float knockbackResistance = (float) config.get("MOB CONTROLS." + entityName, "knockbackResistance",
                this.knockbackResistance).getDouble(this.knockbackResistance);
        float followRange = (float) config.get("MOB CONTROLS." + entityName, "followRange", this.followRange)
                .getDouble(this.followRange);
        return new EntityProperties(maxHealth, attackDamage, moveSpeed, flightChance, knockbackResistance, followRange);
    }
}
