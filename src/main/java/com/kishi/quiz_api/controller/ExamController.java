package com.kishi.quiz_api.controller;

import com.kishi.quiz_api.dto.ExamRequest;
import com.kishi.quiz_api.dto.QuestionRequest;
import com.kishi.quiz_api.entity.Exam;
import com.kishi.quiz_api.entity.Question;
import com.kishi.quiz_api.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/exam")
public class ExamController {

    private ExamService examService;
    private ExamRequest examRequest;

    public ExamController(ExamService examService,ExamRequest examRequest) {
        this.examService = examService;
        this.examRequest=examRequest;
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
    @PostMapping("/addexam")
    public ResponseEntity<Exam> createExam(@RequestBody ExamRequest examRequest) {
        // Create an Exam object
        Exam exam = new Exam();
        exam.setExamCode(examRequest.getExam().getExamCode());
        exam.setTitle(examRequest.getExam().getTitle());
        exam.setDescription(examRequest.getExam().getDescription());
        exam.setTimeDuration(examRequest.getExam().getTimeDuration());
        exam.setTotalMarks(examRequest.getExam().getTotalMarks());

        // Map QuestionRequest to Question entities
        List<Question> questions = new ArrayList<>();
        for (QuestionRequest questionRequest : examRequest.getQuestions()) {
            Question question = new Question();
            question.setQ(questionRequest.getQuestionText()); // Use the correct getter for question text
            question.setA(questionRequest.getOptionA());
            question.setB(questionRequest.getOptionB());
            question.setC(questionRequest.getOptionC()); // Fixed from 'setB' to 'setC'
            question.setD(questionRequest.getOptionD());
            question.setExam(exam); // Set the exam relationship
            questions.add(question);
        }

        // Call service to save exam and questions
        Exam savedExam = examService.createExamWithQuestions(exam, questions);
        return new ResponseEntity<>(savedExam, HttpStatus.OK);
    }

}
