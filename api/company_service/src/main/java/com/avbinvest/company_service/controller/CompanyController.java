package com.avbinvest.company_service.controller;

import com.avbinvest.company_service.dto.CompanyCreateAndUpdateDTO;
import com.avbinvest.company_service.dto.CompanyDTO;
import com.avbinvest.company_service.dto.CompanyWithEmployeesDTO;
import com.avbinvest.company_service.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Company", description = "API для управления компаниями")
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Operation(summary = "Создать компанию", description = "Создаёт новую компанию")
    @ApiResponse(responseCode = "201", description = "Компания успешно создана",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDTO.class)))
    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO) {
        logger.info("Входные данные для createCompany: {}", companyCreateAndUpdateDTO);
        CompanyDTO createdCompany = companyService.createCompany(companyCreateAndUpdateDTO);
        logger.info("Выходные данные из createCompany: {}", createdCompany);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить компанию по ID", description = "Возвращает компанию по идентификатору")
    @ApiResponse(responseCode = "200", description = "Компания найдена",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable("id") Long id) {
        logger.info("Входные данные для getCompanyById: id={}", id);
        CompanyDTO company = companyService.getCompanyById(id);
        logger.info("Выходные данные из getCompanyById: {}", company);
        return ResponseEntity.ok(company);
    }

    @Operation(summary = "Получить список всех компаний", description = "Возвращает список всех компаний")
    @ApiResponse(responseCode = "200", description = "Список компаний получен",
            content = @Content(mediaType = "application/json"))
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        logger.info("Входной вызов getAllCompanies");
        List<CompanyDTO> companies = companyService.getAllCompanies();
        logger.info("Выходные данные из getAllCompanies: возвращено {} компаний", companies);
        return ResponseEntity.ok(companies);
    }

    @Operation(summary = "Обновить компанию", description = "Обновляет данные существующей компании")
    @ApiResponse(responseCode = "200", description = "Компания успешно обновлена",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") Long id, @RequestBody CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO) {
        logger.info("Входные данные для updateCompany: id={}, DTO={}", id, companyCreateAndUpdateDTO);
        CompanyDTO updatedCompany = companyService.updateCompany(id, companyCreateAndUpdateDTO);
        logger.info("Выходные данные из updateCompany: {}", updatedCompany);
        return ResponseEntity.ok(updatedCompany);
    }

    @Operation(summary = "Удалить компанию", description = "Удаляет компанию по идентификатору")
    @ApiResponse(responseCode = "204", description = "Компания успешно удалена")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) {
        logger.info("Входные данные для deleteCompany: id={}", id);
        companyService.deleteCompany(id);
        logger.info("Компания с id {} удалена", id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить все компании с сотрудниками", description = "Возвращает список компаний с подробной информацией о сотрудниках")
    @ApiResponse(responseCode = "200", description = "Список компаний с сотрудниками успешно получен")
    @GetMapping("/with-employees")
    public ResponseEntity<List<CompanyWithEmployeesDTO>> getAllCompaniesWithEmployees() {
        logger.info("Запрос на получение всех компаний с сотрудниками");
        List<CompanyWithEmployeesDTO> companies = companyService.getAllCompaniesWithEmployees();
        logger.info("Результат запроса: найдено {} компаний", companies);
        return ResponseEntity.ok(companies);
    }

    @Operation(summary = "Получить компанию с сотрудниками по ID", description = "Возвращает информацию о компании с подробной информацией о сотрудниках по идентификатору компании")
    @ApiResponse(responseCode = "200", description = "Компания с сотрудниками успешно получена")
    @ApiResponse(responseCode = "404", description = "Компания с указанным ID не найдена")
    @GetMapping("/with-employees/{id}")
    public ResponseEntity<CompanyWithEmployeesDTO> getCompanyWithEmployeesById(@PathVariable("id") Long id) {
        logger.info("Запрос на получение компании с сотрудниками по id={}", id);
        CompanyWithEmployeesDTO company = companyService.getCompanyWithEmployeesById(id);
        if (company == null) {
            logger.warn("Компания с id={} не найдена", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Компания с id={} успешно получена", id);
        return ResponseEntity.ok(company);
    }
}



