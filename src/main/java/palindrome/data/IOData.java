package palindrome.data;

import java.util.ArrayList;

public class IOData{
    public IOData(Word inputWord, GameData curData, ArrayList<GameData> readData, ArrayList<GameData> saveData){
        this.inputWord = inputWord;
        this.curData = curData;
        this.readData = readData;
        this.saveData = saveData;
    }
    private Word inputWord; public Word getInputWord() { return inputWord; }
    private GameData curData; public GameData getCurData() { return curData; }
    private ArrayList<GameData> readData; public ArrayList<GameData> getReadData() { return readData; }
    private ArrayList<GameData> saveData; public ArrayList<GameData> getSaveData() { return saveData; }

    @Override
    public String toString() {
        return String.format("Input Word:\n%s\nCurrent Data:\n%s\nRead Data:\n%s\nSave Data:\n%s",inputWord,curData,readData,saveData);
    }
}
