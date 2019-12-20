package palindrome.handlers;

import palindrome.data.*;
import palindrome.handlers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHandler{

    private String username;
    public String getUsername(){ return this.username; }
    private Integer score;
    public Integer getScore(){return this.score; }

    private BufferedReader reader;
    private LogicHandler lHandler;
    private DataHandler dHandler;

    private ArrayList<Word> allWords = new ArrayList<>();
    public ArrayList<Word> getWords(){ return this.allWords; }

    public GameHandler() throws IOException {
        reader = new BufferedReader( new InputStreamReader(System.in) );
        lHandler = new LogicHandler();

        this.username = readLine("Username: ");
        dHandler = new DataHandler(this);

        System.out.println("Welcome, "+username);
        String startMessage = ProjectStrings.startMessage;
        if(startMessage!=null) {
            System.out.println(startMessage);
        }
    }
    public void startReading() throws IOException, ClassNotFoundException {
        while(true){
            String line = readLine();
            Word word = lHandler.processWord(line);
            if(word==null){ System.out.println( ProjectStrings.notPalindromeMessage ); continue; }

            Boolean isValid = lHandler.isStringValid(allWords, word);

            if(isValid){
                allWords.add(word);
                System.out.println( word.toString() );
                dHandler.saveData();
            }else{
                System.out.println( ProjectStrings.invalidWordMessage );
            }
        }
    }
    private String readLine() throws IOException { return reader.readLine(); }
    private String readLine(String message) throws IOException {
        System.out.println(message);
        return reader.readLine();
    }
}
