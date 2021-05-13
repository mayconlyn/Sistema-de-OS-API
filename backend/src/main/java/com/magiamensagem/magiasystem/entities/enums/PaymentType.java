package com.magiamensagem.magiasystem.entities.enums;

public enum PaymentType {
	
	COBRADOR(1, "Cobrador"),
	PIX(2, "PIX"),
	PAGOUNOLOCAL(3, "Pagou no Local");
	
	private int cod;
	private String description;
	
	private PaymentType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PaymentType toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(PaymentType x: PaymentType.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido: " + cod);
	}

}
