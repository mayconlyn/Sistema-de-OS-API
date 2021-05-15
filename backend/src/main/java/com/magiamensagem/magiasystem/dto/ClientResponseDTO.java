package com.magiamensagem.magiasystem.dto;

import java.util.ArrayList;
import java.util.List;

import com.magiamensagem.magiasystem.entities.Client;
import com.magiamensagem.magiasystem.entities.Order;

public class ClientResponseDTO extends ClientDTO {
	private static final long serialVersionUID = 1L;

	private List<OrderDTO> orders = new ArrayList<>();

	public ClientResponseDTO() {
		super();
	}

	public ClientResponseDTO(Client entity) {
		super(entity);
		for (Order order : entity.getOrders()) {
			orders.add(new OrderDTO(order));
		}

	}

	public List<OrderDTO> getOrders() {
		return orders;
	}

}
