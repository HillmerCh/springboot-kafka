package org.hillmerch.shipments.web.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.hillmerch.shipments.client.dto.ShipmentOrderDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class ShipmentOrderAssembler implements RepresentationModelAssembler<ShipmentOrderDTO, EntityModel<ShipmentOrderDTO>> {
	@Override
	public EntityModel<ShipmentOrderDTO> toModel(ShipmentOrderDTO shipmentOrderDTO) {
		return EntityModel.of(
				shipmentOrderDTO
				//linkTo(methodOn( ShipmentsController.class).get( shipmentOrderDTO.getId())).withSelfRel(),
				//linkTo( methodOn( ShipmentsController.class ).getAllShipmentOrder( 0, 10, "id,asc" ) ).withRel( "shipments" )
		);
	}
}
