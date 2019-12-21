package palindrome;

import org.junit.jupiter.api.Test;
import palindrome.data.Word;
import palindrome.handlers.GameHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordTest {
    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameHandler gHandler = new GameHandler("testUser");

        assertEquals( new Word("топот","топот",5).toString() , gHandler.processLine("топот").getIOData().getInputWord().toString() );
        assertEquals( new Word("madam","madam",5).toString() , gHandler.processLine("madam").getIOData().getInputWord().toString() );

        System.out.println("Test passed successfully");
    }
}
