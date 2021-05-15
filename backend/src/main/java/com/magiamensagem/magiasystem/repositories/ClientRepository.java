package com.magiamensagem.magiasystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magiamensagem.magiasystem.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
