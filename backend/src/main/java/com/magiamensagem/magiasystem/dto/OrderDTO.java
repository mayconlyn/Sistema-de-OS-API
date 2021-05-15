package com.magiamensagem.magiasystem.dto;

import java.io.Serializable;

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
	
	


}
