package com.matheuscordeiro.conceptualmodels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheuscordeiro.conceptualmodels.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
