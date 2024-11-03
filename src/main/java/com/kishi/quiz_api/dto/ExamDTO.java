package com.kishi.quiz_api.dto;

import lombok.Data;

@Data
public class ExamDTO {

    private String examCode;


    private String title;

    private String description;


    private String timeDuration;

    private int totalMarks;
}