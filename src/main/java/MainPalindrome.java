import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
/*
TODO:
 - Format out capital letters, spaces
 - Ban String.length()<2
 - Look for other ways of cheating 
*/

public class MainPalindrome{
  public static void main(String[] args) throws IOException, ClassNotFoundException {
	
	GameHandler gHandler = new GameHandler();
	gHandler.startReading();

  }
}

class GameHandler{

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

class DataHandler implements Serializable {
    //Gives user score, record word, ?other features
    private String username;
    private GameHandler gHandler;
    private FileHandler fHandler;
    public DataHandler(GameHandler gHandler) throws IOException {
        //Get gHandler username, dump correct answers to file
        this.fHandler = new FileHandler(ProjectStrings.filename);
        this.username = gHandler.getUsername();
        this.gHandler = gHandler;



    }
    public void saveData() throws IOException, ClassNotFoundException {
        ArrayList<Word> words = gHandler.getWords();
        Integer totalScore = 0;
        for (Word word : words) {
            totalScore += word.getScore();
        }
        GameData gData = new GameData(this.username, totalScore);

        ArrayList<GameData> gDataRet = fHandler.ReadFromFile();
        System.out.println("gData: "+gData);
        System.out.println("gDataRet: "+gDataRet);
        ArrayList<GameData> gDataSort = sortAscending( gDataRet, gData );


        System.out.println("gDataSort: "+gDataSort);

        fHandler.SaveToFile(gDataSort);
    }
    public ArrayList<GameData> sortAscending(ArrayList<GameData> lData, GameData data){
        boolean newUser = true;

        for (GameData feachData: lData) {
            if( feachData.getUsername().equals( data.getUsername() ) ){
                newUser=false;

                if( data.getScore() > feachData.getScore() )
                    feachData.setScore( data.getScore() );
            }

        }
        if(newUser)
            lData.add(data);

        Collections.sort(lData, new Comparator<GameData>() {
            public int compare(GameData o1, GameData o2) {
                return o1.getScore().compareTo( o2.getScore() );
            }
        });

        Integer lSize = lData.size();
        if(lSize>5)
            lData.subList(0,lSize-5).clear();

        return lData;
    }
}

class GameData implements Serializable{
    //Sent straight to FileHandler/DB
    private String username;
    private Integer score;
    public Integer getScore(){return this.score;}
    public void setScore(Integer score){this.score=score;}
    public  String getUsername(){return this.username;}
    public GameData(String username, Integer score){
        this.username=username;
        this.score=score;
    }
    @Override
    public String toString(){ return this.username+" : "+this.score; }

}

class LogicHandler{
    public Boolean isStringValid(ArrayList<Word> words, Word checkWord){ //TODO: Implement check for spaces, length, etc.
        Boolean ret = true;
        String checkString = checkWord.getWord();

        for (Word word : words) {
            if( word.getWord().equals(checkString) ){
                ret = false;
            }
        }
        return ret;
    }
    public Word processWord(String word){
        String reverse = reverseWord(word);
        Integer score = getWordScore(word, reverse);
        
        if(score==0){ return null; }

        return new Word(word,reverse,score);
    }
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
}

class Word implements UsedWord{
    public String word, reverse;
    Integer score;

    public Word(String word, String reverse, Integer score){
        this.word=word;
        this.reverse=reverse;
        this.score=score;
  }
  @Override
  public String toString(){ return String.format("Word: %s Reverse: %s Score: %d",word,reverse,score); }

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

class FileHandler {
    String fileName;
    public FileHandler(String fileName) throws IOException {
        this.fileName=fileName;
    }
    public void SaveToFile(ArrayList<GameData> obj) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName) );
        out.writeObject(obj);
    }
    public ArrayList<GameData> ReadFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream in=null;
        try{
            in = new ObjectInputStream(new FileInputStream(fileName) );
        }
        catch(FileNotFoundException e){
            ArrayList<GameData> empty = new ArrayList<GameData>();
            SaveToFile( empty );
            return empty;
        }
        return (ArrayList<GameData>) in.readObject();
    }
}

class ProjectStrings{
    public static final String startMessage = null, 
    notPalindromeMessage = "Not a palindrome", 
    invalidWordMessage = "You already used this word",
    filename = "Externals.out";
}

/* Sort debug
	ArrayList<GameData> al = new ArrayList<GameData>();
        al.add(new GameData("user1",8) );
        al.add(new GameData("user2",7) );
        al.add(new GameData("user3",6) );
        al.add(new GameData("user4",2) );
        al.add(new GameData("user5",1) );
        al.add(new GameData("user6",0) );

        System.out.println(al );
        ArrayList<GameData> sorted = sortAscending(al,new GameData("newUser",4) );
        System.out.println(sorted );
        
        */
