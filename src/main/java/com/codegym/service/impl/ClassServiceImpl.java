package com.codegym.service.impl;

import com.codegym.model.Lop;
import com.codegym.repository.ClassRepository;
import com.codegym.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Override
    public Iterable<Lop> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Lop findById(Long id) {
        return classRepository.findOne(id);
    }

    @Override
    public void save(Lop lop) {
    classRepository.save(lop);
    }

    @Override
    public void remove(Long id) {
        classRepository.delete(id);
    }
}
