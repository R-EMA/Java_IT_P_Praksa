package praksa;

import java.util.Map;
import java.util.HashMap;

/**
 * 
 * @author emma
 * 
 * klasa smajliča
 * 
 * konstruktor "puni" mapu smajlija
 * 
 * (String) findEmotions(String) - vraća pattern grupisanih smajlija spreman za pretragu
 * 
 */

public class Emoji {
	
	public static Map<String, String> emoticons = new HashMap<String, String>();
	
	public Emoji() {
		emoticons.put("happy1","\uD83D\uDE42"); // 🙂
		emoticons.put("happy2", "\uD83D\uDE04"); // 😀  it's not \uD83D\uDE00
		emoticons.put("happy3", "\uD83D\uDE0A"); // 😊
		emoticons.put("happy_love", "\uD83D\uDE0D"); // 😍  happy & love
		emoticons.put("love", "\uD83D\uDE18"); // 😘
		
		emoticons.put("sad1", "\uD83D\uDE22"); // 😢
		emoticons.put("sad2", "\uD83D\uDE2D"); // 😭
		emoticons.put("sad3", "\uD83D\uDE1E"); // 😞
		emoticons.put("sad4", "\uD83D\uDC7F"); // 👿
	}
	
	// return group of emojies as string prepared for regex
	public String findEmotions(String em) {
		
		String emoji = "(["; // "list" of emoticons/smiles (happy, sad or love)
		String key, val; // key / value of element
		
		for(Map.Entry<String, String> entry : emoticons.entrySet()){    
	        key = entry.getKey();  
	        val = entry.getValue();  	  
	        if ( key.contains(em)) 
	        	emoji += val.toUpperCase();
	    }	   		
		
		emoji += "])";
		
		return emoji;
	}

}
