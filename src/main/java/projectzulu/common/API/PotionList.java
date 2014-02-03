package projectzulu.common.api;

import net.minecraft.potion.Potion;

import com.google.common.base.Optional;

public enum PotionList {
    INSTANCE;
    public static Optional<? extends Potion> bubbling = Optional.absent();
    public static Optional<? extends Potion> incendiary = Optional.absent();
    public static Optional<? extends Potion> slowfall = Optional.absent();
    public static Optional<? extends Potion> cleansing = Optional.absent();
    public static Optional<? extends Potion> curse = Optional.absent();
    public static Optional<? extends Potion> thorn = Optional.absent();
    public static Optional<? extends Potion> heal2 = Optional.absent();
    public static Optional<? extends Potion> harm2 = Optional.absent();
}
