package org.hillmerch.payments.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.hillmerch.payments.client.dto.PaymentDTO;

public interface PaymentsService {

	Page<PaymentDTO> findAll(Pageable pageable);
	PaymentDTO save(PaymentDTO newPaymentDTO) throws JsonProcessingException;
}
