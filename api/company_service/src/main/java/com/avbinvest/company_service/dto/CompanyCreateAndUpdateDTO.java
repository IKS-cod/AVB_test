package com.avbinvest.company_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateAndUpdateDTO {
    @NotBlank(message = "Имя компании не может быть пустым")
    private String name;
    @Min(value = 0, message = "Бюджет должен быть неотрицательным")
    private Long budget;
    @NotNull(message = "Список сотрудников не может быть null")
    private List<Long> employeeIds = new ArrayList<>(List.of(0L));

}
