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


    @Override
    public String toString() {
        String marubatsu = answer ? "◯" : "×";

/**
* 上記の [String marubatsu = answer ? "◯" : "×";]と同じ
*/
//      String marubatsu;
//      if (answer) {
//          marubatsu = "◯";
//      }
//      else {
//          marubatsu = "×";
//      }
        return question + " " + marubatsu;
    }
}
