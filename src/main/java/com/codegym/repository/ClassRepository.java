package com.codegym.repository;

import com.codegym.model.Lop;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClassRepository extends PagingAndSortingRepository<Lop,Long> {
}
