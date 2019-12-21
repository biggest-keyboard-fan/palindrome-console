package palindrome.handlers;

import palindrome.data.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataHandler implements Serializable {
    //Gives user score, record word, ?other features
    private String username;
    private GameHandler gHandler;
    private FileHandler fHandler;
    public FileHandler getFileHandler(){return  this.fHandler; }
    public DataHandler(GameHandler gHandler) throws IOException {
        //Get gHandler username, dump correct answers to file
        this.fHandler = new FileHandler(ProjectStrings.filename);
        this.username = gHandler.getUsername();
        this.gHandler = gHandler;
    }
    public IOData saveData() throws IOException, ClassNotFoundException {
        ArrayList<Word> words = gHandler.getWords();
        Word lastWord = words.get( words.size()-1 );
        Integer totalScore = 0;
        for (Word word : words) {
            totalScore += word.getScore();
        }
        GameData gData = new GameData(this.username, totalScore);

        ArrayList<GameData> gDataRet = fHandler.readFromFile();
        ArrayList<GameData> reinstDataRet = getReInstantiatedGameData(gDataRet); //Used for debugging. Contains separate instances
        ArrayList<GameData> gDataSort = sortAscending( gDataRet, gData );

        fHandler.saveToFile(gDataSort);

        return new IOData(lastWord,gData,reinstDataRet,gDataSort);
    }
    private ArrayList<GameData> getReInstantiatedGameData(ArrayList<GameData> gData){
        ArrayList<GameData> retData=new ArrayList<>();
        for (GameData data: gData)
            retData.add( new GameData(data) );

        return retData;
    }
    private ArrayList<GameData> sortAscending(ArrayList<GameData> lData, GameData data){
        boolean newUser = true;
        ArrayList<GameData> retData = new ArrayList<>(lData);

        for (GameData feachData: retData) {
            if( feachData.getUsername().equals( data.getUsername() ) ){
                newUser=false;

                if( data.getScore() > feachData.getScore() )
                    feachData.setScore( data.getScore() );
            }
        }
        if(newUser)
            retData.add(data);

        Collections.sort(retData, new Comparator<GameData>() {
            public int compare(GameData o1, GameData o2) {
                return o1.getScore().compareTo( o2.getScore() );
            }
        });

        Integer lSize = retData.size();
        if(lSize>5)
            retData.subList(0,lSize-5).clear();

        return retData;
    }
}
