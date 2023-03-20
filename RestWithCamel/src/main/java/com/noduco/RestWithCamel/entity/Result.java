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
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Student student;
    @OneToMany
    private List<Marks> marks;

    public Result() {
    }

    public Result(Student student, List<Marks> marks) {
        this.student = student;
        this.marks = marks;
    }

}
