package com.example.data;

public interface UsedWord{
    String word=null, reverse=null;
    Integer score = 0;

    String getWord();
    String getReverse();
    Integer getScore();
    Boolean isCorrect();
}
