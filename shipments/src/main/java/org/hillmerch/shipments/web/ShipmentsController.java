package org.hillmerch.shipments.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.hillmerch.shipments.client.ShipmentsControllerDefinition;
import org.hillmerch.shipments.client.dto.ShipmentOrderDTO;
import org.hillmerch.shipments.web.assembler.ShipmentOrderAssembler;
import org.hillmerch.shipments.web.service.ShipmentsService;

@RestController
@RequestMapping("/shipments")
public class ShipmentsController implements ShipmentsControllerDefinition {

	private static final Logger logger = LoggerFactory.getLogger( ShipmentsController.class);
	private final ShipmentsService shipmentsService;
	private final ShipmentOrderAssembler shipmentOrderAssembler;


	public ShipmentsController(ShipmentsService shipmentsService, ShipmentOrderAssembler shipmentOrderAssembler) {
		this.shipmentsService = shipmentsService;
		this.shipmentOrderAssembler = shipmentOrderAssembler;
	}

	@Override
	public ResponseEntity<EntityModel<ShipmentOrderDTO>> getShipmentOrder(Long idShipmentOrder) {
		logger.info( "Getting shipment order identified by: " + idShipmentOrder );
		ShipmentOrderDTO shipmentOrderDTO = shipmentsService.findById( idShipmentOrder );
		return ResponseEntity.status( HttpStatus.OK ).body( shipmentOrderAssembler.toModel( shipmentOrderDTO ) );
	}

}
