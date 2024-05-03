package com.vaghar.qa;

import com.vaghar.qa.service.IQuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class QuestionAndAnswerApplicationTests {

    @Autowired
    private IQuestionService iService;

    @Test
    public void successfulQAInsertion() {
        String questionAndAnswers = "What's your favorite food ? \"ghorme sabze\" \"zereshk polo ba morgh\"";
        int errorCode = iService.save(questionAndAnswers);
        Assertions.assertEquals(200, errorCode);
    }

    @Test
    public void qaSignature() {
        String questionAndAnswers = "What's your favorite food\"spaghetti\" \"sushi\"";
        int errorCode = iService.save(questionAndAnswers);
        Assertions.assertEquals(1111, errorCode);
    }

    @Test
    public void questionLongTitle() {
        String questionAndAnswers = "dani".repeat(100) +
                "?\"spaghetti\" \"sushi\"";
        int errorCode = iService.save(questionAndAnswers);
        Assertions.assertEquals(2222, errorCode);
    }

    @Test
    public void emptyAnswer() {
        String questionAndAnswers = "What's your favorite food?\"\"";
        int errorCode = iService.save(questionAndAnswers);
        Assertions.assertEquals(4444, errorCode);
    }

    @Test
    public void answerLongText() {
        String questionAndAnswers = "What's your favorite food?\"" +
                "spaghetti".repeat(100) + "\"\"sushi\"";
        int errorCode = iService.save(questionAndAnswers);
        Assertions.assertEquals(3333, errorCode);
    }

}
