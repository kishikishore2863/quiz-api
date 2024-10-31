package com.kishi.quiz_api.service;

import com.kishi.quiz_api.entity.Exam;

import java.util.List;

public interface ExamService {

    public Boolean addExam(Exam exam);

    public List<Exam> getAllExam();

    public Exam getExam(String examCode);
}
