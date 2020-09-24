package org.hillmerch.payments.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.hillmerch.payments.client.dto.PaymentDTO;
import org.hillmerch.payments.model.dao.PaymentsDao;
import org.hillmerch.payments.model.entity.Payment;
import org.hillmerch.payments.web.engine.PaymentProducer;
import org.hillmerch.payments.web.service.PaymentsService;

@Service
public class PaymentServiceImpl implements PaymentsService {

	private final PaymentProducer paymentProducer;

	private final ModelMapper MODEL_MAPPER = new ModelMapper();

	private final PaymentsDao paymentsDao;

	public PaymentServiceImpl(PaymentProducer paymentProducer, PaymentsDao paymentsDao) {
		this.paymentProducer = paymentProducer;
		this.paymentsDao = paymentsDao;
	}

	@Override
	public Page<PaymentDTO> findAll(Pageable pageable) {

		Page<Payment> paymentPage = this.paymentsDao.findAll( pageable);

		List<PaymentDTO> paymentDTODtoList = paymentPage.getContent()
				.stream()
				.map(p -> MODEL_MAPPER.map(p, PaymentDTO.class))
				.collect( Collectors.toList());

		return new PageImpl<>( paymentDTODtoList, pageable, paymentPage.getTotalElements());

	}

	@Override
	public PaymentDTO save(PaymentDTO newPaymentDTO) throws JsonProcessingException {

		Payment payment = MODEL_MAPPER.map(newPaymentDTO, Payment.class);

		payment = this.paymentsDao.save( payment );

		newPaymentDTO = MODEL_MAPPER.map(payment, PaymentDTO.class);

		this.paymentProducer.sendMessage( newPaymentDTO );

		return newPaymentDTO;
	}
}
