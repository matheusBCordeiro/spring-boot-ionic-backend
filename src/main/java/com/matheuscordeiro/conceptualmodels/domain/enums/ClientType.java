package com.matheuscordeiro.conceptualmodels.domain.enums;

public enum ClientType {
	NATURALPERSON(1, "Natural Person"), 
	JURIDICALPERSON(2, "Juridical Person");

	private int code;
	private String description;
	
	private ClientType(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDesciption() {
		return description;
	}
	
	public static ClientType toEnum(Integer code) {
		
		if(code == null) {
			return null;
		}
		
		for(ClientType object : ClientType.values()) {
			
			if(code.equals(object.getCode())) {
				return object;
			}
		}
		throw new IllegalArgumentException("id invalid" + code);
	}
}
