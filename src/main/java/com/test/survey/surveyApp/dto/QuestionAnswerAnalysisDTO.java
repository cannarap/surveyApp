package com.test.survey.surveyApp.dto;


import java.util.Map;

public class QuestionAnswerAnalysisDTO {

    private String  questionText;

    private int totalOptionsAvailable;

    private Map<String, Integer> answerCounts;

    private Map<String, Double>  percentageDistribution;

    public QuestionAnswerAnalysisDTO() {

    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getTotalOptionsAvailable() {
        return totalOptionsAvailable;
    }

    public void setTotalOptionsAvailable(int totalOptionsAvailable) {
        this.totalOptionsAvailable = totalOptionsAvailable;
    }

    public Map<String, Double> getPercentageDistribution() {
        return percentageDistribution;
    }

    public void setPercentageDistribution(Map<String, Double> percentageDistribution) {
        this.percentageDistribution = percentageDistribution;
    }

    public Map<String, Integer> getAnswerCounts() {
        return answerCounts;
    }

    public void setAnswerCounts(Map<String, Integer> answerCounts) {
        this.answerCounts = answerCounts;
    }
}
