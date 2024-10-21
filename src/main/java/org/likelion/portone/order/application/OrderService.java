package org.likelion.portone.order.application;

import lombok.RequiredArgsConstructor;
import org.likelion.portone.member.domain.Member;
import org.likelion.portone.order.domain.Order;
import org.likelion.portone.order.domain.repository.OrderRepository;
import org.likelion.portone.payment.domain.Payment;
import org.likelion.portone.payment.domain.Status;
import org.likelion.portone.payment.domain.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Order createOrder(Member member) {
        // 임시 결제내역 생성
        Payment payment = Payment.builder()
                .price(1000L)
                .status(Status.PENDING)
                .build();

        paymentRepository.save(payment);

        // 주문 생성
        Order order = Order.builder()
                .productName("MacBook")
                .price(1000L)
                .orderUid(UUID.randomUUID().toString())
                .member(member)
                .payment(payment)
                .build();

        return orderRepository.save(order);
    }
}
