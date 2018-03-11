package com.test.survey.surveyApp.jpa.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name= "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long id;

    @NotNull
    @Column
    private String text;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_Id", nullable = false)
    @JsonIgnore
    private Survey survey;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "question")
    private List<QuestionOption> options = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    public Question() {}


    public Question(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOption> options) {
        this.options = options;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
