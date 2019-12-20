import palindrome.data.*;
import palindrome.handlers.*;

import java.lang.*;
import java.io.*;
/*
TODO:
 - Format out capital letters, spaces
 - Ban String.length()<2
 - Look for other ways of cheating
 - Simpler IO Class for debugging
 - Reformat classes
 - Implement JUnit
*/

public class MainPalindrome{
  public static void main(String[] args) throws IOException, ClassNotFoundException {
	
	GameHandler gHandler = new GameHandler();
	gHandler.startReading();

  }
}

/* Sort debug
	ArrayList<palindrome.data.GameData> al = new ArrayList<palindrome.data.GameData>();
        al.add(new palindrome.data.GameData("user1",8) );
        al.add(new palindrome.data.GameData("user2",7) );
        al.add(new palindrome.data.GameData("user3",6) );
        al.add(new palindrome.data.GameData("user4",2) );
        al.add(new palindrome.data.GameData("user5",1) );
        al.add(new palindrome.data.GameData("user6",0) );

        System.out.println(al );
        ArrayList<palindrome.data.GameData> sorted = sortAscending(al,new palindrome.data.GameData("newUser",4) );
        System.out.println(sorted );
        
        */
