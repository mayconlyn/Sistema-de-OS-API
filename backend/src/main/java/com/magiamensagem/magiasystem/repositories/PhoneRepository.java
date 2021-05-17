package com.magiamensagem.magiasystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magiamensagem.magiasystem.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
