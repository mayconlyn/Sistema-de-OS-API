package com.magiamensagem.magiasystem.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magiamensagem.magiasystem.dto.ClientDTO;
import com.magiamensagem.magiasystem.dto.ClientResponseDTO;
import com.magiamensagem.magiasystem.entities.Address;
import com.magiamensagem.magiasystem.entities.Client;
import com.magiamensagem.magiasystem.entities.Order;
import com.magiamensagem.magiasystem.entities.Phone;
import com.magiamensagem.magiasystem.entities.enums.ClientStatus;
import com.magiamensagem.magiasystem.entities.enums.PhoneType;
import com.magiamensagem.magiasystem.repositories.ClientRepository;
import com.magiamensagem.magiasystem.repositories.OrderRepository;
import com.magiamensagem.magiasystem.repositories.PhoneRepository;
import com.magiamensagem.magiasystem.services.exceptions.DatabaseException;
import com.magiamensagem.magiasystem.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {

		Page<Client> obj = clientRepository.findAll(pageRequest);
		return obj.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientResponseDTO findById(Long id) {

		Optional<Client> obj = clientRepository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Client não existe! ID " + id));
		List<Order> list = orderRepository.findByClient(entity);
		entity.setOrders(list);
		
		return new ClientResponseDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		Phone phone = new Phone();
		dtoToEntity(entity, dto, phone);
		entity = clientRepository.save(entity);

		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(ClientDTO dto, Long id) {
		try {
			Client entity = clientRepository.getOne(id);
			Phone phone = phoneRepository.getOne(entity.getPhone().getId());
			dtoToEntity(entity, dto, phone);
			entity = clientRepository.save(entity);

			return new ClientDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Client não existe! ID " + id);
		}
	}

	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Cliente não existe! ID" + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de Integridade");
		}
	}

	private void dtoToEntity(Client entity, ClientDTO dto, Phone phone) {
		Address address = new Address();
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setClientStatus(ClientStatus.toEnum(dto.getClientStatus()));
		address.setStreet(dto.getStreet());
		address.setNumber(dto.getNumber());
		address.setDistrict(dto.getDistrict());
		address.setComplement(dto.getComplement());
		entity.setAdress(address);
		phone.setNumber(dto.getNumberPhone());
		phone.setType(PhoneType.toEnum(dto.getTypePhone()));
		entity.setPhone(phoneRepository.save(phone));
	}

}
