package projectzulu.common.core;

import java.net.URL;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandlerClass{

    // TODO Sound Loading: Do this later. Sound registering appears to be hardcoded to Mojang directory. Meaning sounds cannot be
    // loaded from arbitrary location.
    @ForgeSubscribe
	public void onLoadSoundSettings(SoundLoadEvent soundLoadEvent) {
    	ProjectZuluLog.info("Registering Sounds");
    	for (PairDirectoryFile<URL, String> sounds : Sounds.getSounds()) {
    		try {
    			String streamKey = "streaming";
    			if(sounds.getFile().toString().length() >= streamKey.length() && sounds.getFile().toString().substring(0,streamKey.length()).equalsIgnoreCase(streamKey)){
//    				soundLoadEvent.manager.soundPoolStreaming.addSound(sounds.getFile(), sounds.getDirectory());
//    				ProjectZuluLog.info("Registering Streaming %s", sounds.getFile().replaceAll("/", "."));
//    			}else{
//    				ProjectZuluLog.info("Registering Sound %s", sounds.getFile().replaceAll("/", "."));
//    				soundLoadEvent.manager.soundPoolSounds.addSound(sounds.getFile(), sounds.getDirectory());
    			}
    		} catch (Exception e) {
    			ProjectZuluLog.severe("Could Not Load Sound %s", sounds.getFile());
    			e.printStackTrace();
    		}
    	}
	}
}
