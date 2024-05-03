package com.vaghar.qa.service.impl;

import com.vaghar.qa.QuestionAndAnswerApplication;
import com.vaghar.qa.dao.QuestionRepository;
import com.vaghar.qa.model.entity.AnswerEntity;
import com.vaghar.qa.model.entity.QuestionEntity;
import com.vaghar.qa.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String RESET = "\033[0m";
    private static final Logger log = LoggerFactory.getLogger(QuestionAndAnswerApplication.class);
    private final QuestionRepository repository;

    @Override
    @Transactional
    public int save(String questionAndAnswer) {
        String[] split = questionAndAnswer.split("\\?");
        if (split.length != 2) {
            System.out.println(ANSI_RED + "Bad Signature! Please Try Again" + RESET);
            return 1111;
        }
        String questionTitle = split[0];
        if (questionTitle.length() > 255) {
            System.out.println(ANSI_RED + "Question title must not exceed 255 characters" + RESET);
            return 2222;
        }
        String answers = split[1];
        QuestionEntity question = new QuestionEntity();
        question.setTitle(questionTitle);
        int errorCode = manageAnswers(question, answers);
        repository.save(question);
        return errorCode;
    }

    private int manageAnswers(QuestionEntity question, String answers) {


        Pattern pat = Pattern.compile("\"(.*?\")");
        Matcher mat = pat.matcher(answers);
        Set<AnswerEntity> answerSet = new HashSet<>();
        while (mat.find()) {
            String text = mat.group(0).replace("\"", "");
            if (text.length() > 255) {
                System.out.println(ANSI_RED + "Answer text must not exceed 255 characters" + RESET);
                return 3333;
            }

            if (!text.isBlank()) {
                AnswerEntity answer = new AnswerEntity();
                answer.setText(text);
                answer.setQuestion(question);
                answerSet.add(answer);
            }

        }
        if (answerSet.isEmpty()) {
            System.out.println(ANSI_RED + "Question should has at least 1 answer" + RESET);
            return 4444;
        }
        question.getAnswers().addAll(answerSet);
        return 200;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> getAllAnswerByQuestion(String questionTitle) {
        QuestionEntity question = repository.findByTitle(questionTitle);
        if (Objects.isNull(question)) {
            return Set.of("the answer to life, universe and everything is 42");
        } else {
            return question.getAnswers().stream().map(AnswerEntity::getText).collect(Collectors.toSet());
        }
    }
}

