package com.example.javaquiz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JavaQuizController {

    // Quizクラス
    private List<Quiz> quizzes = new ArrayList<>();


    @GetMapping("/show")
    public List<Quiz> show() {
        return quizzes;
    }


    @PostMapping("/create")
    public void create(@RequestParam String question, @RequestParam boolean answer) {
        Quiz quiz = new Quiz(question, answer);
        quizzes.add(quiz);
    }

    // checkメソッド
    @GetMapping("/check")
    public String check(@RequestParam String question, @RequestParam boolean answer) {

        // 解答が正しいかどうかをチェックして、結果を返す
        // 指定されたquestionを登録済みのクイズから検索
        for (Quiz quiz: quizzes) {

            // クイズが見つかった場合...
            if (quiz.getQuestion().equals(question)) {

                // 登録されているanswerと解答として送信されたanswerが一致
                if (quiz.isAnswer() == answer) {
                    return "正解！";
                }
                // 登録されているanswerと解答として送信されたanswerが不一致
                else {
                    return "不正解！";
                }
            }
        }

        // クイズが見つからなかった場合...
        return "見つからなかった!";
    }
}
