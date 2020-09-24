package org.hillmerch.shipments.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.hillmerch.shipments.model.entity.ShipmentOrder;

public interface ShipmentsRepository extends JpaRepository<ShipmentOrder, Long> {
}
