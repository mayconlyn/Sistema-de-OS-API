package com.magiamensagem.magiasystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magiamensagem.magiasystem.entities.OrderItem;
import com.magiamensagem.magiasystem.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
