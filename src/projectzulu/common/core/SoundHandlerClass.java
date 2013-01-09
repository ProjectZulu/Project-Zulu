package projectzulu.common.core;

import java.util.logging.Level;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLCommonHandler;

//Ripped from EE3 on Github, god bless them.

public class SoundHandlerClass{

    @ForgeSubscribe
	public void onLoadSoundSettings(SoundLoadEvent soundLoadEvent) {
		for (String soundFile : Sounds.soundFiles) {
			try {
				soundLoadEvent.manager.soundPoolSounds.addSound("sounds/" + soundFile, this.getClass().getResource(Sounds.SOUND_RESOURCE_LOCATION + soundFile));
				
            }catch (Exception e) {
            	ProjectZuluLog.warning("Failed loading sound file: " + soundFile);
            }
		}
	}
}
