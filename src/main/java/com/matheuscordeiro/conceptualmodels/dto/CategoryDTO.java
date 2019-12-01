package com.matheuscordeiro.conceptualmodels.dto;

import java.io.Serializable;

import com.matheuscordeiro.conceptualmodels.domain.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CategoryDTO() {
		
	}

	public CategoryDTO(Category object) {
		id = object.getId();
		name = object.getNome();
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
