package com.magiamensagem.magiasystem.entities.enums;

public enum ClientStatus {
	
	LIBERADO(1, "Liberado"),
    BLOQUEADO(2, "Bloqueado");
	
	private int cod;
	private String description;

    private ClientStatus(int cod, String descricao){
    	this.cod = cod;
        this.description = descricao;
    }

    public String getDescription() {
        return description;
    }
    public Integer getCod() {
    	return cod;
    }
    
    public static ClientStatus toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(ClientStatus x: ClientStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido: " + cod);
	}
}
