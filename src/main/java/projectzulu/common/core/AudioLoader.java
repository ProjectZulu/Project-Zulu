package projectzulu.common.core;

import java.net.URL;
import java.util.ArrayList;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class AudioLoader {
    private static final ArrayList<String> soundList = new ArrayList<String>();
    private static final ArrayList<String> streamingList = new ArrayList<String>();
    private static final ArrayList<String> musicList = new ArrayList<String>();

    /**
     * 
     * @param domain Domain Name
     * @param location Sound Relative to domain/sound
     */
    public static synchronized void addSound(String domain, String location) {
        soundList.add(domain + ":" + location);
    }

    /**
     * 
     * @param domain Domain Name
     * @param location Sound Relative to domain/records
     */
    public static synchronized void addStreaming(String domain, String location) {
        streamingList.add(domain + ":" + location);
    }

    /**
     * 
     * @param domain Domain Name
     * @param location Sound Relative to domain/music
     */
    public static synchronized void addMusic(String domain, String location) {
        musicList.add(domain + ":" + location);
    }

    @ForgeSubscribe
    public void onLoadSoundSettings(SoundLoadEvent soundLoadEvent) {
        ProjectZuluLog.info("Registering Sounds");

        for (String sound : soundList) {
            soundLoadEvent.manager.addSound(sound);
        }

        for (String record : streamingList) {
            soundLoadEvent.manager.addStreaming(record);
        }

        for (String music : musicList) {
            soundLoadEvent.manager.addMusic(music);
        }
        soundList.clear();
        streamingList.clear();
        musicList.clear();
    }
}
