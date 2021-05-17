package com.magiamensagem.magiasystem.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.magiamensagem.magiasystem.entities.Payment;

public class PaymentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LocalDate payDate;
	private LocalTime payTime;
	private Integer payStatus;
	private Integer payType;
	
	public PaymentDTO() {
	}

	public PaymentDTO(Long id, LocalDate payDate, LocalTime payTime, Integer payStatus, Integer payType) {
		this.id = id;
		this.payDate = payDate;
		this.payTime = payTime;
		this.payStatus = payStatus;
		this.payType = payType;
	}
	
	public PaymentDTO(Payment payment) {
		id = payment.getId();
		payDate = payment.getPayDate();
		payTime = payment.getPayTime();
		payStatus = payment.getPayStatus().getCod();
		payType = payment.getPayType().getCod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}

	public LocalTime getPayTime() {
		return payTime;
	}

	public void setPayTime(LocalTime payTime) {
		this.payTime = payTime;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
}
