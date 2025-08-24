package com.avbinvest.company_service.service;

import com.avbinvest.company_service.dto.CompanyCreateAndUpdateDTO;
import com.avbinvest.company_service.dto.CompanyDTO;
import com.avbinvest.company_service.dto.CompanyWithEmployeesDTO;
import com.avbinvest.company_service.exceptions.CompanyNotFoundException;
import com.avbinvest.company_service.mapper.CompanyMappers;
import com.avbinvest.company_service.model.Company;
import com.avbinvest.company_service.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    private final CompanyRepository companyRepository;
    private final CompanyMappers companyMappers;

    public CompanyService(CompanyRepository companyRepository, CompanyMappers companyMappers) {
        this.companyRepository = companyRepository;
        this.companyMappers = companyMappers;
    }

    public CompanyDTO createCompany(CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO) {
        logger.info("Входные данные для createCompany: {}", companyCreateAndUpdateDTO);
        Company company = companyMappers.companyDtoToCompany(companyCreateAndUpdateDTO);
        logger.debug("Преобразован DTO в сущность Company: {}", company);
        company = companyRepository.save(company);
        logger.debug("Сохранена сущность Company: {}", company);
        CompanyDTO result = companyMappers.companyToCompanyDto(company);
        logger.info("Выходные данные из createCompany: {}", result);
        return result;
    }

    public CompanyDTO getCompanyById(Long id) {
        logger.info("Входные данные для getCompanyById: id={}", id);
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> {
                    logger.debug("Компания с id {} не найдена", id);
                    return new CompanyNotFoundException(id);
                });
        logger.debug("Найдена сущность Company: {}", company);
        CompanyDTO result = companyMappers.companyToCompanyDto(company);
        logger.info("Выходные данные из getCompanyById: {}", result);
        return result;
    }

    public List<CompanyDTO> getAllCompanies() {
        logger.info("Входные данные для getAllCompanies");
        List<Company> companies = companyRepository.findAll();
        logger.debug("Найденные компании: {}", companies);
        List<CompanyDTO> result = companies.stream()
                .map(companyMappers::companyToCompanyDto)
                .collect(Collectors.toList());
        logger.info("Выходные данные из getAllCompanies: возвращено компаний {}", result);
        return result;
    }


    public CompanyDTO updateCompany(Long id, CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO) {
        logger.info("Входные данные для updateCompany: id={}, DTO={}", id, companyCreateAndUpdateDTO);
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> {
                    logger.debug("Компания с id {} не найдена", id);
                    return new CompanyNotFoundException(id);
                });
        company.setName(companyCreateAndUpdateDTO.getName());
        company.setBudget(companyCreateAndUpdateDTO.getBudget());
        company.setEmployeeIds(companyCreateAndUpdateDTO.getEmployeeIds());
        logger.debug("Обновлены поля сущности Company: {}", company);
        company = companyRepository.save(company);
        logger.debug("Сохранена обновленная сущность Company: {}", company);
        CompanyDTO result = companyMappers.companyToCompanyDto(company);
        logger.info("Выходные данные из updateCompany: {}", result);
        return result;
    }

    public void deleteCompany(Long id) {
        logger.info("Входные данные для deleteCompany: id={}", id);
        companyRepository.findById(id)
                .orElseThrow(() -> {
                    logger.debug("Компания с id {} не найдена", id);
                    return new CompanyNotFoundException(id);
                });
        companyRepository.deleteById(id);
        logger.info("Компания с id {} удалена", id);
    }

    public CompanyWithEmployeesDTO getCompanyWithEmployeesById(Long id) {
        logger.info("Входные данные для getCompanyWithEmployeesById: id={}", id);
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> {
                    logger.debug("Компания с id {} не найдена", id);
                    return new CompanyNotFoundException(id);
                });
        logger.debug("Найдена сущность Company: {}", company);
        CompanyWithEmployeesDTO result = companyMappers.companyToCompanyWithEmployeesDTO(company);
        logger.info("Выходные данные из getCompanyWithEmployeesById: {}", result);
        return result;
    }

    public List<CompanyWithEmployeesDTO> getAllCompaniesWithEmployees() {
        logger.info("Входные данные для getAllCompaniesWithEmployees");
        List<Company> companies = companyRepository.findAll();
        logger.debug("Найден список компаний: {}", companies);
        List<CompanyWithEmployeesDTO> result = companies.stream()
                .map(companyMappers::companyToCompanyWithEmployeesDTO)
                .collect(Collectors.toList());
        logger.info("Выходные данные из getAllCompaniesWithEmployees: возвращено {} компаний", result);
        return result;
    }
}


