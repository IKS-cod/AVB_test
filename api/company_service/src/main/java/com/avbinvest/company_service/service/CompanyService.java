package com.avbinvest.company_service.service;

import com.avbinvest.company_service.dto.CompanyCreateAndUpdateDTO;
import com.avbinvest.company_service.dto.CompanyDTO;
import com.avbinvest.company_service.dto.CompanyWithEmployeesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    CompanyDTO createCompany(CompanyCreateAndUpdateDTO dto);
    CompanyDTO getCompanyById(Long id);
    Page<CompanyDTO> getAllCompanies(Pageable pageable);
    CompanyDTO updateCompany(Long id, CompanyCreateAndUpdateDTO dto);
    void deleteCompany(Long id);
    CompanyWithEmployeesDTO getCompanyWithEmployeesById(Long id, int page, int size);
    Page<CompanyWithEmployeesDTO> getAllCompaniesWithEmployees(Pageable pageable, int page, int size);
}


