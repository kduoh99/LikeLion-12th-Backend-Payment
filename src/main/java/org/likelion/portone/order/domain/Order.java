package org.likelion.portone.order.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.portone.member.domain.Member;
import org.likelion.portone.payment.domain.Payment;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Long price;

    private String orderUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Builder
    private Order(String productName, Long price, String orderUid, Member member, Payment payment) {
        this.productName = productName;
        this.price = price;
        this.orderUid = orderUid;
        this.member = member;
        this.payment = payment;
    }
}
