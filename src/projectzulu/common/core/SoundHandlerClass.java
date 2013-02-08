package projectzulu.common.core;

import java.net.URL;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandlerClass{

    @ForgeSubscribe
	public void onLoadSoundSettings(SoundLoadEvent soundLoadEvent) {
    	ProjectZuluLog.info("Registering Sounds");
    	for (PairDirectoryFile<URL, String> sounds : Sounds.getSounds()) {
    		soundLoadEvent.manager.soundPoolSounds.addSound("sounds/" + sounds.getFile(), sounds.getDirectory());
		}
	}
}
