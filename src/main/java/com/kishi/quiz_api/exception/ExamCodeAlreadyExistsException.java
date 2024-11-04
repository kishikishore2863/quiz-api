package com.kishi.quiz_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExamCodeAlreadyExistsException extends RuntimeException {
    public ExamCodeAlreadyExistsException(String examCode) {
        super(String.format("Exam with code '%s' already exists", examCode));
    }
}