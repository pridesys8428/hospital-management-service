package com.example.hospitalmanagement.hospital.management.services;

import com.example.hospitalmanagement.hospital.management.model.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PaymentService {
    public String initPayment() {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        map.add("store_id", "pride69477bf0ca0cc");
        map.add("store_passwd", "pride69477bf0ca0cc@ssl");

        map.add("total_amount", "10");
        map.add("currency", "BDT");
        map.add("tran_id", "TXN_" + System.currentTimeMillis());

        map.add("success_url", "http://localhost:8089/payment/success");
        map.add("fail_url", "http://localhost:8089/payment/fail");
        map.add("cancel_url", "http://localhost:8089/payment/fail");

        map.add("cus_name", "Test User");
        map.add("cus_email", "test@test.com");
        map.add("cus_phone", "01700000000");
        map.add("cus_add1", "Dhaka");
        map.add("cus_city", "Dhaka");
        map.add("cus_country", "Bangladesh");

        map.add("shipping_method", "NO");
        map.add("product_name", "Hospital Bill");
        map.add("product_category", "Healthcare");
        map.add("product_profile", "general");

        Map<String, Object> response = restTemplate.postForObject(
                "https://sandbox.sslcommerz.com/gwprocess/v4/api.php",
                map,
                Map.class
        );

        if (response == null || !"SUCCESS".equals(response.get("status"))) {
            throw new RuntimeException("SSLCommerz init failed: " + response);
        }

        return (String) response.get("GatewayPageURL");
    }

    public Map<String, Object> validatePayment(String valId) {
        String url =
                "https://sandbox.sslcommerz.com/validator/api/validationserverAPI.php"
                        + "?val_id=" + valId
                        + "&store_id=pride69477bf0ca0cc"
                        + "&store_passwd=pride69477bf0ca0cc@ssl"
                        + "&format=json";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response =
                restTemplate.getForEntity(url, Map.class);

        return response.getBody();
    }
}
