package org.hillmerch.payments.model.dao;

import org.hillmerch.payments.model.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentsDao {
	Page<Payment> findAll(Pageable pageable);
	Payment save(Payment payment) ;
}
