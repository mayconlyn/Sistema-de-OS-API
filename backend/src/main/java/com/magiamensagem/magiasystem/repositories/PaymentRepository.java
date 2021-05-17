package com.magiamensagem.magiasystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magiamensagem.magiasystem.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
