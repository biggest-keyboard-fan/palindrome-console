import palindrome.data.*;
import palindrome.handlers.*;

import java.lang.*;
import java.io.*;
import java.util.ArrayList;
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
	//new GameHandler(user) - testing mode
    //new GameHandler() - game mode
	GameHandler gHandler = new GameHandler("testUser");
	//gHandler.startReading();
    ArrayList<IOResponse> tResponses=new ArrayList<>();

    tResponses.add( gHandler.processLine("топот") );
    tResponses.add( gHandler.processLine("потоп") );
    tResponses.add( gHandler.processLine("madam") );
    tResponses.add( gHandler.processLine("radar") );
    tResponses.add( gHandler.processLine("топот") );
    tResponses.add( gHandler.processLine("madam") );

    for (IOResponse tResponse: tResponses ) {
      System.out.println(tResponse);
    }
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
