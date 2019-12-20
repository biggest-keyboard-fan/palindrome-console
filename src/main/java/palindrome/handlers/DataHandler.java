package palindrome.handlers;

import palindrome.data.*;
import palindrome.handlers.*;

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
