package palindrome.handlers;

import palindrome.data.*;
import palindrome.handlers.*;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
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
