package com.avbinvest.user_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Company {
    private Long id;
    private String name;
    private Double budget;
}

