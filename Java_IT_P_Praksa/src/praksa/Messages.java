package praksa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author emma
 * 
 * klasa poruka
 * 
 * (void) charactersData() - pomoćni metod za "punjenje" lokalnih atributa (lista)
 * (void) showCharacterMessage(int) - prikazuje poruke jednog karaktera
 * (void) showMessagesNumbers() - broj poslatih poruka za svaki karakter
 * (void) showHappiness() - brojač sreće
 * (void) showLoveStatus(int,int) - proverava koliko se dva karaktera vole (samo pozitivna provera)
 * 
 */

public class Messages {
	
	//Characters data as Lists
	private static List<String> ch_names = new ArrayList<String>(); //list of characters
	private static List<String> ch_houses = new ArrayList<String>(); //list of allegiance
	private static List<String> ch_messages = new ArrayList<String>(); //list of messages files names

	//helper method to fill Characters data
	private static void charactersData() {
		ch_names.clear();
		ch_houses.clear();
		ch_messages.clear();
		ch_names.addAll(Characters.listCharactersNames());
		ch_houses.addAll(Characters.listCharactersHouses());
		ch_messages.addAll(Characters.listCharactersMessages());	
	}
	
	// show message for specific character
	public static void showCharacterMessage(int character_id) {
		
		if (character_id < 1 || character_id >= Characters.listCharactersNames().size() ) {
			System.out.println("Greška! Ne postoji osoba sa id oznakom " + character_id + ".");
			System.exit(0);
		}
		
		String message_file = Characters.listCharacters().get(character_id-1).split(", ")[2];
		List<String> messages = new ArrayList<String>(); //messages 
				
		messages.addAll(FileHelper.loadMessages(message_file));
		//messages.remove(0); // clear first header row
		for(String m : messages) {
			System.out.println(m);		
		}			
		
	}
	
	// number of sent messages by each character (including blanks messages)
	public static void showMessagesNumbers() {
		
		List<String> messages = new ArrayList<String>(); //context of specific message files
		int messages_number = 0; // number of messages
		int character_numbers = 0; // number of characters
		
		charactersData();
		character_numbers = ch_names.size();
				
		for(int i = 0; i < character_numbers; i++) {
			messages.addAll(FileHelper.loadMessages(ch_messages.get(i)));
			//while ( messages.remove("") ); //remove all blanks lines
			messages.removeAll(Arrays.asList("")); //and this remove all blank lines too
			messages_number = messages.size() - 1; // minus header with (nick) name of character
			System.out.println(ch_names.get(i) + " from the " + ch_houses.get(i) + " sent " + messages_number + " messages.");
			messages.clear(); // empty messages list
		}
		
	}
	
	//count happy VS sad emojis
	public static void showHappiness() {
		
		Emoji em = new Emoji();
		// happy variables
		String happy_faces = em.findEmotions("happy");
		Pattern happy_pattern = Pattern.compile(happy_faces);
		Matcher happy_match;
		int happy_num = 0, happy_row = 0,	 // count happy faces
			max_happy = 0, happy_id = 0;		// store max happy character
		// sad variables
		String sad_faces = em.findEmotions("sad");
		Pattern sad_pattern = Pattern.compile(sad_faces);
		Matcher sad_match;
		int sad_num = 0, sad_row = 0,	 // count sad faces
			max_sad = 0, sad_id = 0;			// store max sad character
		// neutral		
		int characters_number;
		String emoji_state = ""; // happy or sad
		List<String> messages = new ArrayList<String>(); // message list
		
		charactersData();
		characters_number = ch_names.size();
			
		for(int i = 0; i < characters_number; i++) {
			messages.clear();
			messages.addAll(FileHelper.loadMessages(ch_messages.get(i)));
			happy_num = 0;
			sad_num = 0;		
			for(String m : messages) {
				// happy
				happy_match = happy_pattern.matcher(m.toUpperCase());
				happy_row = (int) happy_match.results().count();
				happy_num += happy_row;
				// sad
				sad_match = sad_pattern.matcher(m.toUpperCase());
				sad_row = (int) sad_match.results().count();
				sad_num += sad_row;			
			}			
			if ( happy_num >= sad_num )
				emoji_state = "HAPPY"; // I give a slight edge to happiness :)
			else
				emoji_state = "SAD";
			// print message
			System.out.print(ch_names.get(i) + " is " + emoji_state + ". ");
			System.out.println("happy_faces = " + happy_num + " & sad_faces = " + sad_num + " in all messages.");
			// who is max happy
			if (max_happy < happy_num && happy_num > sad_num) {
				max_happy = happy_num;
				happy_id = i;
			}
			// who is max sad
			if (max_sad < sad_num && sad_num > happy_num) {
				max_sad = sad_num;
				sad_id = i;
			}
		}
		System.out.println("________");
		// print the happiest character
		System.out.println(ch_names.get(happy_id) + " from " + ch_houses.get(happy_id) 
				+ " is hapiest character in the chat with " + max_happy + " happy faces.");
		// print the saddest character
		System.out.println(ch_names.get(sad_id) + " from " + ch_houses.get(sad_id) 
				+ " is saddest character in the chat with " + max_sad + " sad faces.");
	
	}

	// who is more in love
	public static void showLoveStatus(int id_char1, int id_char2) {
		
		if (id_char1 < 1 || id_char1 >= Characters.listCharactersNames().size() ) {
			System.out.println("Greška! Ne postoji osoba sa id oznakom " + id_char1 + ".");
			System.exit(0);
		}
		if (id_char2 < 1 || id_char2 >= Characters.listCharactersNames().size() ) {
			System.out.println("Greška! Ne postoji osoba sa id oznakom " + id_char2 + ".");
			System.exit(0);
		}
		
		//one steps back because id starts from 0
		id_char1--;
		id_char2--;
		// character 1
		String message_file1 = Characters.listCharactersMessages().get(id_char1);
		String name1 = Characters.listCharactersNames().get(id_char1);
		// character 2
		String message_file2 = Characters.listCharactersMessages().get(id_char2);
		String name2 = Characters.listCharactersNames().get(id_char2);
		// together
		List<String> messages = new ArrayList<String>(); //all messages in one file
		// patterns and match
		// emoji find
		Emoji em = new Emoji();
		String love_faces = em.findEmotions("love");
		Pattern love_pattern = Pattern.compile(love_faces);
		Matcher all_matchs;	
		int num_love1 = 0, num_love2 = 0; 	// numbers of love faces
		String name_in_chat;
		
		//first person -> second person
		messages.addAll(FileHelper.loadMessages(message_file1));
		for(String m : messages) {
			name_in_chat = m.split(", ")[0];	
			if (name_in_chat.equals(name2)) {
				//System.out.println(m);
				all_matchs = love_pattern.matcher(m.toUpperCase());
				num_love1 += (int) all_matchs.results().count();				
			}
		}

		//second person -> first person
		messages.clear();
		messages.addAll(FileHelper.loadMessages(message_file2));
		for(String m : messages) {
			name_in_chat = m.split(", ")[0];	
			if (name_in_chat.equals(name1)) {
				//System.out.println(m);
				all_matchs = love_pattern.matcher(m.toUpperCase());
				num_love2 += (int) all_matchs.results().count();				
			}
		}
		
		if (num_love1 > num_love2) {
			System.out.print(name1 + " (" + num_love1 + ") love is stronger. ");
			System.out.println(name2 + " (" + num_love2 + ") loves less.");
		}
		else {
			System.out.print(name2 + " (" + num_love2 + ") love is stronger. ");
			System.out.println(name1 + " (" + num_love1 + ") loves less.");
		}
		
	}

}
