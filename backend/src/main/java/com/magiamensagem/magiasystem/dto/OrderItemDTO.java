package com.magiamensagem.magiasystem.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.magiamensagem.magiasystem.entities.OrderItem;

public class OrderItemDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ProductDTO product;
	private Integer quantity;
	private Double price;
	private List<MessageDTO> messages = new ArrayList<>();
	
	public OrderItemDTO() {
	}
	
	public OrderItemDTO(OrderItem entity) {
		product = new ProductDTO(entity.getProduct());
		quantity = entity.getQuantity();
		price = entity.getPrice();
		
		entity.getMessages().forEach(x -> messages.add(new MessageDTO(x)));
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}
	
}
