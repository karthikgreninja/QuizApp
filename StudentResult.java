package com.example.karthikeyan.karthikphplogin;

public class StudentResult {
    private String RollNo;
    private float Score;

    public StudentResult(String RollNo,float Score){
        this.RollNo=RollNo;
        this.Score=Score;
    }

    public String getRoll(){
        return RollNo;
    }

    public float getScore(){
        return Score;
    }

    public void setRollNo(String RollNo){
        this.RollNo=RollNo;
    }

    public void setScore(float Score){
        this.Score=Score;
    }
}
