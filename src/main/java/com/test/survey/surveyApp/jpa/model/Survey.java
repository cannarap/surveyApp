package com.test.survey.surveyApp.jpa.model;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "survey_id")
    private  Long id;

    @NotNull
    @Column(unique=true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "survey")
    private List<Question> questions = new ArrayList<>();

    public Survey() {}

    public Survey(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
