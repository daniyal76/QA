package com.vaghar.qa.service;

import java.util.Set;

public interface IQuestionService {
    int save(String questionAndAnswer);

    Set<String> getAllAnswerByQuestion(String questionTitle);
}
