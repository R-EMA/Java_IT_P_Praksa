package praksa;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author emma
 * 
 * Class Characters
 * character = osoba, lik/likuša
 * 
 * analiza i sinteza tekst fajla koji je rezultat FileHelper.loadMetaData() metoda
 * 
 * (list) listCharacters() - lista stringova koji sadrže podatke o osobama
 * (list) listCharactersName() - lista imena osoba
 * (list) listCharactersMessages() - lista naziva fajlova sa porukama
 * (list) listCharactersHouses() - lista odanosti ("kuće" kojima pripadaju osobe)
 * 
 * (void) selectCharacter() - pomoćni metod za izbor osobe koji ih izlistava iz listCharactersName()
 *
 */

public class Characters {
			
	// list of characters in chat (list)
	// it isn't same as FileHelper.loadMetaData (string)
	public static List<String> listCharacters() {
		//sve
		List<String> lcharAll = new ArrayList<String>();
		lcharAll.addAll( Arrays.asList( FileHelper.loadMetaData().split("\n") ) );
		lcharAll.remove(0); // delete header
		return lcharAll;		
	}
	
	// list of names of characters in chat
	public static List<String> listCharactersNames() {
		
		List<String> lchar = new ArrayList<String>();
		for(String s : listCharacters()) {
			lchar.add( s.split(", ")[0]);
		}		
				
		return lchar;
	}
	
	// list of files with chat messages
	public static List<String> listCharactersMessages() {
		
		List<String> lchar = new ArrayList<String>();
		for(String s : listCharacters()) {
			lchar.add( s.split(", ")[2]);
		}		
				
		return lchar;
	}
	
	// list of houses (allegiance) 
	public static List<String> listCharactersHouses() {
		
		List<String> lchar = new ArrayList<String>();
		for(String s : listCharacters()) {
			lchar.add( s.split(", ")[1]);
		}		
				
		return lchar;
	}
	
	// select one of character (list with "id") - helper method
	public static void selectCharacter() {
		int id = 1;
		for(String s : listCharactersNames()) {
			System.out.println(id + " - " + s);
			id++;
		}		
	}
	
}	
