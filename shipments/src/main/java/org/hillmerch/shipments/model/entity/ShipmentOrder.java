package org.hillmerch.shipments.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShipmentOrder {

	@Id
	@GeneratedValue
	private Long id;
	private String orderId;
	private LocalDateTime processingDate;
	private LocalDate estimatedShippingDate;

	public ShipmentOrder() {
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

	public LocalDateTime getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(LocalDateTime processingDate) {
		this.processingDate = processingDate;
	}

	public LocalDate getEstimatedShippingDate() {
		return estimatedShippingDate;
	}

	public void setEstimatedShippingDate(LocalDate estimatedShippingDate) {
		this.estimatedShippingDate = estimatedShippingDate;
	}

	@Override
	public String toString() {
		return "ShipmentOrder{" +
				"id=" + id +
				", orderId=" + orderId +
				", processingDate=" + processingDate +
				", estimatedShippingDate=" + estimatedShippingDate +
				'}';
	}
}
