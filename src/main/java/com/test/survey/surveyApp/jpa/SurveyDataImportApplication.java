package com.test.survey.surveyApp.jpa;

import com.test.survey.surveyApp.jpa.model.*;
import com.test.survey.surveyApp.jpa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class SurveyDataImportApplication implements CommandLineRunner {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionOptionRepository questionOptionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SurveyDataImportApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        Survey testSurvey = new Survey("testSurvey");
        surveyRepository.save(testSurvey);


        Question testQuestion = new Question("What is your most favourite programming language");
        testQuestion.setSurvey(testSurvey);
        questionRepository.save(testQuestion);

        QuestionOption testQuestionOption1 = new QuestionOption("java");
        testQuestionOption1.setQuestion(testQuestion);
        questionOptionRepository.save(testQuestionOption1);

        QuestionOption testQuestionOption2 = new QuestionOption("javascript");
        testQuestionOption2.setQuestion(testQuestion);
        questionOptionRepository.save(testQuestionOption2);

        QuestionOption testQuestionOption3 = new QuestionOption("php");
        testQuestionOption3.setQuestion(testQuestion);
        questionOptionRepository.save(testQuestionOption3);

        QuestionOption testQuestionOption4 = new QuestionOption("phython");
        testQuestionOption4.setQuestion(testQuestion);
        questionOptionRepository.save(testQuestionOption4);

        User testUser1 = new User("testUser1");
        userRepository.save(testUser1);

        Answer answer1 = new Answer();
        answer1.setUser(testUser1);
        answer1.setQuestion(testQuestion);
        answer1.setSelectedOption(testQuestionOption3);
        answerRepository.save(answer1);

        User testUser2 = new User("testUser2");
        userRepository.save(testUser2);

        Answer answer2 = new Answer();
        answer2.setUser(testUser2);
        answer2.setQuestion(testQuestion);
        answer2.setSelectedOption(testQuestionOption1);
        answerRepository.save(answer2);


        User testUser3 = new User("testUser3");
        userRepository.save(testUser3);

        Answer answer3 = new Answer();
        answer3.setUser(testUser3);
        answer3.setQuestion(testQuestion);
        answer3.setSelectedOption(testQuestionOption1);
        answerRepository.save(answer3);




    }
}



