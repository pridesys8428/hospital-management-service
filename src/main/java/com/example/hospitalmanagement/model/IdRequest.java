package com.example.hospitalmanagement.model;

import jakarta.persistence.Entity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class IdRequest {
    // Getter and Setter (or use Lombok @Data)
    private Long id;

}
