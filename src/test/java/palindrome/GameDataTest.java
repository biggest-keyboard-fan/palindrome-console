package palindrome;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import palindrome.data.GameData;
import palindrome.handlers.GameHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GameDataTest {
    @Test
    @Tag("BasicTest")
    void runTest() throws IOException, ClassNotFoundException {
        GameHandler gHandler = new GameHandler("testUser");

        assertEquals( new GameData(gHandler.getUsername(),5).toString() , gHandler.processLine("топот").getIOData().getCurData().toString() );
        assertNull(gHandler.processLine("топот").getIOData());
        assertNull(gHandler.processLine("12345").getIOData());
        assertEquals( new GameData(gHandler.getUsername(),10).toString() , gHandler.processLine("madam").getIOData().getCurData().toString() );

        System.out.println("Test passed successfully");
    }
}
