package com.example.javaquiz;

public class Quiz {

    /**
     * 問題文
     */
    private String question;

    /**
     * 正解文
     */
    private boolean answer;


    public Quiz(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }


    public String getQuestion() {
        return question;
    }

    public boolean isAnswer() {
        return answer;
    }
}
