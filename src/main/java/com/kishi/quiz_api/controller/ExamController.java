package com.kishi.quiz_api.controller;

import com.kishi.quiz_api.entity.Exam;
import com.kishi.quiz_api.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exam")
public class ExamController {

    private ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/addexam")
    public ResponseEntity<String>addExam(@RequestBody Exam exam){

        Boolean isCreatedExam = examService.addExam(exam);

        if(isCreatedExam) {
            return new ResponseEntity<String>("exam created successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("exam created failed", HttpStatus.OK);
    }

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


}
