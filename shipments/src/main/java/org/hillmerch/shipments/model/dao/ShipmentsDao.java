package org.hillmerch.shipments.model.dao;

import java.util.Optional;

import org.hillmerch.shipments.model.entity.ShipmentOrder;

public interface ShipmentsDao {
	Optional<ShipmentOrder> findById(Long idShipmentOrder);
	ShipmentOrder save(ShipmentOrder shipmentOrder);
}
