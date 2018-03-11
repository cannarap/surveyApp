package com.test.survey.surveyApp.dao.impl;

import com.test.survey.surveyApp.dao.SurveyDao;
import com.test.survey.surveyApp.jpa.model.*;
import com.test.survey.surveyApp.jpa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SurveyDaoImpl implements SurveyDao {

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

    @Override
    public void createSurvey(String name, List<Question> questions) throws Exception {

        Survey survey = new Survey(name);
        surveyRepository.save(survey);

        for(Question question: questions) {
            createOrUpdateQuestion(name, question);
        }

    }

    @Override
    public void createOrUpdateQuestion(String surveyName, Question question) throws  Exception {

        Survey survey = surveyRepository.findByName(surveyName);
        // update the question in the survey,
        // if no survey exists, create one
        if(survey == null ) {

            survey = new Survey(surveyName);
            surveyRepository.save(survey);

        }
        Question q = questionRepository.findQuestionBySurveyAndText(survey, question.getText());

        if(q != null){

            q.setText(question.getText());
            // remove all Options, if exists any
            for(QuestionOption qo: q.getOptions()){
                questionOptionRepository.deleteById(qo.getId());
            }


        } else {

            // creating new Question and Options
            q = new Question(question.getText());
            q.setSurvey(survey);
            questionRepository.save(q);



        }

        ArrayList<QuestionOption> qOptions = new ArrayList<>();

        // Update question Options
        for(QuestionOption qo: question.getOptions()){

            QuestionOption newQOption = new QuestionOption(qo.getValue());
            newQOption.setQuestion(q);
            questionOptionRepository.save(newQOption);

            qOptions.add(newQOption);

        }

        q.setOptions(qOptions);
        questionRepository.save(q);



    }

    @Override
    public String deleteQuestion(Long questionId) throws Exception{

        if( questionRepository.findById(questionId).isPresent()) {
            questionRepository.deleteById(questionId);
            return " Question with Id "+ questionId + " deleted successfully fron our database";
        } else {
            return " Question with Id "+ questionId + " does not exist in our Database";
        }
    }

    @Override
    public void createOrUpdateAnswer(Long questionId, Answer answer) throws Exception {

        Optional<Question> question = questionRepository.findById(questionId);

        question.ifPresent( q -> {

            User user = userRepository.findByName(answer.getUser().getName());

            Answer existingAnswer  = answerRepository.findByQuestionAndUser(q, user);

            if(existingAnswer != null){

                updateAnswerForQuestion(existingAnswer, answer);
            } else {
                createAnswerForQuestion(q, answer);

            }
        });
    }

    @Override
    public String deleteAnswer(Long answerId) throws Exception {

        if( answerRepository.findById(answerId).isPresent()) {
            answerRepository.deleteById(answerId);
            return " Answer with Id "+ answerId + " deleted successfully fron our database";
        } else {
            return " Answer with Id "+ answerId + " does not exist in our Database";
        }

    }

    @Override
    public List<Question> getAllQuestions()  throws Exception{

        return questionRepository.findAll();
    }


    private void updateAnswerForQuestion(Answer existingAnswer, Answer newAnswer) {

        existingAnswer.setSelectedOption(newAnswer.getSelectedOption());
        answerRepository.save(existingAnswer);

    }


    private void createAnswerForQuestion(Question question, Answer answer) {

        Answer newAnswer = new Answer();

        User user = userRepository.findByName(answer.getUser().getName());

        if(user == null) {
            user = new User(answer.getUser().getName());
            userRepository.save(user);

        }
        newAnswer.setUser(answer.getUser());
        newAnswer.setQuestion(question);
        newAnswer.setSelectedOption(answer.getSelectedOption());
        answerRepository.save(newAnswer);

        question.getAnswers().add(newAnswer);
        questionRepository.save(question);


    }

    @Override
    public boolean isQuestionExist(Long questionId) throws Exception {
        Optional<Question> question = questionRepository.findById(questionId);

        return question.isPresent();
    }

    @Override
    public boolean isAnswerExist(Long answerId) throws Exception {

        Optional<Answer> answer = answerRepository.findById(answerId);

        return answer.isPresent();
    }

    @Override
    public Question getQuestionById(Long questionId) throws Exception {

        Optional<Question> question =  questionRepository.findById(questionId);

        if(question.isPresent()){
            return question.get();
        }

        return null;
    }
}
