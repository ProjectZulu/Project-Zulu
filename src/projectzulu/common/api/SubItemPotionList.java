package projectzulu.common.api;

import projectzulu.common.potion.subitem.SubItemPotion;

import com.google.common.base.Optional;

public enum SubItemPotionList {
    /* Vanilla Alternates */
    STRENGTH, REGENERATION, POISON, WEAKNESS, MOVE_SPEED, MOVE_SLOW, FIRE_RESISTANCE, NIGHT_VISION, BLINDNESS, HEAL, HARM,

    /* Custom Potions */
    BUBBLING, INCENDIARY, SLOWFALL, CLEANSING, THORNS, JUMP, DIG_SPEED, DIG_SLOW, RESISTANCE, WATER_BREATHING;

    public Optional<SubItemPotion> subItem = Optional.absent();
}
