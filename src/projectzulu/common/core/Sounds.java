package projectzulu.common.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Sounds {
	private static final ArrayList<PairDirectoryFile<URL,String>> soundList = new ArrayList<PairDirectoryFile<URL,String>>();
	
    public static final String SOUND_RESOURCE_LOCATION = DefaultProps.coreDiretory + "sounds/";
	static{
		String SOUND_RESOURCE_LOCATION = DefaultProps.coreDiretory + "sounds/";
		addSound(SOUND_RESOURCE_LOCATION, "armadillodeath.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "armadillohurt.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "armadilloliving.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "lizardhurt.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "mummyroar.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "sandwormroar.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "streaming.summonwhispers.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "MummyShortRoar.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "vulturehurt.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "treeenthurt.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "treeentliving.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "mammothhurt.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "mammothliving.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "foxhurtsound.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "boarliving1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "boarliving2.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "mimicliving.ogg");	
		addSound(SOUND_RESOURCE_LOCATION, "bearliving.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "crocoodileliving.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "froghurt.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "frogliving.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "penguinhurt.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "beaverliving.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "ostrichhurtsound.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "ostrichlivingsound.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "rhinohurtsound.ogg"); 
		addSound(SOUND_RESOURCE_LOCATION, "rhinolivingsound.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "mammothstomp.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "rabbitdead1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "bird1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "bird2.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "bird3.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "bird4.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "bird5.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "birdhurt1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "birdhurt2.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "birdplop1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "gorillageneric1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "gorillahurt1.ogg");	
		addSound(SOUND_RESOURCE_LOCATION, "elephanthurtsound.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "elephantlivingsound.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "horse1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "horse2.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "horse3.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "horse4.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "horsehurt1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "pelicanhurt.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "pelicanliving1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "pelicanliving2.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "pelicanliving3.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "eaglehurt1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "eagleliving1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "eagleliving2.ogg");	
		addSound(SOUND_RESOURCE_LOCATION, "hornbillhurt1.ogg");
		
		addSound(SOUND_RESOURCE_LOCATION, "hornbillliving1.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "hornbillliving2.ogg");
		addSound(SOUND_RESOURCE_LOCATION, "hornbillliving3.ogg");
	}
	
	public static synchronized void addSound(String soundDirectory, String soundFile){
		soundList.add(PairDirectoryFile.createPair(Sounds.class.getResource(soundDirectory + soundFile), soundFile));
	}
	
	public static synchronized void addSound(File soundFile){
		try {
			soundList.add(PairDirectoryFile.createPair(soundFile.toURI().toURL(), soundFile.getName()));
		} catch (MalformedURLException e) {
        	ProjectZuluLog.warning("MalformedURLException loading sound file: " + soundFile.getName());
			e.printStackTrace();
		}
	}
	
	public static synchronized ArrayList<PairDirectoryFile<URL, String>> getSounds(){
		return soundList;
	}
}


