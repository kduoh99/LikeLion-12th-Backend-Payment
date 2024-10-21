package org.likelion.portone.payment.api.dto.response;

import lombok.Builder;
import org.likelion.portone.order.domain.Order;

@Builder
public record PaymentResDto(
        String customerName,
        String customerEmail,
        String customerAddress,
        String productName,
        Long price,
        String orderUid
) {
    public static PaymentResDto from(Order order) {
        return PaymentResDto.builder()
                .customerName(order.getMember().getName())
                .customerEmail(order.getMember().getEmail())
                .customerAddress(order.getMember().getAddress())
                .productName(order.getProductName())
                .price(order.getPayment().getPrice())
                .orderUid(order.getOrderUid())
                .build();
    }
}
