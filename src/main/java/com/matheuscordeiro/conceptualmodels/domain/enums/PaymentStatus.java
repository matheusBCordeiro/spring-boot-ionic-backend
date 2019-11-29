package com.matheuscordeiro.conceptualmodels.domain.enums;

public enum PaymentStatus {
	PENDING(1, "Pending"),
	PAID(2, "Paid"),
	CANCELED(3, "Canceled");
	
	private int code;
	private String description;
	
	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDesciption() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer code) {
		
		if(code == null) {
			return null;
		}
		
		for(PaymentStatus object : PaymentStatus.values()) {
			
			if(code.equals(object.getCode())) {
				return object;
			}
		}
		throw new IllegalArgumentException("id invalid" + code);
	}

}
