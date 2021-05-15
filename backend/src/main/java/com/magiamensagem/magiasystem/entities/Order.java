package com.magiamensagem.magiasystem.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.magiamensagem.magiasystem.entities.enums.OrderStatus;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String shipper;
	private String receiver;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;
	
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Embedded
	private Address addressReceiver;
	@OneToOne(cascade = CascadeType.ALL)
	private Phone phoneReceiver;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment pay;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	private Integer orderStatus;

	public Order() {
	}

	public Order(Long id, String shipper, String receiver, Client client,
			Address addressReceiver, Phone phoneReceiver, Payment pay, OrderStatus orderStatus) {
		this.id = id;
		this.shipper = shipper;
		this.receiver = receiver;
		this.client = client;
		this.addressReceiver = addressReceiver;
		this.phoneReceiver = phoneReceiver;
		this.pay = pay;
		this.orderStatus = (orderStatus==null) ? null : orderStatus.getCod();
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAdressReceiver() {
		return addressReceiver;
	}

	public void setAdressReceiver(Address addressReceiver) {
		this.addressReceiver = addressReceiver;
	}

	public Phone getPhoneReceiveir() {
		return phoneReceiver;
	}

	public void setPhoneReceiveir(Phone phoneReceiver) {
		this.phoneReceiver = phoneReceiver;
	}

	public Payment getPay() {
		return pay;
	}

	public void setPay(Payment pay) {
		this.pay = pay;
	}

	public Set<OrderItem> getOrderItems() {
		return items;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.toEnum(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus.getCod();
	}
	
	public Double getTotal() {
		Double sum = items.stream()
				.mapToDouble(item -> item.getSubTotal())
				.sum();
		
		return sum;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
