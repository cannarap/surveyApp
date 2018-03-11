package com.test.survey.surveyApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by sna on 3/10/18.
 */


@Entity
public class QuestionOption {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "option_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @NotNull
    @Column
    private String value;

    public QuestionOption(String value){
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
