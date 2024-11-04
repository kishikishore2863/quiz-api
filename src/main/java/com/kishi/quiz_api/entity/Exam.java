package com.kishi.quiz_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String examCode;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String timeDuration;


    private int totalMarks;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions=new ArrayList<>();

    // Helper method to manage bidirectional relationship
    public void addQuestion(Question question) {
        questions.add(question);
        question.setExam(this);
    }
}


