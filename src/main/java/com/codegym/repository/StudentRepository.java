package com.codegym.repository;

import com.codegym.model.Lop;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
    Page<Student> findAllByNameContaining(String name, Pageable pageable);
    Page<Student> findAllByLop(Lop lop,Pageable pageable);
}
