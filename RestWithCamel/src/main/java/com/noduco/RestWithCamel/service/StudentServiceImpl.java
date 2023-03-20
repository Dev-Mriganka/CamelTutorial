package com.noduco.RestWithCamel.service;

import com.noduco.RestWithCamel.entity.Student;
import com.noduco.RestWithCamel.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl /*implements StudentService*/{

//    @Autowired
//    private StudentRepo studentRepo;
//
//    public Student getStudent(Integer id) {
//        System.out.println("Student id: " + id);
//        Student student = studentRepo.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
//        System.out.println("Student: " + student);
//        return student;
//
//    }
//
//    @Override
//    public List<Student> getAllStudents() {
//        return studentRepo.findAll();
//    }
//
//    public Student registerStudent(Student student) {
//        return studentRepo.save(student);
//    }
//
//    public Student updateStudent(Student student) {
//        Student student1 = studentRepo.findById(student.getId()).orElseThrow(()-> new RuntimeException("Student not found"));
//        student1.setName(student.getName());
//        student1.setEmail(student.getEmail());
//        student1.setAddress(student.getAddress());
//        student1.setPhone(student.getPhone());
//        return studentRepo.save(student1);
//    }
//
//    public String deleteStudent(Integer id) {
//        Student student1 = studentRepo.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
//        studentRepo.delete(student1);
//        return "Student with id " + id + " deleted";
//    }
//
//    @Override
//    public String test() {
//        return "Test";
//    }

}
