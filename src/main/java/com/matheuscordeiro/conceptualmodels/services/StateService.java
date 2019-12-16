package com.matheuscordeiro.conceptualmodels.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.State;
import com.matheuscordeiro.conceptualmodels.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repository;

	public List<State> findAll() {
		return repository.findAllByOrderByName();
	}
}