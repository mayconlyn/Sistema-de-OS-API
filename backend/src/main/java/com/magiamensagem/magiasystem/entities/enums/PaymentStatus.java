package com.magiamensagem.magiasystem.entities.enums;

public enum PaymentStatus {
	
	PENDENTE(1, "Pendente"),
    PAGO(2, "Pago");

	private int cod;
    private String description;

    private PaymentStatus(int cod, String description){
    	this.cod = cod;
        this.description = description;
        
    }

    public String getDescription() {
        return description;
    }
    public Integer getCod() {
    	return cod;
    }
    
    public static PaymentStatus toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(PaymentStatus x: PaymentStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido: " + cod);
	}

}
