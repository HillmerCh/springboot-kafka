package org.hillmerch.shipments.web.service;

import org.hillmerch.shipments.client.dto.ShipmentOrderDTO;

public interface ShipmentsService {
	ShipmentOrderDTO findById(Long idShipmentOrder) ;
	ShipmentOrderDTO save(ShipmentOrderDTO ShipmentOrderDTO);
}
