package com.kishi.quiz_api.controller;

import com.kishi.quiz_api.entity.Question;
import com.kishi.quiz_api.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exam")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService=questionService;
    }

    @PostMapping("/{examCode}")
    public ResponseEntity<String> addQuestion(@PathVariable String examCode , @RequestBody List<Question> question){
        String res = questionService.addQuestion(examCode,question);
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }



}
