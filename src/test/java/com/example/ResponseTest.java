package com.example;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.example.data.IOResponse;
import com.example.handlers.GameHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ResponseTest {
    @Test
    @Tag("BasicTest")
    void runTest() throws IOException, ClassNotFoundException {
        GameHandler gHandler = new GameHandler("testUser");

        assertSame( IOResponse.responseType.correct , gHandler.processLine("топот").getResponse() );
        assertSame( IOResponse.responseType.notPalindrome , gHandler.processLine("12345").getResponse() );
        assertSame( IOResponse.responseType.used , gHandler.processLine("топот").getResponse() );

        System.out.println("Test passed successfully");
    }
}
