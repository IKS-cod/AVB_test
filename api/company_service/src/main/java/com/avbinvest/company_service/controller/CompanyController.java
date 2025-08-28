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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Company", description = "API для управления компаниями")
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "Создать компанию", description = "Создаёт новую компанию")
    @ApiResponse(responseCode = "201", description = "Компания успешно создана",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDTO.class)))
    @PostMapping
    public CompanyDTO createCompany(@Valid @RequestBody CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO) {
        log.info("Входные данные для createCompany: {}", companyCreateAndUpdateDTO);
        return companyService.createCompany(companyCreateAndUpdateDTO);
    }

    @Operation(summary = "Получить компанию по ID", description = "Возвращает компанию по идентификатору")
    @ApiResponse(responseCode = "200", description = "Компания найдена",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDTO.class)))
    @GetMapping("/{id}")
    public CompanyDTO getCompanyById(@PathVariable("id") Long id) {
        log.info("Входные данные для getCompanyById: id={}", id);
        return companyService.getCompanyById(id);
    }

    @GetMapping
    public Page<CompanyDTO> getAllCompanies(Pageable pageable) {
        log.info("Входной вызов getAllCompanies с пагинацией: {}", pageable);
        return companyService.getAllCompanies(pageable);
    }

    @Operation(summary = "Обновить компанию", description = "Обновляет данные существующей компании")
    @ApiResponse(responseCode = "200", description = "Компания успешно обновлена",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDTO.class)))
    @PutMapping("/{id}")
    public CompanyDTO updateCompany(@PathVariable("id") Long id, @Valid @RequestBody CompanyCreateAndUpdateDTO companyCreateAndUpdateDTO) {
        log.info("Входные данные для updateCompany: id={}, DTO={}", id, companyCreateAndUpdateDTO);
        return companyService.updateCompany(id, companyCreateAndUpdateDTO);
    }

    @Operation(summary = "Удалить компанию", description = "Удаляет компанию по идентификатору")
    @ApiResponse(responseCode = "204", description = "Компания успешно удалена")
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") Long id) {
        log.info("Входные данные для deleteCompany: id={}", id);
        companyService.deleteCompany(id);
    }

    @Operation(summary = "Получить все компании с сотрудниками", description = "Возвращает список компаний с подробной информацией о сотрудниках")
    @ApiResponse(responseCode = "200", description = "Список компаний с сотрудниками успешно получен")
    @GetMapping("/with-employees")
    public Page<CompanyWithEmployeesDTO> getAllCompaniesWithEmployees(Pageable pageable,
                                                                      @RequestParam(name = "employeesPage", defaultValue = "0") int employeesPage,
                                                                      @RequestParam(name = "employeesSize", defaultValue = "10") int employeesSize) {
        log.info("Запрос на получение всех компаний с сотрудниками. Компании - page: {}, size: {}; Сотрудники - page: {}, size: {}",
                pageable.getPageNumber(), pageable.getPageSize(), employeesPage, employeesSize);
        return companyService.getAllCompaniesWithEmployees(pageable, employeesPage, employeesSize);
    }

    @Operation(summary = "Получить компанию с сотрудниками по ID", description = "Возвращает информацию о компании с подробной информацией о сотрудниках по идентификатору компании")
    @ApiResponse(responseCode = "200", description = "Компания с сотрудниками успешно получена")
    @ApiResponse(responseCode = "404", description = "Компания с указанным ID не найдена")
    @GetMapping("/with-employees/{id}")
    public CompanyWithEmployeesDTO getCompanyWithEmployeesById(@PathVariable("id") Long id,
                                                               @RequestParam(name = "employeesPage", defaultValue = "0") int employeesPage,
                                                               @RequestParam(name = "employeesSize", defaultValue = "10") int employeesSize) {
        log.info("Запрос на получение компании с сотрудниками по id={}. Параметры пагинации сотрудников: page={}, size={}",
                id, employeesPage, employeesSize);
        return companyService.getCompanyWithEmployeesById(id, employeesPage, employeesSize);
    }

}