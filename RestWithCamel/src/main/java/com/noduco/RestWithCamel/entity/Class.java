package com.noduco.RestWithCamel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String teacher;
    @OneToMany
    private List<Student> students;
    @OneToMany
    private List<Result> results;
    private Integer capacity;

    public Class() {
    }

    public Class(String name, String teacher, List<Student> students, List<Result> results, Integer capacity) {
        this.name = name;
        this.teacher = teacher;
        this.students = students;
        this.results = results;
        this.capacity = capacity;
    }

}
