package com.kishi.quiz_api.service;

import com.kishi.quiz_api.entity.Exam;
import com.kishi.quiz_api.entity.Question;
import com.kishi.quiz_api.repository.ExamRepository;
import com.kishi.quiz_api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService{

    private ExamRepository examRepository;
    private QuestionRepository questionRepository;


    @Autowired
    public ExamServiceImpl(ExamRepository examRepository,QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository =questionRepository;
    }

    @Override
    public Boolean addExam(Exam exam) {
        examRepository.save(exam);
        return true;
    }

    @Override
    public List<Exam> getAllExam() {
        List<Exam> exams = examRepository.findAll();
        return exams;
    }

    @Override
    public Exam getExam(String examCode) {
        return examRepository.findByExamCode(examCode).orElse(null);
    }

//    @Override
//    public Exam createExamWithQuestions(Exam exam, List<Question> questions) {
//        Exam savedExam = examRepository.save(exam);
//        // Set the exam for each question and save them
//        for (Question question : questions) {
//            question.setExam(savedExam);
//            questionRepository.save(question);
//        }
//        return savedExam;
//    }


}
