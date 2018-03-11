package com.test.survey.surveyApp.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "questionOptions")
public class QuestionOption {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    private Question question;

    @NotNull
    @Column
    private String value;

    public QuestionOption() {}

    public QuestionOption(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
