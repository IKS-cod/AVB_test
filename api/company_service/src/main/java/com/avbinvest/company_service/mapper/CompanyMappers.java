package com.avbinvest.company_service.mapper;

import com.avbinvest.company_service.client.UserClient;
import com.avbinvest.company_service.dto.CompanyCreateAndUpdateDTO;
import com.avbinvest.company_service.dto.CompanyDTO;
import com.avbinvest.company_service.dto.CompanyWithEmployeesDTO;
import com.avbinvest.company_service.dto.UserDTO;
import com.avbinvest.company_service.model.Company;
import com.avbinvest.company_service.model.UserPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CompanyMappers {
    private final UserClient userClient;

    public void companyDtoToCompany(CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO, Company company) {
        company.setName(companyCreateAndUpdateDTO.getName());
        company.setEmployeeIds(companyCreateAndUpdateDTO.getEmployeeIds());
        company.setBudget(companyCreateAndUpdateDTO.getBudget());
    }

    public CompanyDTO companyToCompanyDto(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setEmployees(company.getEmployeeIds());
        companyDTO.setBudget(company.getBudget());
        return companyDTO;
    }

    public CompanyWithEmployeesDTO companyToCompanyWithEmployeesDTO(Company company, int page, int size) {
        CompanyWithEmployeesDTO companyDTO = new CompanyWithEmployeesDTO();
        UserPage userPage = userClient.getUsersByIds(company.getEmployeeIds(), page, size);
        List<UserDTO> users = userPage.getContent();

        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setEmployees(users);
        companyDTO.setBudget(company.getBudget());

        return companyDTO;
    }

    public List<CompanyWithEmployeesDTO> companiesListToCompanyWithEmployeesDTOList(List<Company> companies, int page, int size) {
        if (companies == null) {
            return Collections.emptyList();
        }
        return companies.stream()
                .map(company -> companyToCompanyWithEmployeesDTO(company, page, size))
                .collect(Collectors.toList());
    }


    public List<CompanyDTO> companiesListToCompanyDtoList(List<Company> companies) {
        if (companies == null) {
            return Collections.emptyList();
        }
        return companies.stream()
                .map(this::companyToCompanyDto)
                .collect(Collectors.toList());
    }



}
