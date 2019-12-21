package palindrome;

import org.junit.jupiter.api.Test;
import palindrome.data.*;
import palindrome.handlers.GameHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
/*Description
* This test will simulate multiple game sessions.
* In order to run this test, you have to delete the file stated in ProjectStrings.filename.
* */
/*TODO:
*  - Create separate test save file instead of overwriting main
*  - Override equals() for IOData, GameData, Word classes
* */
public class ScoreboardTest {
    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File boardFile = new File(System.getProperty("user.dir")+"/"+ProjectStrings.filename);
        boolean exists = boardFile.exists();
        if(exists) try{  boardFile.delete(); } catch(Exception e){ e.printStackTrace(); }

        System.out.println("Board file exists: "+exists);

        GameHandler gHandler = new GameHandler("testUser");

        assertSame( IOResponse.responseType.correct , gHandler.processLine("топот").getResponse() );
        assertSame( IOResponse.responseType.used , gHandler.processLine("топот").getResponse() );
        assertSame( IOResponse.responseType.notPalindrome , gHandler.processLine("12345").getResponse() );

        GameHandler gHandler2 = new GameHandler("testUser2");

        IOResponse response1 = gHandler2.processLine("racecar");

        ArrayList<GameData> readDataExpected = new ArrayList<>();
            readDataExpected.add(new GameData(gHandler.getUsername(),5) );
        ArrayList<GameData> writeDataExpected = new ArrayList<>();
            writeDataExpected.add( new GameData( gHandler.getUsername(), 5) );
            writeDataExpected.add( new GameData( gHandler2.getUsername(), 7 ) );

        IOData expected1 = new IOData(
                new Word("racecar","racecar",7),
                new GameData(gHandler2.getUsername(),7),
                readDataExpected,
                writeDataExpected);

        assertSame( IOResponse.responseType.correct , response1.getResponse() );
        assertEquals( expected1.toString() , response1.getIOData().toString() );
        assertSame( IOResponse.responseType.used , gHandler2.processLine("racecar").getResponse() );
        assertSame( IOResponse.responseType.notPalindrome , gHandler2.processLine("12345").getResponse() );

        System.out.println("Test passed successfully");
    }
}
