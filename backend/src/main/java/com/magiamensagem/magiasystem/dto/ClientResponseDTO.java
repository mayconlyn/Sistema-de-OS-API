package com.magiamensagem.magiasystem.dto;

import java.util.ArrayList;
import java.util.List;

import com.magiamensagem.magiasystem.entities.Client;

public class ClientResponseDTO extends ClientDTO {
	private static final long serialVersionUID = 1L;

	private List<OrderDTO> orders = new ArrayList<>();

	public ClientResponseDTO() {
		super();
	}

	public ClientResponseDTO(Client entity) {
		super(entity);
		entity.getOrders().forEach(x -> orders.add(new OrderDTO(x)));
	}

	public List<OrderDTO> getOrders() {
		return orders;
	}

}
