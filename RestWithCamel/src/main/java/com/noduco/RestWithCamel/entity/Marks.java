package com.noduco.RestWithCamel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject;
    private Integer maxMarks;
    private Integer marks;
    private String grade;

    public Marks() {
    }
    public Marks(String subject, Integer maxMarks, Integer marks, String grade) {
        this.subject = subject;
        this.maxMarks = maxMarks;
        this.marks = marks;
        this.grade = grade;
    }

}
