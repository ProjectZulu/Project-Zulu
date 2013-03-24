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
		addSound(SOUND_RESOURCE_LOCATION, "armadillodeath.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "armadillohurt.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "armadilloliving.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "lizardhurt.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "mummyroar.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "sandwormroar.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "summonwhispers.ogg", "streaming/");
		addSound(SOUND_RESOURCE_LOCATION, "MummyShortRoar.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "vulturehurt.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "treeenthurt.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "treeentliving.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "mammothhurt.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "mammothliving.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "foxhurtsound.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "boarliving1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "boarliving2.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "mimicliving.ogg", "sounds/");	
		addSound(SOUND_RESOURCE_LOCATION, "bearliving.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "crocoodileliving.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "froghurt.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "frogliving.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "penguinhurt.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "beaverliving.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "ostrichhurtsound.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "ostrichlivingsound.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "rhinohurtsound.ogg", "sounds/"); 
		addSound(SOUND_RESOURCE_LOCATION, "rhinolivingsound.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "mammothstomp.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "rabbitdead1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "bird1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "bird2.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "bird3.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "bird4.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "bird5.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "birdhurt1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "birdhurt2.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "birdplop1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "gorillageneric1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "gorillahurt1.ogg", "sounds/");	
		addSound(SOUND_RESOURCE_LOCATION, "elephanthurtsound.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "elephantlivingsound.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "horse1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "horse2.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "horse3.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "horse4.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "horsehurt1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "pelicanhurt.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "pelicanliving1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "pelicanliving2.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "pelicanliving3.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "eaglehurt1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "eagleliving1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "eagleliving2.ogg", "sounds/");	
		addSound(SOUND_RESOURCE_LOCATION, "hornbillhurt1.ogg", "sounds/");
		
		addSound(SOUND_RESOURCE_LOCATION, "hornbillliving1.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "hornbillliving2.ogg", "sounds/");
		addSound(SOUND_RESOURCE_LOCATION, "hornbillliving3.ogg", "sounds/");
	}
	
	//TODO: Change Prefix into the inGame File name such that the ingame call is independant of filename
	private static synchronized void addSound(String soundDirectory, String soundFile, String prefix){
		soundList.add(PairDirectoryFile.createPair(Sounds.class.getResource(soundDirectory + soundFile), prefix+soundFile));
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
	
	public static synchronized void addSound(File soundFile, File relativeDirectory){
		try {
			String relative = relativeDirectory.toURI().relativize(soundFile.toURI()).getPath();
			soundList.add(PairDirectoryFile.createPair(soundFile.toURI().toURL(), relative));
		} catch (MalformedURLException e) {
        	ProjectZuluLog.warning("MalformedURLException loading sound file: " + soundFile.getName());
			e.printStackTrace();
		}
	}
	
	public static synchronized ArrayList<PairDirectoryFile<URL, String>> getSounds(){
		return soundList;
	}
}


