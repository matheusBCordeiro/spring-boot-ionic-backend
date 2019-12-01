package com.matheuscordeiro.conceptualmodels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.Category;
import com.matheuscordeiro.conceptualmodels.dto.CategoryDTO;
import com.matheuscordeiro.conceptualmodels.repositories.CategoryRepository;
import com.matheuscordeiro.conceptualmodels.services.exceptions.DataIntegrityException;
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
	
	public Category insert(Category object) {
		object.setId(null);
		return repository.save(object);
	}
	
	public Category update(Category object) {
		Category newObject = find(object.getId());
		updateData(newObject, object);
		return repository.save(newObject);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete a category that has products");
		}
	}
	
	public List<Category>  findAll(){
		return repository.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objectDto) {
		return new Category(objectDto.getId(), objectDto.getName());
	}
	public void updateData(Category newObject, Category object) {
		newObject.setName(object.getName());
	}
}