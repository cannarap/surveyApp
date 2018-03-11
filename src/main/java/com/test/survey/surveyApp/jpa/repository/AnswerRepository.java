package com.test.survey.surveyApp.jpa.repository;

import com.test.survey.surveyApp.jpa.model.Answer;
import com.test.survey.surveyApp.jpa.model.Question;
import com.test.survey.surveyApp.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer findByQuestionAndUser(Question question, User user);
}
