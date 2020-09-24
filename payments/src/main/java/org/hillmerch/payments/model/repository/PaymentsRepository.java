package org.hillmerch.payments.model.repository;

import org.hillmerch.payments.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payment, Long> {
}
