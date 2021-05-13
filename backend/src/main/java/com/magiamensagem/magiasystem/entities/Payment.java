package com.magiamensagem.magiasystem.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magiamensagem.magiasystem.entities.enums.PaymentStatus;
import com.magiamensagem.magiasystem.entities.enums.PaymentType;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@Column(columnDefinition = "DATE")
	private LocalDate payDate;
	@Column(columnDefinition = "TIME")
	private LocalTime payTime;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;
	private Integer payStatus;
	private Integer payType;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="order_id")
	@MapsId
	private Order order;

	public Payment() {
	}

	public Payment(Long id, LocalDate payDate, LocalTime payTime, PaymentStatus payStatus, PaymentType payType) {
		this.id = id;
		this.payDate = payDate;
		this.payTime = payTime;
		this.payStatus = (payStatus == null) ? null : payStatus.getCod();
		this.payType = (payType == null) ? null : payType.getCod();
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
	
	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}

	public PaymentStatus getPayStatus() {
		return PaymentStatus.toEnum(payStatus);
	}

	public void setPayStatus(PaymentStatus payStatus) {
		this.payStatus = payStatus.getCod();
	}
	
	public PaymentType getPayType() {
		return PaymentType.toEnum(payType);
	}

	public void setPayType(PaymentType payType) {
		this.payStatus = payType.getCod();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
