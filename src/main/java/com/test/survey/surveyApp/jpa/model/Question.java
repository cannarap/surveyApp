package com.test.survey.surveyApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sna on 3/10/18.
 */

@Entity
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
    private Survey survey;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "question")
    private List<QuestionOption> options = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();


    public Question(String text) {
        this.text = text;
    }


}
