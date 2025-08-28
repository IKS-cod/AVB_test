package com.avbinvest.company_service.service;

import com.avbinvest.company_service.dto.CompanyCreateAndUpdateDTO;
import com.avbinvest.company_service.dto.CompanyDTO;
import com.avbinvest.company_service.dto.CompanyWithEmployeesDTO;
import com.avbinvest.company_service.exceptions.CompanyNotFoundException;
import com.avbinvest.company_service.mapper.CompanyMappers;
import com.avbinvest.company_service.model.Company;
import com.avbinvest.company_service.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMappers companyMappers;

    @Override
    public CompanyDTO createCompany(CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO) {
        Company company = new Company();
        companyMappers.companyDtoToCompany(companyCreateAndUpdateDTO, company);
        company = companyRepository.save(company);
        CompanyDTO result = companyMappers.companyToCompanyDto(company);
        log.info("Выходные данные из company_service в методе createCompany: {}", result);
        return result;
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Company company = findCompanyOrThrow(id);
        CompanyDTO result = companyMappers.companyToCompanyDto(company);
        log.info("Выходные данные из company_service в методе getCompanyById: {}", result);
        return result;
    }

    @Override
    public Page<CompanyDTO> getAllCompanies(Pageable pageable) {
        Page<Company> companiesPage = companyRepository.findAll(pageable);
        List<CompanyDTO> dtoList = companyMappers.companiesListToCompanyDtoList(companiesPage.getContent());

        Page<CompanyDTO> resultPage = new PageImpl<>(dtoList, pageable, companiesPage.getTotalElements());

        log.info("Выполнен запрос company_service в методе getAllCompanies с пагинацией {}, возвращено компаний: {}", pageable, resultPage.getContent());

        return resultPage;
    }


    @Override
    public CompanyDTO updateCompany(Long id, CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO) {
        Company company = findCompanyOrThrow(id);
        companyMappers.companyDtoToCompany(companyCreateAndUpdateDTO, company);
        company = companyRepository.save(company);
        CompanyDTO result = companyMappers.companyToCompanyDto(company);
        log.info("Выходные данные из company_service в методе updateCompany: {}", result);
        return result;
    }

    @Override
    public void deleteCompany(Long id) {
        findCompanyOrThrow(id);
        companyRepository.deleteById(id);
        log.info("Компания с id {} удалена в company_service в методе deleteCompany", id);
    }

    @Override
    public CompanyWithEmployeesDTO getCompanyWithEmployeesById(Long id, int page, int size) {
        Company company = findCompanyOrThrow(id);
        CompanyWithEmployeesDTO result = companyMappers.companyToCompanyWithEmployeesDTO(company, page, size);
        log.info("Выходные данные из company_service в методе getCompanyWithEmployeesById: {}", result);
        return result;
    }

    @Override
    public Page<CompanyWithEmployeesDTO> getAllCompaniesWithEmployees(Pageable pageable, int page, int size) {
        Page<Company> companyPage = companyRepository.findAll(pageable);

        List<CompanyWithEmployeesDTO> result = companyMappers
                .companiesListToCompanyWithEmployeesDTOList(companyPage.getContent(), page, size);

        Page<CompanyWithEmployeesDTO> dtoPage = new PageImpl<>(result, pageable, companyPage.getTotalElements());
        log.info("Выходные данные из company_service в методе getAllCompaniesWithEmployees: возвращено компаний: {}", dtoPage.getContent());
        return dtoPage;
    }

    private Company findCompanyOrThrow(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }


}
