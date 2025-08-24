package com.avbinvest.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateAndUpdateDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long companyId = 0L;
}
