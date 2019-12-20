package palindrome.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import palindrome.data.IOResponse;
import palindrome.handlers.GameHandler;

import java.io.IOException;

public class ResponseTest {
    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameHandler gHandler = new GameHandler("testUser");

        Assert.assertSame( IOResponse.responseType.correct , gHandler.processLine("топот").getResponse() );
        Assert.assertSame( IOResponse.responseType.notPalindrome , gHandler.processLine("12345").getResponse() );
        Assert.assertSame( IOResponse.responseType.used , gHandler.processLine("топот").getResponse() );

        System.out.println("Test passed successfully");
    }
}
