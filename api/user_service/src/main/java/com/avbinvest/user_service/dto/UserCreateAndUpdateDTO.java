package com.avbinvest.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateAndUpdateDTO {
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @Pattern(regexp = "\\+?\\d{10,15}", message = "Номер телефона должен содержать от 10 до 15 цифр и может начинаться с +")
    private String phoneNumber;

    @NotNull(message = "ID компании не должен быть null")
    private Long companyId = 0L;

}
