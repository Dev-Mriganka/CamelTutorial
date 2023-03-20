package com.noduco.RestWithCamel.service;

import com.noduco.RestWithCamel.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    Student getStudent(Integer id);

    List<Student> getAllStudents();

    Student registerStudent(Student student);

    Student updateStudent(Student student);

    String deleteStudent(Integer id);

    String test();
}
