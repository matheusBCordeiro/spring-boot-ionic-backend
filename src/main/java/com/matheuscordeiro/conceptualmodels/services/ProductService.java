package com.matheuscordeiro.conceptualmodels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.Category;
import com.matheuscordeiro.conceptualmodels.domain.Product;
import com.matheuscordeiro.conceptualmodels.repositories.CategoryRepository;
import com.matheuscordeiro.conceptualmodels.repositories.ProductRepository;
import com.matheuscordeiro.conceptualmodels.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id) {

		Optional<Product> object = repository.findById(id);
		if(object == null) {
			throw new ObjectNotFoundException("Object not found! id:" + id
					+ ", Type:" + Product.class.getName());
		}
		return object.orElse(null);
	}
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return repository.findDistinctByNomeContainingAndCategoriasIn(name, categories, pageRequest);
	}
}