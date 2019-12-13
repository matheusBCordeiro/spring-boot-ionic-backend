package com.matheuscordeiro.conceptualmodels.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.matheuscordeiro.conceptualmodels.domain.Client;
import com.matheuscordeiro.conceptualmodels.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	
	@NotEmpty(message="Field requerid")
	@Length(min=5, max=120, message="Size must be between 5 and 120 characters")
	private String name;
	
	@NotEmpty(message="Field requerid")
	@Email(message="Invalid Email")
	private String email;
	
	public ClientDTO() {
	}

	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}