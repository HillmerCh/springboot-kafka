package org.hillmerch.shipments.model.dao.impl;

import java.util.Optional;

import org.hillmerch.shipments.model.repository.ShipmentsRepository;
import org.springframework.stereotype.Service;
import org.hillmerch.shipments.model.dao.ShipmentsDao;
import org.hillmerch.shipments.model.entity.ShipmentOrder;

@Service
public class ShipmentsDaoImpl implements ShipmentsDao {

	private final ShipmentsRepository shipmentsRepository;

	public ShipmentsDaoImpl(ShipmentsRepository shipmentsRepository) {
		this.shipmentsRepository = shipmentsRepository;
	}


	@Override
	public Optional<ShipmentOrder> findById(Long idShipmentOrder) {
		return this.shipmentsRepository.findById( idShipmentOrder );
	}

	@Override
	public ShipmentOrder save(ShipmentOrder shipmentOrder) {
		return this.shipmentsRepository.save( shipmentOrder );
	}
}
