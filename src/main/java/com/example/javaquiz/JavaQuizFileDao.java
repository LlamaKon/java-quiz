package com.example.javaquiz;

import org.apache.tomcat.jni.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Dao → Data access object
public class JavaQuizFileDao {

    private static final String FILE_PATH = "quizzes.txt";

    // ファイルに書き込む処理
    public void write(List<Quiz> quizzes) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Quiz quiz: quizzes) {
            lines.add(quiz.toString());
        }

        Path path = Paths.get(FILE_PATH);

        // 第１引数 → 書き込み先のファイルパス
        // 第２引数 → 書き込むデータ
        Files.write(path, lines);
    }


    // ファイルから読み込む処理
    public List<Quiz> read() throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = Files.readAllLines(path);

        List<Quiz> quizzes = new ArrayList<>();
        for (String line : lines) {
            quizzes.add(Quiz.fromString(line));
        }

        return quizzes;
    }
}
