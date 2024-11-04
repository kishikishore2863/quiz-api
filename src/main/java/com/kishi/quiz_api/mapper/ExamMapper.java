package com.kishi.quiz_api.mapper;

import com.kishi.quiz_api.dto.*;
import com.kishi.quiz_api.entity.Exam;
import com.kishi.quiz_api.entity.Question;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExamMapper {

    public ExamResponse mapExamToResponse(Exam exam) {
        if (exam == null) {
            return null;
        }

        return ExamResponse.builder()
                .id(exam.getId())
                .examCode(exam.getExamCode())
                .title(exam.getTitle())
                .description(exam.getDescription())
                .timeDuration(exam.getTimeDuration())
                .totalMarks(exam.getTotalMarks())
                .questions(mapQuestionsToResponse(exam.getQuestions()))
//                .createdAt(exam.getCreatedAt())
//                .updatedAt(exam.getUpdatedAt())
                .build();
    }

    private List<QuestionResponse> mapQuestionsToResponse(List<Question> questions) {
        if (questions == null) {
            return Collections.emptyList();
        }

        return questions.stream()
                .map(this::mapQuestionToResponse)
                .collect(Collectors.toList());
    }

    private QuestionResponse mapQuestionToResponse(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .optionA(question.getOptionA())
                .optionB(question.getOptionB())
                .optionC(question.getOptionC())
                .optionD(question.getOptionD())
                .build();
    }

    public Exam mapExamRequestToExam(ExamRequest examRequest) {
        Exam exam = new Exam();
        ExamDTO examDTO = examRequest.getExam();

        exam.setExamCode(examDTO.getExamCode());
        exam.setTitle(examDTO.getTitle());
        exam.setDescription(examDTO.getDescription());
        exam.setTimeDuration(examDTO.getTimeDuration());
        exam.setTotalMarks(examDTO.getTotalMarks());

        List<Question> questions = examRequest.getQuestions().stream()
                .map(this::mapQuestionDTOToQuestion)
                .collect(Collectors.toList());

        questions.forEach(exam::addQuestion);

        return exam;
    }

    private Question mapQuestionDTOToQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestionText(questionDTO.getQuestionText());
        question.setOptionA(questionDTO.getOptionA());
        question.setOptionB(questionDTO.getOptionB());
        question.setOptionC(questionDTO.getOptionC());
        question.setOptionD(questionDTO.getOptionD());
        return question;
    }
}
