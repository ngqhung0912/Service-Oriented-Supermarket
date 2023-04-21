package com.project.payment.repository;

import com.project.payment.entity.PaymentLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentLog, Long> {
  List<PaymentLog> getAllByUuid(String uuid);
}
