package org.hillmerch.shipments.web.service.impl;

import org.hillmerch.shipments.model.dao.ShipmentsDao;
import org.hillmerch.shipments.web.exception.ShipmentOrderNotFoundException;
import org.hillmerch.shipments.web.service.ShipmentsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.hillmerch.shipments.client.dto.ShipmentOrderDTO;
import org.hillmerch.shipments.model.entity.ShipmentOrder;

@Service
public class ShipmentsServiceImpl implements ShipmentsService {

	private final ShipmentsDao shipmentsDao;
	private ModelMapper MODEL_MAPPER = new ModelMapper();


	public ShipmentsServiceImpl(ShipmentsDao shipmentsDao) {
		this.shipmentsDao = shipmentsDao;
	}
	@Override
	public ShipmentOrderDTO findById(Long idShipmentOrder) {
		ShipmentOrder shipmentOrder = this.shipmentsDao.findById( idShipmentOrder ).orElseThrow(() -> new ShipmentOrderNotFoundException( idShipmentOrder));
		return MODEL_MAPPER.map( shipmentOrder,  ShipmentOrderDTO.class);
	}

	@Override
	public ShipmentOrderDTO save(ShipmentOrderDTO shipmentOrderDTO) {
		ShipmentOrder shipmentOrder =  this.shipmentsDao.save( MODEL_MAPPER.map( shipmentOrderDTO,  ShipmentOrder.class) );
		return MODEL_MAPPER.map( shipmentOrder,  ShipmentOrderDTO.class);
	}

}
