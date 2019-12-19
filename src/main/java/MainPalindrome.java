import java.lang.*;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;
/*
TODO:
 - Format out capital letters, spaces
 - Ban String.length()<2
 - Look for other ways of cheating 
 - ? Separate GameData class / process data in GameHandler
*/

public class MainPalindrome{
  public static BufferedReader reader;
  public static void main(String[] args) throws IOException {
	
	GameHandler gHandler = new GameHandler();
	gHandler.startReading();

  }

}

class GameHandler{
  private String username;
  private enum userActions{quit, readWord};

  public String getUsername(){ return this.username; }

  private BufferedReader reader;
  private LogicHandler lHandler;

  private ArrayList<Word> allWords = new ArrayList<Word>();  

  public GameHandler() throws IOException {
	reader = new BufferedReader( new InputStreamReader(System.in) );
	lHandler = new LogicHandler();

	String username = readLine("Username: ");
	
	this.username = username;
	System.out.println("Welcome, "+username);
	String startMessage = ProjectStrings.startMessage;
	if(startMessage!=null)
	  System.out.println(startMessage);
	
  }
  public void startReading() throws IOException{
	while(true){
	  String line = readLine("Waiting for input: ");
	  userActions action = interpretLine(line);
	  
	  //FIXME: Put this in switch readWord section
	  
	  Word word = lHandler.processWord(line);
	  System.out.println(word.toString() );
	  allWords.add(word);
	  
	  //FIXME: End

	  /* FIXME: Switch
	    switch(action){
		case (quit) :
		  //Do quit logic

		break;
		case (readWord):
		  //Do read logic

		break;	  
	  }*/
	}
  }
  private userActions interpretLine(String line){ //TODO: Compare to ProjectStrings
	//if(line.equals(ProjectStrings.userActions_quit) ) return userActions.quit;
	return userActions.readWord;
  }
  private String readLine() throws IOException {
	return reader.readLine();
  }
  private String readLine(String message) throws IOException {
	System.out.println(message);
	return reader.readLine();
  }
}
class LogicHandler{
  
  private String reverseWord(String word){
	StringBuilder sBuilder = new StringBuilder().append(word);
	return sBuilder.reverse().toString();
  }
  private Boolean checkPalindrome(String word, String reverse){
	return word.equals(reverse);
  }
  private Integer getWordScore(String word, String reverse){
	if(!checkPalindrome (word,reverse) ){ return 0; }
	else{ return word.length(); }
  }
  public Word processWord(String word){
	String reverse = reverseWord(word);
	Integer score = getWordScore(word, reverse);

	return new Word(word,reverse,score);
  }
}

class Word implements UsedWord{
  public String word, reverse;
  Integer score;

  public Word(String word, String reverse, Integer score){
      this.word=word;
      this.reverse=reverse;
      this.score=score;
  }
  @Override //TODO: Add proper JSON
  public String toString(){
      Gson g = new Gson();
      String ret= g.toJson(this );
      return ret;
  }
  //Getters
  public String getWord(){return this.word;}
  public String getReverse(){return this.reverse;}
  public Integer getScore(){return this.score;}
  public Boolean isCorrect(){return getScore()>0;}
}
interface UsedWord{
  String word=null, reverse=null;
  Integer score = 0;

  String getWord();
  String getReverse();
  Integer getScore();
  Boolean isCorrect();
}
class ProjectStrings{
  public static final String startMessage = null;
}