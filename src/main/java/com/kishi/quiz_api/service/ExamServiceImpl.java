package com.kishi.quiz_api.service;

import com.kishi.quiz_api.entity.Exam;
import com.kishi.quiz_api.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService{

    private ExamRepository examRepository;

    @Autowired
    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
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
}
