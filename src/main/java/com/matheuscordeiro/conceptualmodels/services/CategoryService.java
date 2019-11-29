package com.matheuscordeiro.conceptualmodels.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.Category;
import com.matheuscordeiro.conceptualmodels.repositories.CategoryRepository;
import com.matheuscordeiro.conceptualmodels.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category find(Integer id) {

		Optional<Category> object = repository.findById(id);
		if(object == null) {
			throw new ObjectNotFoundException("Object not found! id:" + id
					+ ", Type:" + Category.class.getName());
		}
		return object.orElse(null);
	}

}
