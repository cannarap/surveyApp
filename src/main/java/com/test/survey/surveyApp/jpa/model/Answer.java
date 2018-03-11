package com.test.survey.surveyApp.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    private Question question;


    @OneToOne
    @JoinColumn(name="option_id")
    private QuestionOption selectedOption;


    public Answer(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QuestionOption getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(QuestionOption selectedOption) {
        this.selectedOption = selectedOption;
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
