package com.kishi.quiz_api.dto;

import com.kishi.quiz_api.entity.Exam;
import com.kishi.quiz_api.entity.Question;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data
public class ExamRequest {
    private ExamDTO exam;
    private List<QuestionDTO> questions;
}
