package com.matheuscordeiro.conceptualmodels.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.matheuscordeiro.conceptualmodels.domain.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CategoryDTO() {
		
	}
	
	@NotEmpty(message="Fill requerid")
	@Length(min=5, max=80, message="Size must be between 5 and 80 characters")
	public CategoryDTO(Category object) {
		id = object.getId();
		name = object.getName();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
