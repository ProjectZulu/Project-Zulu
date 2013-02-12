package projectzulu.common.core;

import java.net.URL;

import com.google.common.base.CharMatcher;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandlerClass{

    @ForgeSubscribe
	public void onLoadSoundSettings(SoundLoadEvent soundLoadEvent) {
    	ProjectZuluLog.info("Registering Sounds");
    	for (PairDirectoryFile<URL, String> sounds : Sounds.getSounds()) {
    		if(sounds.getFile().toLowerCase().contains("streaming.")){
    			soundLoadEvent.manager.soundPoolStreaming.addSound("stream/" + sounds.getFile().toLowerCase().replace("streaming.", ""),
    					sounds.getDirectory());
    		}else if(sounds.getDirectory().toString().toLowerCase().contains("streaming")){
        		soundLoadEvent.manager.soundPoolStreaming.addSound("stream/" + sounds.getFile(), sounds.getDirectory());
    		}else{
        		soundLoadEvent.manager.soundPoolSounds.addSound("sounds/" + sounds.getFile(), sounds.getDirectory());
    		}
		}
	}
}
