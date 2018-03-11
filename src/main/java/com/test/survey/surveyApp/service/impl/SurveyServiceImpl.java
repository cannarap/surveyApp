package com.test.survey.surveyApp.service.impl;

import com.test.survey.surveyApp.dao.SurveyDao;
import com.test.survey.surveyApp.jpa.model.Answer;
import com.test.survey.surveyApp.jpa.model.Question;
import com.test.survey.surveyApp.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class SurveyServiceImpl implements SurveyService{

    @Autowired
    SurveyDao   surveyDao;

    @Override
    public List<Question> getQuestions() throws Exception {
        return surveyDao.getAllQuestions();
    }

    @Override
    public void createSurvey(String name, List<Question> questions) throws Exception {
        surveyDao.createSurvey(name,questions);
    }

    @Override
    public String createOrUpdateQuestion(String surveyName, Question question) throws Exception {

        if(StringUtils.isEmpty(surveyName)) {
            return " ERROR: No Survey Name specified in the request Parameter";
        } else {
            surveyDao.createOrUpdateQuestion(surveyName, question);
            return " Updated Question in our databases";
        }

    }

    @Override
    public boolean isQuestionExist(Question question) throws Exception {

        return surveyDao.isQuestionExist(question.getId());

    }

    @Override
    public String deleteQuestion(Long questionId) throws Exception {
        return surveyDao.deleteQuestion(questionId);
    }

    @Override
    public String createOrUpdateAnswer(Long questionId, Answer answer) throws Exception {

            if(questionId == null) {
                return " ERROR: No QuestionId specified in the request JSON";
            } else if( answer.getUser() == null  || StringUtils.isEmpty(answer.getUser().getName())) {
                return " ERROR: No User specified in the request JSON";
            }else {
                surveyDao.createOrUpdateAnswer(questionId, answer);
                return " Updated Answer in our databases";
            }

    }

    @Override
    public Question getQuestionById(Long id) throws Exception {
        if( id != null ){
            return surveyDao.getQuestionById(id);
        }

        return null;
    }

    @Override
    public boolean isAnswerExist(Answer answer) throws Exception {
        return surveyDao.isAnswerExist(answer.getId());
    }

    @Override
    public String deleteAnswer(Long answerId) throws Exception {
        return surveyDao.deleteAnswer(answerId);
    }
}
