package com.example.karthikeyan.karthikphplogin;

public class QuizWrapper {

    private int id;

    private String question;

    private String[] answers;

    private String correctAnswer;

    public QuizWrapper(int id, String question, String[] answers, String correctAnswer) {

        this.id = id;

        this.question = question;

        this.answers = answers;

        this.correctAnswer = correctAnswer;

    }

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public String getQuestion() {

        return question;

    }

    public void setQuestion(String question) {

        this.question = question;

    }

    public String[] getAnswers() {

        return answers;

    }

    public void setAnswers(String[] answers) {

        this.answers = answers;

    }

    public String getCorrectAnswer() {

        return correctAnswer;

    }

    public void setCorrectAnswer(String correctAnswer) {

        this.correctAnswer = correctAnswer;

    }

}