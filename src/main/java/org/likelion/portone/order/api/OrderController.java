package org.likelion.portone.order.api;

import lombok.RequiredArgsConstructor;
import org.likelion.portone.member.application.MemberService;
import org.likelion.portone.member.domain.Member;
import org.likelion.portone.order.application.OrderService;
import org.likelion.portone.order.domain.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private static final String ENCODED_ORDER_SUCCESS = URLEncoder.encode("주문 성공", StandardCharsets.UTF_8);

    private final MemberService memberService;
    private final OrderService orderService;

    @GetMapping("/order")
    public String orderPage(@RequestParam(name = "message", required = false) String message,
                            @RequestParam(name = "orderUid", required = false) String orderUid,
                            Model model) {

        model.addAttribute("message", message);
        model.addAttribute("orderUid", orderUid);

        return "order";
    }

    @PostMapping("/order")
    public String processOrder() {
        Member member = memberService.signUp();
        Order order = orderService.createOrder(member);
        return "redirect:/order?message=" + ENCODED_ORDER_SUCCESS + "&orderUid=" + order.getOrderUid();
    }
}
