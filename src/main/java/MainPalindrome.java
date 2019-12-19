import java.lang.*;
import java.io.*;
import java.util.*;
/*
TODO:
 - Better interface implementation
 - Format out capital letters, spaces
 - Ban String.length()<2
 - Look for other ways of cheating 
 - ? Separate GameData class / process data in GameHandler
*/

public class MainPalindrome{
  public static void main(String[] args) throws IOException {
	
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
        dHandler = new DataHandler(this);

        this.username = readLine("Username: ");

        System.out.println("Welcome, "+username);
        String startMessage = ProjectStrings.startMessage;
        if(startMessage!=null) {
            System.out.println(startMessage);
        }
    }
    public void startReading() throws IOException{
        while(true){
            String line = readLine("Waiting for input: ");
            Word word = lHandler.processWord(line);

            System.out.println( word.toString() );

            Boolean isValid = lHandler.isStringValid(allWords, word);

            if(isValid){
                allWords.add(word);
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

class DataHandler{
    //Gives user score, record word, ?other features
    private String username;
    private ArrayList<Word> lastSaveWords;
    private GameHandler gHandler;
    public DataHandler(GameHandler gHandler){
        //Get gHandler username, dump correct answers to file
        this.username = gHandler.getUsername();
        this.gHandler = gHandler;
    }
    public void saveData(){
        ArrayList<Word> correctWords = getCorrectWords();
        Integer totalScore = 0;
        for (Word word : correctWords) {
            totalScore+=word.getScore();
        }
        GameData gData = new GameData(username, totalScore );
        
    }
    public ArrayList<Word> getCorrectWords(){ return new SortedWords( gHandler.getWords() ).getCorrect(); }
    //Local classes
    private class SortedWords{
        private ArrayList<Word> correct=new ArrayList<>(), wrong=new ArrayList<>();
        public ArrayList<Word> getCorrect(){return this.correct;} public ArrayList<Word> getWrong(){return this.wrong;}
        
        public SortedWords(ArrayList<Word> words){
                for (Word w : words) {
                    String wText=w.getWord();String wRev=w.getReverse();Integer wScore=w.getScore();Boolean wCorrect=w.isCorrect(); //Destructuring
                    if(wCorrect){
                        correct.add(w);
                    }else {
                        wrong.add(w);
                    }
                }
            }

            @Override
            public String toString() {
                String ret="Correct:\n";
                for(Word word : correct)
                    ret+=word.toString()+"\n";
                ret+="Wrong:\n";
                for(Word word : wrong)
                    ret+=word.toString()+"\n";
                return ret;
        }
    }//SortedWords END

    private class GameData implements Serializable{
        //Sent straight to FileHandler/DB
        private String username;
        private Integer score;
        public GameData(String username, Integer score){
            this.username=username; 
            this.score=score;
        }
        @Override
        public String toString(){ return this.username+""+this.score; }
        /* TODO: Finish DB Functions
        public Boolean sendToDB(Connection conn, String tableName, GameData curUser, ArrayList<GameData> boardUsers ){
        //Call to Merge Sort Ascending function
        //Foreach Merge Sort Ascending upload to db
            try {
                Statement stmt = conn.createStatement();
                String query = String.format( "INSERT INTO %s (username, score) values(%s,%s)", tableName , gameData.username , gameData.score );
                ResultSet rs = stmt.executeQuery();
            }
            catch (SQLException e ) {
                return false;
            }
        }
        //DB FUNCTIONALITY (SORT): Load current leaders, form array, sort by descending, upload to DB
        //DB FEATURES: Prevent username SQL injection
        public Boolean
         */
    }
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
class ProjectStrings{
    public static final String startMessage = null;
    public static final String invalidWordMessage = "You already used this word";
}
