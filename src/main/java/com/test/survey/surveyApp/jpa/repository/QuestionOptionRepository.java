package com.test.survey.surveyApp.jpa.repository;


import com.test.survey.surveyApp.jpa.model.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
}
