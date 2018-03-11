package com.test.survey.surveyApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sna on 3/10/18.
 */

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "survey_id")
    private  Long id;

    @NotNull
    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "survey")
    private List<Question> questions = new ArrayList<>();

    public Survey(String name) {
        this.name = name;
    }


}
