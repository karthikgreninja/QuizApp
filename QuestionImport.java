package com.example.karthikeyan.karthikphplogin;

public class QuestionImport {
    private String QId;
    private String QStatement;
    private String Option1;
    private String Option2;
    private String Option3;
    private String Option4;
    private String Answer;
    private String TId;

    public QuestionImport(String QId, String QStatement, String Option1, String Option2, String Option3, String Option4, String Answer, String TId) {
        this.QId = QId;
        this.QStatement = QStatement;
        this.Option1 = Option1;
        this.Option2 = Option2;
        this.Option3 = Option3;
        this.Option4 = Option4;
        this.Answer = Answer;
        this.TId = TId;
    }

    public String getQId() {
        return QId;
    }

    public void setQId(String QId) {
        this.QId = QId;
    }

    public String getQStatement() {
        return QStatement;
    }

    public void setQStatement(String QStatement) {
        this.QStatement = QStatement;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public String getOption4() {
        return Option4;
    }

    public void setOption4(String option4) {
        Option4 = option4;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getTId() {
        return TId;
    }

    public void setTId(String TId) {
        this.TId = TId;
    }
}
