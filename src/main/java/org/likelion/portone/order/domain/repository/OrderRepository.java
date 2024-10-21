package org.likelion.portone.order.domain.repository;

import org.likelion.portone.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o" +
            " LEFT JOIN FETCH o.payment p" +
            " LEFT JOIN FETCH o.member m" +
            " WHERE o.orderUid = :orderUid")
    Optional<Order> findOrderAndPaymentAndMember(String orderUid);

    @Query("SELECT o FROM Order o" +
            " LEFT JOIN FETCH o.payment p" +
            " WHERE o.orderUid = :orderUid")
    Optional<Order> findOrderAndPayment(String orderUid);
}
