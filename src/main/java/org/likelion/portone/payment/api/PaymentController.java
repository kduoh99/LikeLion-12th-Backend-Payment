package org.likelion.portone.payment.api;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.likelion.portone.payment.api.dto.request.PaymentCallbackReqDto;
import org.likelion.portone.payment.application.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    @Value("${imp.api.client-code}")
    private String clientCode;

    private final PaymentService paymentService;

    @GetMapping("/payment/{orderUid}")
    public String paymentPage(@PathVariable(name = "orderUid") String orderUid,
                              Model model) {

        model.addAttribute("clientCode", clientCode);
        model.addAttribute("paymentInfo", paymentService.getPaymentInfo(orderUid));

        return "payment";
    }

    @PostMapping("/payment")
    public ResponseEntity<IamportResponse<Payment>> validationPayment(@RequestBody PaymentCallbackReqDto reqDto) {
        return new ResponseEntity<>(paymentService.processPayment(reqDto), HttpStatus.OK);
    }

    @GetMapping("/success-payment")
    public String successPaymentPage() {
        return "success-payment";
    }

    @GetMapping("/fail-payment")
    public String failPaymentPage() {
        return "fail-payment";
    }
}
