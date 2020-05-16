package com.example.main;

import com.example.handlers.GameHandler;

import java.lang.*;
import java.io.*;
/*
TODO:
 - Format out capital letters, spaces
 - Ban String.length()<2
 - Look for other ways of cheating
 - FileHandler debugging
 - Reformat classes
 - Override equals() for IOData, GameData, Word classes
*/

public class MainPalindrome{
  public static Boolean writeFilesToConsole = false;
  public static void main(String[] args) throws IOException, ClassNotFoundException {

    GameHandler gHandler = new GameHandler(); // - game mode
    gHandler.startReading(); // - start reading

  }
}
