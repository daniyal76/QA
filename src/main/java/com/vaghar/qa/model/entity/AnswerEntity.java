package com.vaghar.qa.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "answer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @Column
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;
}
