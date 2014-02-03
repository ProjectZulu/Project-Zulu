package projectzulu.common.mobs;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import projectzulu.common.core.AudioLoader;
import projectzulu.common.core.DefaultProps;

public class MobSounds {

    public static void registerMobSounds() {
        String SOUND_DOMAIN = DefaultProps.coreKey;
        AudioLoader.addStreaming(SOUND_DOMAIN, "misc/summonwhispers.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/armadillodeath.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/armadillohurt.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/armadilloliving.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/lizardhurt.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/mummyroar.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/sandwormroar.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/MummyShortRoar.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/vulturehurt.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/treeenthurt.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/treeentliving.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/mammothhurt.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/mammothliving.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/foxhurtsound.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/boarliving1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/boarliving2.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/mimicliving.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/bearliving.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/crocoodileliving.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/froghurt.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/frogliving.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/penguinhurt.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/beaverliving.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/ostrichhurtsound.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/ostrichlivingsound.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/rhinohurtsound.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/rhinolivingsound.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/mammothstomp.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/rabbitdead1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/bird1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/bird2.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/bird3.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/bird4.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/bird5.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/birdhurt1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/birdhurt2.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/birdplop1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/gorillageneric1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/gorillahurt1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/elephanthurtsound.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/elephantlivingsound.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/horse1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/horse2.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/horse3.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/horse4.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/horsehurt1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/pelicanhurt.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/pelicanliving1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/pelicanliving2.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/pelicanliving3.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/eaglehurt1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/eagleliving1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/eagleliving2.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/hornbillhurt1.ogg");

        AudioLoader.addSound(SOUND_DOMAIN, "mob/hornbillliving1.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/hornbillliving2.ogg");
        AudioLoader.addSound(SOUND_DOMAIN, "mob/hornbillliving3.ogg");
    }
}
