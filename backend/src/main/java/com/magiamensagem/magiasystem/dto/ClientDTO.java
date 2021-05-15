package com.magiamensagem.magiasystem.dto;

import java.io.Serializable;

import com.magiamensagem.magiasystem.entities.Client;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String cpf;
	private Integer clientStatus;
	private String street;
	private String number;
	private String district;
	private String complement;
	private String numberPhone;
	private Integer typePhone;

	public ClientDTO() {
	}

	public ClientDTO(Long id, String name, String cpf, Integer clientStatus, String street, String number,
			String district, String complement, String numberPhone, Integer typePhone) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.clientStatus = clientStatus;
		this.street = street;
		this.number = number;
		this.district = district;
		this.complement = complement;
		this.numberPhone = numberPhone;
		this.typePhone = typePhone;
	}

	public ClientDTO(Client entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		clientStatus = entity.getClientStatus().getCod();
		street = entity.getAdress().getStreet();
		number = entity.getAdress().getNumber();
		district = entity.getAdress().getDistrict();
		complement = entity.getAdress().getComplement();
		numberPhone = entity.getPhone().getNumber();
		typePhone = entity.getPhone().getType().getCod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getClientStatus() {
		return clientStatus;
	}

	public void setClientStatus(Integer clientStatus) {
		this.clientStatus = clientStatus;
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

	public Integer getTypePhone() {
		return typePhone;
	}

	public void setTypePhone(Integer typePhone) {
		this.typePhone = typePhone;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

}
