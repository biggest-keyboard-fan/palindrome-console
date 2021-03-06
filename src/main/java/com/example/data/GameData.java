package com.example.data;

import java.io.Serializable;

public class GameData implements Serializable {
    //Sent straight to palindrome.handlers.FileHandler/DB
    private String username;
    private Integer score;
    public Integer getScore(){return this.score;}
    public void setScore(Integer score){this.score=score;}
    public  String getUsername(){return this.username;}
    public GameData(String username, Integer score){
        this.username=username;
        this.score=score;
    }
    public GameData(GameData gData){
        this.username=gData.getUsername();
        this.score=gData.getScore();
    }
    @Override
    public String toString(){ return this.username+" : "+this.score; }

}
