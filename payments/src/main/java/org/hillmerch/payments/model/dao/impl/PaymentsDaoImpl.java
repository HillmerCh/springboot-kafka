package org.hillmerch.payments.model.dao.impl;

import org.hillmerch.payments.model.dao.PaymentsDao;
import org.hillmerch.payments.model.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.hillmerch.payments.model.repository.PaymentsRepository;

@Service
public class PaymentsDaoImpl implements PaymentsDao {

	private final PaymentsRepository paymentsRepository;

	public PaymentsDaoImpl(PaymentsRepository paymentsRepository) {
		this.paymentsRepository = paymentsRepository;
	}

	@Override
	public Page<Payment> findAll(Pageable pageable) {
		return this.paymentsRepository.findAll(pageable);
	}

	@Override
	public Payment save(Payment payment) {
		return this.paymentsRepository.save( payment );
	}
}
