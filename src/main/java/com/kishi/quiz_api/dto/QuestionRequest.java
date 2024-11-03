package com.kishi.quiz_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class QuestionRequest {
    private String q; // Should match your JSON structure
    private String a;
    private String b;
    private String c;
    private String d;
}
