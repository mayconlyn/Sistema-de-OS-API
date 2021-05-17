package com.magiamensagem.magiasystem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magiamensagem.magiasystem.dto.OrderDTO;
import com.magiamensagem.magiasystem.entities.Order;
import com.magiamensagem.magiasystem.repositories.OrderRepository;
import com.magiamensagem.magiasystem.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Optional<Order> obj = orderRepository.findById(id);
		Order entity = obj.orElseThrow(()-> new ResourceNotFoundException("Pedido de ID "+id+" não existe"));
		
		return new OrderDTO(entity);
	}

}
