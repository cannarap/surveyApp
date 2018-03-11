package com.test.survey.surveyApp.service;


import com.test.survey.surveyApp.jpa.model.Answer;
import com.test.survey.surveyApp.jpa.model.Question;
import com.test.survey.surveyApp.jpa.model.Survey;

import java.util.List;

public interface SurveyService {

    public List<Question> getQuestions() throws Exception;

    public Question getQuestionById(Long id) throws Exception;

    public void createSurvey(String name, List<Question> questions) throws Exception;

    public String createOrUpdateQuestion(String surveyName,Question question) throws Exception;

    public boolean isQuestionExist(Question question) throws  Exception;

    public String deleteQuestion(Long questionId) throws Exception;

    public String createOrUpdateAnswer(Long questionId, Answer answer) throws Exception;

    public boolean isAnswerExist(Answer answer) throws Exception;

    public String deleteAnswer(Long answerId ) throws Exception;


}
