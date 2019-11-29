package com.matheuscordeiro.conceptualmodels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheuscordeiro.conceptualmodels.domain.State;

public interface StateRepository extends JpaRepository<State, Integer>{

}
