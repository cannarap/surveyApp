package com.test.survey.surveyApp.dao;

import com.test.survey.surveyApp.jpa.model.Answer;
import com.test.survey.surveyApp.jpa.model.Question;

import java.util.List;


public interface SurveyDao {

    public void createSurvey(String name, List<Question> questions)  throws Exception;

    public void createOrUpdateQuestion(String surveyName, Question question) throws Exception;

    public String deleteQuestion(Long questionId) throws Exception;

    public void createOrUpdateAnswer(Long questionId, Answer answer) throws Exception;

    public String deleteAnswer(Long answerId ) throws Exception;

    public List<Question> getAllQuestions() throws Exception;

    public boolean isQuestionExist(Long questionId) throws  Exception;

    public boolean isAnswerExist(Long answerId) throws Exception;

    public Question getQuestionById(Long questionId) throws Exception;


}
