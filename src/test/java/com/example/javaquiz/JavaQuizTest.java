package com.example.javaquiz;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JavaQuizTest {

    @Test
    public void toStringWhenMaru() {

        Quiz quiz = new Quiz("問題文", true);
        assertThat(quiz.toString(), is("問題文 ◯"));
    }

    @Test
    public void toStringWhenBatsu() {

        Quiz quiz = new Quiz("問題文", false);
        assertThat(quiz.toString(), is("問題文 ×"));

    }
}
