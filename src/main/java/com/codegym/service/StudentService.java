package com.codegym.service;

import com.codegym.model.Lop;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Page<Student> findAll(Pageable pageable);
    Student findById(Long id);
    void save(Student student);
    void remove(Long id);
    Page<Student> findAllByNameContaining(String name, Pageable pageable);
    Page<Student> findAllByLop(Lop lop, Pageable pageable);
}
