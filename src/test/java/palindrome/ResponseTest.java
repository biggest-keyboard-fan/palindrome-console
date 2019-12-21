package palindrome;

import org.junit.jupiter.api.Test;
import palindrome.data.IOResponse;
import palindrome.handlers.GameHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ResponseTest {
    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameHandler gHandler = new GameHandler("testUser");

        assertSame( IOResponse.responseType.correct , gHandler.processLine("топот").getResponse() );
        assertSame( IOResponse.responseType.notPalindrome , gHandler.processLine("12345").getResponse() );
        assertSame( IOResponse.responseType.used , gHandler.processLine("топот").getResponse() );

        System.out.println("Test passed successfully");
    }
}
