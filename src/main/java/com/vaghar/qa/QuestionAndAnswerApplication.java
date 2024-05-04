package com.vaghar.qa;

import com.vaghar.qa.service.IQuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

@SpringBootApplication
public class QuestionAndAnswerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionAndAnswerApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner demo(IQuestionService iService) {
        return args -> {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            while (true) {
                System.out.println("\n\n\nWELCOME TO Q&A\n");
                System.out.println("Choose desired action:");
                System.out.println("1.Enter question and answers into system.");
                System.out.println("2.Ask from system.");
                System.out.println("3.Exit.");

                int action = Integer.parseInt(reader.readLine());

                if (action == 1) {
                    System.out.println("Now enter your question and Answers with this signature : (Question?\"Answers1\"\"Answers2\"...\"AnswersN\")");
                    String questionAndAnswers = reader.readLine();
                    int errorCode = iService.save(questionAndAnswers);
                    if (errorCode == 200) {
                        System.out.println("Your Question And Answers Successfully Stored");
                    }

                } else if (action == 2) {
                    System.out.println("Ask Your Question:");
                    String questionTitle = reader.readLine();
                    Set<String> allAnswerByQuestion = iService.getAllAnswerByQuestion(questionTitle);
                    allAnswerByQuestion.forEach(System.out::println);
                } else if (action == 3) {
                    System.exit(0);
                }
            }
        };
    }

}
