package palindrome.data;

public class Word implements UsedWord {
    public String word, reverse;
    Integer score;

    public Word(String word, String reverse, Integer score){
        this.word=word;
        this.reverse=reverse;
        this.score=score;
  }
  @Override
  public String toString(){ return String.format("Word: %s Reverse: %s Score: %d",word,reverse,score); }

    //Getters

    public String getWord(){return this.word;}
    public String getReverse(){return this.reverse;}
    public Integer getScore(){return this.score;}
    public Boolean isCorrect(){return getScore()>0;}
}
