package org.hillmerch.payments.client.dto;

import java.time.LocalDateTime;

public class PaymentDTO {

	private Long id;
	private String orderId;
	private LocalDateTime dateOfPayment;
	private Long value;

	public PaymentDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(LocalDateTime dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}




	public PaymentDTO orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public PaymentDTO dateOfPayment(LocalDateTime dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
		return this;
	}

	public PaymentDTO value(Long value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		return "PaymentDTO{" +
				"id=" + id +
				", orderId='" + orderId + '\'' +
				", dateOfPayment=" + dateOfPayment +
				", value=" + value +
				'}';
	}
}
