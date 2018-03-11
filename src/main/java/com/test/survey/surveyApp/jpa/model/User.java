package com.test.survey.surveyApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column
    private String name;

    public User(String name) {
        this.name = name;
    }


}
