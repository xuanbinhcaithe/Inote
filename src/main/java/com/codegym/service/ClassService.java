package com.codegym.service;

import com.codegym.model.Lop;

public interface ClassService {
    Iterable<Lop> findAll();
    Lop findById(Long id);
    void save(Lop lop);
    void remove(Long id);
}
