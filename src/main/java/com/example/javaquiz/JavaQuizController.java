package com.example.javaquiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("page")
public class JavaQuizController {

    // Quizクラス
    private List<Quiz> quizzes = new ArrayList<>();

    private JavaQuizFileDao javaQuizFileDao = new JavaQuizFileDao();


    @GetMapping("/quiz")
    public String quiz(Model model) {
        int index = new Random().nextInt(quizzes.size());
        model.addAttribute("quiz", quizzes.get(index));

        return "quiz";
    }


    // showメソッド
    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("quizzes", quizzes);

        return "list";
    }


    // createメソッド
    @PostMapping("/create")
    public String create(@RequestParam String question, @RequestParam boolean answer) {
        Quiz quiz = new Quiz(question, answer);
        quizzes.add(quiz);

        return "redirect:/page/show";
    }


    // checkメソッド
    @GetMapping("/check")
    public String check(Model model, @RequestParam String question, @RequestParam boolean answer) {

        // 解答が正しいかどうかをチェックして、結果を返す
        // 指定されたquestionを登録済みのクイズから検索
        for (Quiz quiz: quizzes) {

            // クイズが見つかった場合...
            if (quiz.getQuestion().equals(question)) {
                model.addAttribute("quiz", quiz);

                // 登録されているanswerと解答として送信されたanswerが一致
                if (quiz.isAnswer() == answer) {
                    model.addAttribute("result","正解!");

                }
                // 登録されているanswerと解答として送信されたanswerが不一致
                else {
                    model.addAttribute("result","不正解!");
                }
            }
        }

        return "answer";
    }


    // saveメソッド
    @PostMapping("/save")
    public String save(RedirectAttributes attributes) {
        try {
            javaQuizFileDao.write(quizzes);
            attributes.addFlashAttribute("successMessage","ファイルに保存しました");
        }
        catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage","ファイルの保存に失敗しました");
        }

        return "redirect:/page/show";
    }


    // loadメソッド
    @GetMapping("/load")
    public String load(RedirectAttributes attributes) {
        try {
            quizzes = javaQuizFileDao.read();
            attributes.addFlashAttribute("successMessage","ファイルを読み込みました");
        }
        catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage","ファイルの読み込みに失敗しました");
        }

        return "redirect:/page/show";
    }
}
