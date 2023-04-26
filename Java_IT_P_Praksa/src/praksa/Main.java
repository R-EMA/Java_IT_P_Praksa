package praksa;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int id_char1, id_char2;
		
		// 2. show message of specific character by id (order in file) 
		System.out.println("\n------------------------------------");
		System.out.println("Please input id of one character to show his/her messages");
		Characters.selectCharacter();
		id_char1 = sc.nextInt();		
		Messages.showCharacterMessage(id_char1);
		
		// 3. show number of messages of each character
		System.out.println("\n------------------------------------");
		Messages.showMessagesNumbers();
		
		// 4. happy VS sad  & 5. max happy VS max sad character
		System.out.println("\n------------------------------------");
		Messages.showHappiness();
		
		// 6. two person chat. Who is more in love?
		System.out.println("\n------------------------------------");	
		System.out.println("Analysis of chat between two character");
		Characters.selectCharacter();
		System.out.print("Please input id of first character: ");
		id_char1 = sc.nextInt();
		System.out.print("Please input id of second character: ");
		id_char2 = sc.nextInt();
		Messages.showLoveStatus(id_char1, id_char2);
		sc.close();
	}

}
