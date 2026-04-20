package com.example.hospitalmanagement.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class AiQueryRequest {
    private String prompt;
}
