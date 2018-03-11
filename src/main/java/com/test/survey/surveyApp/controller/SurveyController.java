package com.test.survey.surveyApp.controller;


import com.test.survey.surveyApp.dto.QuestionAnswerAnalysisDTO;
import com.test.survey.surveyApp.jpa.model.Answer;
import com.test.survey.surveyApp.jpa.model.Question;
import com.test.survey.surveyApp.jpa.model.QuestionOption;
import com.test.survey.surveyApp.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/surveyService")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @RequestMapping(value = "/getAllQuestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<Question>();

        try {
            questions.addAll(surveyService.getQuestions());

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return questions;

    }

    @RequestMapping(value = "/getQuestionWithAnswers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Question getQuestionWithAnswers(@PathVariable("id") int id) {

        if(id == 0) {
            return null;
        }

        try {

            Question question = surveyService.getQuestionById(Long.valueOf(id));
            return question;

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/analyseQuestionWithAllAnswers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private QuestionAnswerAnalysisDTO analyseQuestionWithAllAnswers(@PathVariable("id") int id) {

        if(id == 0) {
            return null;
        }

        try {

            Question question = surveyService.getQuestionById(Long.valueOf(id));

            QuestionAnswerAnalysisDTO analysisDTO = new QuestionAnswerAnalysisDTO();

            analysisDTO.setQuestionText(question.getText());
            analysisDTO.setTotalOptionsAvailable(question.getOptions().size());

            List<Answer> answers = question.getAnswers();

            Map<String, Integer> answerCounts = new HashMap<String, Integer>();
            Map<String, Double> answerDistribution = new HashMap<String, Double>();

            int totalAnswers = 0;

            for(Answer answer: answers) {

                if(answerCounts.containsKey(answer.getSelectedOption().getValue())) {
                    int answerCount = answerCounts.get(answer.getSelectedOption().getValue());
                    answerCount++;
                    answerCounts.put(answer.getSelectedOption().getValue(), Integer.valueOf(answerCount) );
                    totalAnswers++;
                } else {
                    answerCounts.put(answer.getSelectedOption().getValue(), Integer.valueOf(1) );
                    totalAnswers++;
                }

            }

            analysisDTO.setAnswerCounts(answerCounts);


            for(QuestionOption qOption : question.getOptions()) {

                if(answerCounts.containsKey(qOption.getValue()) && totalAnswers > 0){

                    int count = answerCounts.get(qOption.getValue());

                    double percent =  ( count / Double.valueOf(totalAnswers) ) * 100;

                    answerDistribution.put(qOption.getValue(), Double.valueOf(percent));
                } else {
                    answerDistribution.put(qOption.getValue(), Double.valueOf(0.0));
                }

            }

            analysisDTO.setPercentageDistribution(answerDistribution);

            return analysisDTO;


        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addQuestion(@RequestParam(value = "surveyName") String surveyName, @RequestBody Question question) {
        String message = null;

        if (question == null)
            return "Bad Request";

        try {
            if (question.getId() != null && surveyService.isQuestionExist(question)) {

                return "Question with text " + question.getText() + " is already exist.";

            } else {

                surveyService.createOrUpdateQuestion(surveyName, question);
                message = "Question added successfully";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    @RequestMapping(value = "/updateQuestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateQuestion(@RequestParam(value = "surveyName") String surveyName, @RequestBody Question question) {
        String message = null;

        if (question == null)
            return "Bad Request";

        try {

                surveyService.createOrUpdateQuestion(surveyName, question);
                message = "Question updated successfully";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    @RequestMapping(value = "/deleteQuestion/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteQuestion(@PathVariable("id") int id) throws Exception {

        return surveyService.deleteQuestion(Long.valueOf(id));



    }


    @RequestMapping(value = "/addAnswer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addAnswer(@RequestParam(value = "questionId") int questionId, @RequestBody Answer answer) {
        String message = null;

        if (answer == null || questionId == 0)
            return "Bad Request";

        try {
            if (answer.getId() != null &&surveyService.isAnswerExist(answer)) {

                return "Answer with Id " + answer.getId() + " is already exist.";

            } else {

                surveyService.createOrUpdateAnswer(Long.valueOf(questionId), answer);
                message = "Employee added successfully";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    @RequestMapping(value = "/updateAnswer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateAnswer(@RequestParam(value = "questionId") Long questionId, @RequestBody Answer answer) {
        String message = null;

        if (answer == null || questionId == 0)
            return "Bad Request";

        try {

            surveyService.createOrUpdateAnswer(questionId, answer);
            message = "Answer updated successfully";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    @RequestMapping(value = "/deleteAnswer/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteAnswer(@PathVariable("id") Long id) throws Exception {

        return surveyService.deleteAnswer(id);

    }




}
