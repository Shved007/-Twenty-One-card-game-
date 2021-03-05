package com.game21;

public class Players {
   private String name;
   private int balance;
   private int score;
    public Players(){}
    public Players(String name, int balance, int score){
        this.name = name;
        this.balance = balance;
        this.score = score;

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getBalance(){
        return balance;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }

    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score =score;

    }

}
