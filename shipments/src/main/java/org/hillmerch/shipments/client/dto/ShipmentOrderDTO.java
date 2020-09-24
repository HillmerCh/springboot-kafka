package org.hillmerch.shipments.client.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ShipmentOrderDTO {

	private Long id;
	private String orderId;
	private LocalDateTime processingDate;
	private LocalDate estimatedShippingDate;

	public ShipmentOrderDTO() {
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
		return "ShipmentOrderDTO{" +
				"id=" + id +
				", orderId=" + orderId +
				", processingDate=" + processingDate +
				", estimatedShippingDate=" + estimatedShippingDate +
				'}';
	}



	public ShipmentOrderDTO orderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public ShipmentOrderDTO processingDate(LocalDateTime processingDate) {
		this.processingDate = processingDate;
		return this;
	}

	public ShipmentOrderDTO estimatedShippingDate(LocalDate estimatedShippingDate) {
		this.estimatedShippingDate = estimatedShippingDate;
		return this;
	}

}
