package com.kishi.quiz_api.repository;

import com.kishi.quiz_api.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam,Long> {

    Optional<Exam>  findByExamCode(String examCode);
}
