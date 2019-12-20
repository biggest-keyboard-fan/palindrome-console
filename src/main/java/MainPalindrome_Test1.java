import org.junit.Assert;
import org.junit.jupiter.api.Test;
import palindrome.data.IOData;
import palindrome.data.IOResponse;
import palindrome.data.Word;
import palindrome.handlers.GameHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainPalindrome_Test1 {
    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameHandler gHandler = new GameHandler("testUser");

        Assert.assertSame( IOResponse.responseType.correct , gHandler.processLine("топот").getResponse() );
        Assert.assertSame( IOResponse.responseType.notPalindrome , gHandler.processLine("12345").getResponse() );
        Assert.assertSame( IOResponse.responseType.used , gHandler.processLine("топот").getResponse() );
    }
}
