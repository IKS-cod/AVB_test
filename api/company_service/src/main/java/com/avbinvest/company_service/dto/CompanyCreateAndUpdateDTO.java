package com.avbinvest.company_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateAndUpdateDTO {
    private String name;
    private Long budget;
    private List<Long> employeeIds = new ArrayList<>(List.of(0L));
}
