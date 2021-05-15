package com.magiamensagem.magiasystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magiamensagem.magiasystem.dto.ClientDTO;
import com.magiamensagem.magiasystem.dto.ClientResponseDTO;
import com.magiamensagem.magiasystem.entities.Client;
import com.magiamensagem.magiasystem.entities.Order;
import com.magiamensagem.magiasystem.repositories.ClientRepository;
import com.magiamensagem.magiasystem.repositories.OrderRepository;
import com.magiamensagem.magiasystem.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){	
		
		Page<Client> obj = clientRepository.findAll(pageRequest);
		return obj.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientResponseDTO findById (Long id) {
		
		Optional<Client> obj = clientRepository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Client não existe! ID " + id));
		List<Order> list = orderRepository.findByClient(entity);
		entity.setOrders(list);
		return new ClientResponseDTO(entity);
	}

}
