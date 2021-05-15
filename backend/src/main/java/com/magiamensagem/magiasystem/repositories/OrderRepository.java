package com.magiamensagem.magiasystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magiamensagem.magiasystem.entities.Client;
import com.magiamensagem.magiasystem.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByClient(Client client);

}
