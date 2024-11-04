package com.kishi.quiz_api.controller;

import com.kishi.quiz_api.dto.ExamRequest;
import com.kishi.quiz_api.dto.ExamResponse;
import com.kishi.quiz_api.entity.Exam;
import com.kishi.quiz_api.mapper.ExamMapper;
import com.kishi.quiz_api.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/exam")
public class ExamController {

    private ExamService examService;
    private ExamMapper examMapper;

    public ExamController(ExamService examService, ExamMapper examMapper) {
        this.examService = examService;
        this.examMapper = examMapper;
    }

//    @PostMapping("/addexam")
//    public ResponseEntity<String>addExam(@RequestBody Exam exam){
//
//        Boolean isCreatedExam = examService.addExam(exam);
//
//        if(isCreatedExam) {
//            return new ResponseEntity<String>("exam created successfully", HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("exam created failed", HttpStatus.OK);
//    }

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>>getAllExam(){
        List<Exam> exams =examService.getAllExam();
            return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
    }

    @GetMapping("/{examCode}")
    public ResponseEntity<Exam>getExam(@PathVariable String examCode){
        Exam exam =examService.getExam(examCode);
        if(exam ==null){
            return new ResponseEntity<Exam>(exam,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Exam>(exam,HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<ExamResponse> createExam( @RequestBody ExamRequest examRequest) {
        Exam exam = examMapper.mapExamRequestToExam(examRequest);
        Exam savedExam = examService.createExamWithQuestions(exam);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(examMapper.mapExamToResponse(savedExam));
    }
}
