package com.example.hospitalmanagement.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class PaymentRequest {
    private String total_amount;
    private String currency = "BDT";
    private String tran_id; // Unique ID from your DB
    private String success_url = "http://localhost:8080/payment/success";
    private String fail_url = "http://localhost:8080/payment/fail";
    private String cancel_url = "http://localhost:8080/payment/cancel";
}
