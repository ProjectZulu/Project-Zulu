package projectzulu.common.potion;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.api.PotionList;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PotionManager {
    bubbling(21) {
        @Override
        protected void setupPotion() {
            PotionList.bubbling = Optional.of((new PotionZulu(potionID, true, (165 << 16) + (131 << 8) + 70, 1, 2))
                    .setPotionName("potion.shining"));
        }

        @Override
        protected void registerPotion() {
            LanguageRegistry.instance().addStringLocalization("potion.shining.postfix", "Shining Potion");
            LanguageRegistry.instance().addStringLocalization("potion.shining", "Shiny!");
        }
    },
    incendiary(22) {
        @Override
        protected void setupPotion() {
            PotionList.incendiary = Optional.of((new PotionIncendiary(potionID, true, (133 << 16) + (69 << 8) + 26))
                    .setPotionName("potion.incendiary"));
        }

        @Override
        protected void registerPotion() {
            LanguageRegistry.instance().addStringLocalization("potion.incendiary.postfix", "Incendiary Potion");
            LanguageRegistry.instance().addStringLocalization("potion.incendiary", "Incendiary");
        }
    },
    slowfall(23) {
        @Override
        protected void setupPotion() {
            PotionList.slowfall = Optional.of((new PotionSlowFall(potionID, true, (214 << 16) + (214 << 8) + 214))
                    .setPotionName("potion.slowfall"));
        }

        @Override
        protected void registerPotion() {
            LanguageRegistry.instance().addStringLocalization("potion.slowfall.postfix", "Slowfall Potion");
            LanguageRegistry.instance().addStringLocalization("potion.slowfall", "Slowfall");
        }
    },
    cleansing(24) {
        @Override
        protected void setupPotion() {
            PotionList.cleansing = Optional.of((new PotionCleansing(potionID, true, (141 << 16) + (153 << 8) + 79))
                    .setPotionName("potion.cleansing"));
        }

        @Override
        protected void registerPotion() {
            LanguageRegistry.instance().addStringLocalization("potion.cleansing.postfix", "Cleansing Potion");
            LanguageRegistry.instance().addStringLocalization("potion.cleansing", "Cleansing");
        }
    },
    curse(25) {
        @Override
        protected void setupPotion() {
            PotionList.curse = Optional.of((new PotionCurse(potionID, true, (114 << 16) + (160 << 8) + 52))
                    .setPotionName("potion.curse"));
        }

        @Override
        protected void registerPotion() {
            LanguageRegistry.instance().addStringLocalization("potion.curse.postfix", "Cursed Potion");
            LanguageRegistry.instance().addStringLocalization("potion.curse", "Curse");
        }
    },
    thorn(26) {
        @Override
        protected void setupPotion() {
            PotionList.thorn = Optional.of((new PotionThorns(potionID, true, (18 << 16) + (133 << 8) + 34))
                    .setPotionName("potion.thorn"));
        }

        @Override
        protected void registerPotion() {
            LanguageRegistry.instance().addStringLocalization("potion.thorn.postfix", "Thorn Potion");
            LanguageRegistry.instance().addStringLocalization("potion.thorn", "Thorn");
        }
    };

    int potionID;
    public static boolean potionModuleEnabled = true;
    public static boolean enableNullPotionHandler = true;

    protected abstract void setupPotion();

    protected abstract void registerPotion();

    PotionManager(int potionID) {
        this.potionID = potionID;
    }

    public static void loadSettings(Configuration config) {
        potionModuleEnabled = config.get("Potion Controls", "Potion Module Enabled", potionModuleEnabled).getBoolean(
                potionModuleEnabled);
        enableNullPotionHandler = config.get("Potion Controls", "Enable Null Potion Handler", enableNullPotionHandler)
                .getBoolean(enableNullPotionHandler);
        for (PotionManager potion : PotionManager.values()) {
            potion.potionID = config.get("Potion Controls." + potion.toString(), "PotionID", potion.potionID).getInt(
                    potion.potionID);
        }
    }

    public static void setupAndRegisterPotions() {
        for (PotionManager potion : PotionManager.values()) {
            if (potion.potionID > 0) {
                potion.setupPotion();
                potion.registerPotion();
            }
        }

        /** Register Events and Tickers Responsible for PotionEffect if appropriate potionEffects are declared */
        if (PotionList.cleansing.isPresent() || PotionList.thorn.isPresent()) {
            MinecraftForge.EVENT_BUS.register(new PotionEventHookContainerClass());
        }
    }
}
