package palindrome.handlers;

import palindrome.data.*;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    String fileName;
    ObjectInputStream in=null;
    public void closeInputStream() throws IOException { if(in!=null)in.close(); }
    public FileHandler(String fileName) throws IOException {
        this.fileName=fileName;
    }
    public void saveToFile(ArrayList<GameData> obj) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName) );
        out.writeObject(obj);
    }
    public ArrayList<GameData> readFromFile() throws IOException, ClassNotFoundException {
        in=null;
        try{
            in = new ObjectInputStream(new FileInputStream(fileName) );
        }
        catch(FileNotFoundException e){
            ArrayList<GameData> empty = new ArrayList<GameData>();
            saveToFile( empty );
            return empty;
        }

        ArrayList<GameData> gameData = (ArrayList<GameData>) in.readObject();
        return gameData;
    }
}
