package com.magiamensagem.magiasystem.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.magiamensagem.magiasystem.entities.Order;

public class OrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String shipper;
	private String receiver;
	private String street;
	private String number;
	private String district;
	private String complement;
	private String numberPhone;
	private Integer typePhone;
	private Integer orderStatus;
	private ClientDTO client;
	private PaymentDTO payment;
	private List<OrderItemDTO> itemsDto = new ArrayList<>();
	
	public OrderDTO(){
	}
	
	public OrderDTO(Order entity) {
		
		id = entity.getId();
		shipper = entity.getShipper();
		receiver = entity.getReceiver();
		street = entity.getAdressReceiver().getStreet();
		number = entity.getAdressReceiver().getNumber();
		district = entity.getAdressReceiver().getDistrict();
		complement = entity.getAdressReceiver().getComplement();
		numberPhone = entity.getPhoneReceiveir().getNumber();
		typePhone = entity.getPhoneReceiveir().getType().getCod();
		orderStatus = entity.getOrderStatus().getCod();
		client = new ClientDTO(entity.getClient());
		payment = new PaymentDTO(entity.getPay());
		
		entity.getOrderItems().forEach(x -> itemsDto.add(new OrderItemDTO(x)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public Integer getTypePhone() {
		return typePhone;
	}

	public void setTypePhone(Integer typePhone) {
		this.typePhone = typePhone;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}

	public List<OrderItemDTO> getItemsDto() {
		return itemsDto;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
}
