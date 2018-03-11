package com.test.survey.surveyApp.jpa.repository;


import com.test.survey.surveyApp.jpa.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository  extends JpaRepository<Survey, Long>{

    public Survey findByName(String name);
}
