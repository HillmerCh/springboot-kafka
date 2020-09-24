package org.hillmerch.payments.web.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.hillmerch.payments.client.dto.PaymentDTO;

@Service
public class PaymentProducer {

	private static final Logger logger = LoggerFactory.getLogger( PaymentProducer.class);
	private static final String TOPIC = "MY-STREAM";
	private static final String ID = "PAYMENTS";

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule( new JavaTimeModule());;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(PaymentDTO paymentDTO) throws JsonProcessingException {

		logger.info(String.format("Payment producing message for -> %s", paymentDTO));
		this.kafkaTemplate.send(TOPIC, ID, OBJECT_MAPPER.writeValueAsString( paymentDTO ));

	}

}
