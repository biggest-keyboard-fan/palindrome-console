package palindrome.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import palindrome.data.GameData;
import palindrome.handlers.GameHandler;

import java.io.IOException;

public class GameDataTest {
    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameHandler gHandler = new GameHandler("testUser");

        Assert.assertEquals( new GameData(gHandler.getUsername(),5).toString() , gHandler.processLine("топот").getIOData().getCurData().toString() );
        Assert.assertNull(gHandler.processLine("топот").getIOData());
        Assert.assertNull(gHandler.processLine("12345").getIOData());
        Assert.assertEquals( new GameData(gHandler.getUsername(),10).toString() , gHandler.processLine("madam").getIOData().getCurData().toString() );

        System.out.println("Test passed successfully");
    }
}
