package com.example.handlers;

import com.example.data.IOResponse;
import com.example.data.ProjectStrings;
import com.example.data.Word;
import com.example.main.MainPalindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHandler{

    private String username=null;
    public String getUsername(){ return this.username; }
    private Integer score;
    public Integer getScore(){return this.score; }

    private BufferedReader reader;
    private DataHandler dHandler;
    public FileHandler getFileHandler(){ return this.dHandler.getFileHandler(); }

    private ArrayList<Word> allWords = new ArrayList<>();
    public ArrayList<Word> getWords(){ return this.allWords; }

    public GameHandler() throws IOException {
        reader = new BufferedReader( new InputStreamReader(System.in) );

        this.username = readLine("Username: ");
        dHandler = new DataHandler(this);

        System.out.println("Welcome, "+username);
        String startMessage = ProjectStrings.startMessage;
        if(startMessage!=null) {
            System.out.println(startMessage);
        }
    }
    public GameHandler(String username) throws IOException{
        reader = new BufferedReader( new InputStreamReader(System.in) );
        this.username=username;

        System.out.println("Testing mode. Username: "+username);

        dHandler = new DataHandler(this);
    }
    public void startReading() throws IOException, ClassNotFoundException {
        while(true){
            String line = readLine();
            IOResponse processL = processLine(line);
            if(MainPalindrome.writeFilesToConsole) System.out.println( processL );
        }
    }
    public IOResponse processLine(String line) throws IOException, ClassNotFoundException{
        Word word = LogicHandler.processWord(line);
        if(word==null){ System.out.println( ProjectStrings.notPalindromeMessage ); return new IOResponse(IOResponse.responseType.notPalindrome, null); }

        Boolean isValid = LogicHandler.isStringValid(allWords, word);

        if(isValid){
            allWords.add(word);
            if(!MainPalindrome.writeFilesToConsole) System.out.println( word.toString() );
            return new IOResponse( IOResponse.responseType.correct, dHandler.saveData() );
        }else{
            System.out.println( ProjectStrings.invalidWordMessage );
            return new IOResponse(IOResponse.responseType.used,null);
        }
    }

    private String readLine() throws IOException { return reader.readLine(); }
    private String readLine(String message) throws IOException {
        System.out.println(message);
        return reader.readLine();
    }
}
