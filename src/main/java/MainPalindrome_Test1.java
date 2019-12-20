import org.junit.Assert;
import org.junit.jupiter.api.Test;
import palindrome.data.IOResponse;
import palindrome.handlers.GameHandler;

import java.io.IOException;
import java.util.ArrayList;

public class MainPalindrome_Test1 {
    @Test
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //new GameHandler(user) - testing mode
        //new GameHandler() - game mode
        //gHandler.startReading() - start reading;

        GameHandler gHandler = new GameHandler("testUser");

        ArrayList<IOResponse> tResponses=new ArrayList<>();

        tResponses.add( gHandler.processLine("топот") );
        tResponses.add( gHandler.processLine("потоп") );
        tResponses.add( gHandler.processLine("madam") );
        tResponses.add( gHandler.processLine("radar") );
        tResponses.add( gHandler.processLine("топот") );
        tResponses.add( gHandler.processLine("madam") );

        for (IOResponse tResponse: tResponses )
            System.out.println(tResponse);
        //Assert.assertArrayEquals();
    }
}
