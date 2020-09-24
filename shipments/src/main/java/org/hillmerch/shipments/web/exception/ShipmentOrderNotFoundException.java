package org.hillmerch.shipments.web.exception;

public class ShipmentOrderNotFoundException extends RuntimeException {
	public ShipmentOrderNotFoundException(Long id) {
		super("Could not find shipment order " + id);
	}
}
