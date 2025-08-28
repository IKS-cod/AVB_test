package com.avbinvest.company_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyWithEmployeesDTO {
    private Long id;
    private String name;
    private Long budget;
    private List<UserDTO> employees;
}
