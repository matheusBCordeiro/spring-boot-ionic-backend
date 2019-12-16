package com.matheuscordeiro.conceptualmodels.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.City;
import com.matheuscordeiro.conceptualmodels.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;

	public List<City> findByState(Integer stateId) {
		return repository.findCities(stateId);
	}
}