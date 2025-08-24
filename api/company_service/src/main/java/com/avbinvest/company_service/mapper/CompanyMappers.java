package com.avbinvest.company_service.mapper;

import com.avbinvest.company_service.client.UserClient;
import com.avbinvest.company_service.dto.CompanyCreateAndUpdateDTO;
import com.avbinvest.company_service.dto.CompanyDTO;
import com.avbinvest.company_service.dto.CompanyWithEmployeesDTO;
import com.avbinvest.company_service.dto.UserDTO;
import com.avbinvest.company_service.model.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMappers {
    private final UserClient userClient;

    public CompanyMappers(UserClient userClient) {
        this.userClient = userClient;
    }

    public Company companyDtoToCompany(CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO) {
        Company company = new Company();
        company.setName(companyCreateAndUpdateDTO.getName());
        company.setEmployeeIds(companyCreateAndUpdateDTO.getEmployeeIds());
        company.setBudget(companyCreateAndUpdateDTO.getBudget());
        return company;
    }

    public CompanyDTO companyToCompanyDto(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setEmployees(company.getEmployeeIds());
        companyDTO.setBudget(company.getBudget());
        return companyDTO;
    }

    public CompanyWithEmployeesDTO companyToCompanyWithEmployeesDTO(Company company) {
        CompanyWithEmployeesDTO companyDTO = new CompanyWithEmployeesDTO();
        List<UserDTO> users = userClient.getUsersByIds(company.getEmployeeIds());
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setEmployees(users);
        companyDTO.setBudget(company.getBudget());
        return companyDTO;
    }

}
