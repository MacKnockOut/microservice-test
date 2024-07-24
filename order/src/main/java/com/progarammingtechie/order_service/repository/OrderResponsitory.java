package com.progarammingtechie.order_service.repository;

import com.progarammingtechie.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderResponsitory extends JpaRepository<Order, Long> {
}
