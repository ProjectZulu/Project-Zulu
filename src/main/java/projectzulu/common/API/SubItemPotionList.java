package projectzulu.common.api;

import projectzulu.common.potion.subitem.SubItemPotion;

import com.google.common.base.Optional;

public enum SubItemPotionList {
    /* Vanilla Alternates */
    STRENGTH, REGENERATION, POISON, WEAKNESS, MOVE_SPEED, MOVE_SLOW, FIRE_RESISTANCE, NIGHT_VISION, BLINDNESS, HEAL,
    HARM, INVISIBILITY,

    /* Custom Potions */
    BUBBLING, INCENDIARY, SLOWFALL, CLEANSING, THORNS, JUMP, DIG_SPEED, DIG_SLOW, RESISTANCE, WATER_BREATHING, CURSE;

    private Optional<SubItemPotion> subItem = Optional.absent();

    public boolean isPresent() {
        return subItem.isPresent();
    }

    public SubItemPotion get() {
        return subItem.get();
    }

    public void set(SubItemPotion potion) {
        subItem = Optional.of(potion);
    }

    public void clear() {
        subItem = Optional.absent();
    }
}