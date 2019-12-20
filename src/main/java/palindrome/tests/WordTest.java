package palindrome.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import palindrome.data.GameData;
import palindrome.data.Word;
import palindrome.handlers.GameHandler;

import java.io.IOException;

public class WordTest {
    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameHandler gHandler = new GameHandler("testUser");

        Assert.assertEquals( new Word("топот","топот",5).toString() , gHandler.processLine("топот").getIOData().getInputWord().toString() );
        Assert.assertEquals( new Word("madam","madam",5).toString() , gHandler.processLine("madam").getIOData().getInputWord().toString() );

        System.out.println("Test passed successfully");
    }
}
