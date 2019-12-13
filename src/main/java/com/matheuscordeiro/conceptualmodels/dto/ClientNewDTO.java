package com.matheuscordeiro.conceptualmodels.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.matheuscordeiro.conceptualmodels.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Field requerid")
	@Length(min=5, max=120, message="Size must be between 5 and 120 characters")
	private String name;
	
	@NotEmpty(message="Field requerid")
	@Email(message="Invalid Email")
	private String email;
	
	@NotEmpty(message="Field requerid")
	private String cpfORCnpj;
	private Integer type;
	
	@NotEmpty(message="Field requerid")
	private String password;
	
	@NotEmpty(message="Field requerid")
	private String place;
	
	@NotEmpty(message="Field requerid")
	private String number;
	
	private String complement;
	private String district;
	
	@NotEmpty(message="Field requerid")
	private String cep; 
	
	@NotEmpty(message="Field requerid")
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Integer cityId;
	
	public ClientNewDTO() {
		
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

	public String getCpfORCnpj() {
		return cpfORCnpj;
	}

	public void setCpfORCnpj(String cpfORCnpj) {
		this.cpfORCnpj = cpfORCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}