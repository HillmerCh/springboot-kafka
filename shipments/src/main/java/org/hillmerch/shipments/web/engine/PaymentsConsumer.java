package org.hillmerch.shipments.web.engine;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hillmerch.shipments.client.dto.ShipmentOrderDTO;
import org.hillmerch.shipments.web.service.ShipmentsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.hillmerch.payments.client.dto.PaymentDTO;

@Service
public class PaymentsConsumer {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule( new JavaTimeModule());
	private final Logger logger = LoggerFactory.getLogger( PaymentsConsumer.class);
	private static final String TOPIC = "MY-STREAM";
	private static final String GROUP_ID = "group_id";
	private static final String ID = "PAYMENTS";

	private final ShipmentsService shipmentsService;

	public PaymentsConsumer(ShipmentsService shipmentsService) {
		this.shipmentsService = shipmentsService;
	}


	@KafkaListener(topics = TOPIC, groupId = GROUP_ID, id = ID)
	public void consume(String message) throws IOException {
		logger.info(String.format("Payment consumed from Shipments message -> %s", message));

		PaymentDTO paymentDTO = OBJECT_MAPPER.readValue( message, PaymentDTO.class);

		ShipmentOrderDTO shipmentOrderDTO = this.createShipmentOrder( paymentDTO );

		logger.info(String.format("Creating shipment order -> %s", shipmentOrderDTO));

		shipmentOrderDTO = this.shipmentsService.save( this.createShipmentOrder( paymentDTO ) );

		logger.info(String.format("Shipment order created -> %s", shipmentOrderDTO));


	}

	private ShipmentOrderDTO createShipmentOrder(PaymentDTO paymentDTO){
		return new ShipmentOrderDTO().processingDate( LocalDateTime.now() )
				.orderId( paymentDTO.getOrderId() )
				.estimatedShippingDate( LocalDate.now() );

	}

}
