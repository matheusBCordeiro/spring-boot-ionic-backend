package com.matheuscordeiro.conceptualmodels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheuscordeiro.conceptualmodels.domain.City;

public interface CityRepository extends JpaRepository<City, Integer>{

}
