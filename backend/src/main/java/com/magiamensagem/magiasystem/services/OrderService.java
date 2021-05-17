package com.magiamensagem.magiasystem.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magiamensagem.magiasystem.dto.MessageDTO;
import com.magiamensagem.magiasystem.dto.OrderDTO;
import com.magiamensagem.magiasystem.dto.OrderItemDTO;
import com.magiamensagem.magiasystem.entities.Address;
import com.magiamensagem.magiasystem.entities.Message;
import com.magiamensagem.magiasystem.entities.Order;
import com.magiamensagem.magiasystem.entities.OrderItem;
import com.magiamensagem.magiasystem.entities.Payment;
import com.magiamensagem.magiasystem.entities.Phone;
import com.magiamensagem.magiasystem.entities.enums.OrderStatus;
import com.magiamensagem.magiasystem.entities.enums.PaymentStatus;
import com.magiamensagem.magiasystem.entities.enums.PaymentType;
import com.magiamensagem.magiasystem.entities.enums.PhoneType;
import com.magiamensagem.magiasystem.entities.pk.OrderItemPK;
import com.magiamensagem.magiasystem.repositories.ClientRepository;
import com.magiamensagem.magiasystem.repositories.MessageRepository;
import com.magiamensagem.magiasystem.repositories.OrderItemRepository;
import com.magiamensagem.magiasystem.repositories.OrderRepository;
import com.magiamensagem.magiasystem.repositories.PaymentRepository;
import com.magiamensagem.magiasystem.repositories.PhoneRepository;
import com.magiamensagem.magiasystem.repositories.ProductRepository;
import com.magiamensagem.magiasystem.services.exceptions.DatabaseException;
import com.magiamensagem.magiasystem.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired 
	private ClientRepository clientRepository;
	@Autowired
	private PhoneRepository phoneRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private OrderItemRepository itemRepository;
	@Autowired
	private PaymentRepository payRepository;
	
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Optional<Order> obj = orderRepository.findById(id);
		Order entity = obj.orElseThrow(()-> new ResourceNotFoundException("Pedido de ID "+id+" não existe"));
		
		return new OrderDTO(entity);
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order entity = new Order();
		Phone phone = new Phone();
		Payment pay = new Payment();
		dtoToEntity(entity, dto, phone, pay);
		entity = orderRepository.save(entity);
		payRepository.save(entity.getPay());
		orderItemDtoToEntity(entity, dto);
		
		return new OrderDTO(entity);
	}
	
	@Transactional
	public OrderDTO update(OrderDTO dto, Long id) {
		try {
			Order entity = orderRepository.getOne(id);
			Phone phone = phoneRepository.getOne(entity.getPhoneReceiveir().getId());
			Payment pay = payRepository.getOne(id);
			dtoToEntity(entity, dto, phone, pay);
			entity = orderRepository.save(entity);
			payRepository.save(entity.getPay());
			
			orderItemDtoToEntity(entity, dto);	

			return new OrderDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Ordem de serviço não encontrada! ID "+id);
		}
	}
	
	public void delete(Long id) {
		try {
			
			orderRepository.deleteById(id);
			
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Ordem de Serviço não encontrada! ID "+id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de Integridade");
		}
	}

	private void dtoToEntity(Order entity, OrderDTO dto, Phone phone, Payment pay) {
		Address address = new Address();
		entity.setReceiver(dto.getReceiver());
		entity.setShipper(dto.getShipper());
		entity.setClient(clientRepository.getOne(dto.getClient().getId()));
		entity.setOrderStatus(OrderStatus.toEnum(dto.getOrderStatus()));
		address.setStreet(dto.getStreet());
		address.setNumber(dto.getNumber());
		address.setDistrict(dto.getDistrict());
		address.setComplement(dto.getComplement());
		entity.setAdressReceiver(address);
		phone.setNumber(dto.getNumberPhone());
		phone.setType(PhoneType.toEnum(dto.getTypePhone()));
		entity.setPhoneReceiveir(phoneRepository.save(phone));
		pay.setPayStatus(PaymentStatus.toEnum(dto.getPayment().getPayStatus()));
		pay.setPayDate(dto.getPayment().getPayDate());
		pay.setPayTime(dto.getPayment().getPayTime());
		pay.setPayType(PaymentType.toEnum(dto.getPayment().getPayType()));
		pay.setOrder(entity);
		entity.setPay(pay);
	}
	
	private void orderItemDtoToEntity(Order entity, OrderDTO dto) {
		for(OrderItemDTO obj : dto.getItemsDto()) {
			OrderItem item;
			try {
				item = itemRepository.findById(new OrderItemPK(entity, productRepository
						.getOne(obj.getProduct().getId()))).get();
			}catch(NoSuchElementException e) {
				item = new OrderItem();
			}
			item.setOrder(entity);
			item.setProduct(productRepository.getOne(obj.getProduct().getId()));
			item.setPrice(obj.getPrice());
			item.setQuantity(obj.getQuantity());
			item = itemRepository.save(item);
			item.getMessages().clear();
			messageDtoToEntity(item, obj);
			entity.getOrderItems().add(item);
		}
	}
	
	private void messageDtoToEntity(OrderItem item, OrderItemDTO dto) {

		for(MessageDTO msg : dto.getMessages()) {
			Message message = new Message();
			if(msg.getId() != null)
				message = messageRepository.getOne(msg.getId());
			message.setCd(msg.getCd());
			message.setMsg(msg.getMsg());
			message.setOrderItem(item);
			message = messageRepository.save(message);
			item.getMessages().add(message);
		}
	}
	
}
