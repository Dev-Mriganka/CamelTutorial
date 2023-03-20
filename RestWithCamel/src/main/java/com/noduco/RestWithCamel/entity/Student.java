package com.noduco.RestWithCamel.entity;

import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NamedQuery(name = "findAll", query = "SELECT s FROM Student s")
@NamedQuery(name = "findById", query = "SELECT s FROM Student s WHERE s.id = :id")
@NamedQuery(name = "findByName", query = "SELECT s FROM Student s WHERE s.name like :name")
@NamedQuery(name = "DeleteById", query = "DELETE FROM Student s WHERE s.id = :id")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<Result> results;
    private String phone;

    public Student() {
    }

    public Student(String name, String email, Address address, List<Result> results, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.results = results;
        this.phone = phone;
    }

}
