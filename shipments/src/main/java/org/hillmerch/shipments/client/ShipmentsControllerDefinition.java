package org.hillmerch.shipments.client;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.hillmerch.shipments.client.dto.ShipmentOrderDTO;

public interface ShipmentsControllerDefinition {

	@Operation(summary = "Get a shipment order", description = "Getting shipment order identified by")
	@GetMapping("/{idShipmentOrder}")
	ResponseEntity<EntityModel<ShipmentOrderDTO>> getShipmentOrder(@PathVariable Long idShipmentOrder);
}
