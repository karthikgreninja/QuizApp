package com.example.karthikeyan.karthikphplogin;

public class studentQuizResult {
    private String Test;
    private float Score;

    public studentQuizResult(String Test, float Score){
        this.Test=Test;
        this.Score=Score;
    }

    public String getTest(){
        return Test;
    }

    public float getScore(){
        return Score;
    }

    public void setTest(String RollNo){
        this.Test=Test;
    }

    public void setScore(float Score){
        this.Score=Score;
    }
}

