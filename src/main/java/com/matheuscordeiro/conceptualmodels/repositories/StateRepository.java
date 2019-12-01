package com.matheuscordeiro.conceptualmodels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheuscordeiro.conceptualmodels.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
