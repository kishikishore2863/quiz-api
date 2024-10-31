package com.kishi.quiz_api.service;

import com.kishi.quiz_api.entity.Exam;
import com.kishi.quiz_api.entity.Question;
import com.kishi.quiz_api.repository.ExamRepository;
import com.kishi.quiz_api.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    private QuestionRepository questionRepository;
    private ExamRepository examRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository,ExamRepository examRepository){
        this.questionRepository =questionRepository;
        this.examRepository=examRepository;
    }

    @Override
    public String addQuestion(String examCode, List<Question> question) {
        Exam getExam =examRepository.findByExamCode(examCode).orElse(null);
        if(getExam != null){
            getExam.setQuestion(question);
            return "question add successfully ";
        }
        if(getExam == null) {
         return "exam not fund";
        }
        return null;
    }
}
