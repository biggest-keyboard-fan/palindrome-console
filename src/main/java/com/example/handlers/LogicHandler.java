package com.example.handlers;

import com.example.data.Word;

import java.util.ArrayList;

public class LogicHandler{ //Completely static
    public static Boolean isStringValid(ArrayList<Word> words, Word checkWord){ //TODO: Implement check for spaces, length, etc.
        Boolean ret = true;
        String checkString = checkWord.getWord();

        for (Word word : words) {
            if( word.getWord().equals(checkString) ){
                ret = false;
            }
        }
        return ret;
    }
    public static Word processWord(String word){
        String reverse = reverseWord(word);
        Integer score = getWordScore(word, reverse);

        if(score==0){ return null; }

        return new Word(word,reverse,score);
    }
    private static String reverseWord(String word){
        StringBuilder sBuilder = new StringBuilder().append(word);
        return sBuilder.reverse().toString();
    }
    private static Boolean checkPalindrome(String word, String reverse){
        return word.equals(reverse);
    }
    private static Integer getWordScore(String word, String reverse){
        if(!checkPalindrome (word,reverse) ){ return 0; }
        else{ return word.length(); }
    }
}
