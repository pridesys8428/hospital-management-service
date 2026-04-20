package com.example.hospitalmanagement.controllers;

import com.example.hospitalmanagement.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/initiate-payment")
    public ResponseEntity<Void> initiatePayment() {
        System.out.println("Started payment");
        String gatewayUrl = paymentService.initPayment();
        return ResponseEntity.status(302)
                .header("Location", gatewayUrl)
                .build();
    }

    @PostMapping("/success")
    public ResponseEntity<?> paymentSuccess(@RequestParam Map<String, String> requestParams) {

        String valId = requestParams.get("val_id");

        if (valId == null) {
            return ResponseEntity.badRequest().body("Missing val_id");
        }

        Map<String, Object> validationResponse =
                paymentService.validatePayment(valId);

        if (validationResponse == null) {
            return ResponseEntity.status(500).body("Validation failed");
        }

        String status = (String) validationResponse.get("status");

        if ("VALID".equals(status) || "VALIDATED".equals(status)) {

            String tranId = (String) validationResponse.get("tran_id");
            String amount = (String) validationResponse.get("amount");

            // ✅ SAVE TO DATABASE (example)
            // orderService.markPaid(tranId, amount);

            return ResponseEntity.ok(
                    "Payment Successful! Transaction ID: " + tranId
            );
        }

        return ResponseEntity.status(400)
                .body("Payment validation failed. Status: " + status);
    }


    @PostMapping("/fail")
    public String paymentFail() {
        return "Payment Failed";
    }
}
