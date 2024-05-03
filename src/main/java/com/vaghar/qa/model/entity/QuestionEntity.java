package com.vaghar.qa.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @NotNull
    @Column(nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question", orphanRemoval = true)
    private Set<AnswerEntity> answers = new HashSet<>();
}
