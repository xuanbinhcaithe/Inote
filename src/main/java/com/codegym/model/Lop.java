package com.codegym.model;

import org.springframework.data.domain.Page;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "class")
public class Lop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotEmpty
    private String name;

    @OneToMany(targetEntity = Student.class)
    public List<Student> students;

    public Lop() {
    }

    public Lop(@NotEmpty String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public Lop(@NotEmpty String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
