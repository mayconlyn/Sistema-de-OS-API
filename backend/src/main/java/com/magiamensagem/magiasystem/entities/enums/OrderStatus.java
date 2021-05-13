package com.magiamensagem.magiasystem.entities.enums;

public enum OrderStatus {
	
	PENDENTE(1, "Pendente Envio"),
    ENVIADO(2, "Enviado");
	
	private int cod;
	private String description;
	
	private OrderStatus(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	public String getDescription() {
		return description;
	}
	
	public static OrderStatus toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(OrderStatus x: OrderStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido: " + cod);
	}

}
