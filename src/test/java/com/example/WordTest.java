package com.example;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.example.data.Word;
import com.example.handlers.GameHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordTest {
    @Test
    @Tag("BasicTest")
    void runTest() throws IOException, ClassNotFoundException {
        GameHandler gHandler = new GameHandler("testUser");

        assertEquals( new Word("топот","топот",5).toString() , gHandler.processLine("топот").getIOData().getInputWord().toString() );
        assertEquals( new Word("madam","madam",5).toString() , gHandler.processLine("madam").getIOData().getInputWord().toString() );

        System.out.println("Test passed successfully");
    }
}
