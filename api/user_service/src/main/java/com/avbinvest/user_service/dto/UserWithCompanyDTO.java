package com.avbinvest.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithCompanyDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private CompanyDTO company;

}
