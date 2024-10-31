package com.kishi.quiz_api.service;

import com.kishi.quiz_api.entity.Question;

import java.util.List;

public interface QuestionService {

    public String addQuestion(String examCode , List<Question> question);
}
