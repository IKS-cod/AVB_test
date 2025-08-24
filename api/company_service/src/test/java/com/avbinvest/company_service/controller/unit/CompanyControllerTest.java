package com.avbinvest.company_service.controller.unit;

import com.avbinvest.company_service.controller.CompanyController;
import com.avbinvest.company_service.dto.CompanyCreateAndUpdateDTO;
import com.avbinvest.company_service.dto.CompanyDTO;
import com.avbinvest.company_service.dto.CompanyWithEmployeesDTO;
import com.avbinvest.company_service.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCompany_ShouldReturnCreatedCompany() {
        CompanyCreateAndUpdateDTO createDTO = new CompanyCreateAndUpdateDTO();
        createDTO.setName("Test Company");

        CompanyDTO createdCompany = new CompanyDTO();
        createdCompany.setId(1L);
        createdCompany.setName("Test Company");

        when(companyService.createCompany(createDTO)).thenReturn(createdCompany);

        ResponseEntity<CompanyDTO> response = companyController.createCompany(createDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(createdCompany, response.getBody());
        verify(companyService).createCompany(createDTO);
    }

    @Test
    public void getCompanyById_ShouldReturnCompany() {
        Long companyId = 1L;
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(companyId);
        companyDTO.setName("Company 1");

        when(companyService.getCompanyById(companyId)).thenReturn(companyDTO);

        ResponseEntity<CompanyDTO> response = companyController.getCompanyById(companyId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(companyDTO, response.getBody());
        verify(companyService).getCompanyById(companyId);
    }

    @Test
    public void getAllCompanies_ShouldReturnList() {
        CompanyDTO company1 = new CompanyDTO();
        company1.setId(1L);
        company1.setName("Company 1");
        CompanyDTO company2 = new CompanyDTO();
        company2.setId(2L);
        company2.setName("Company 2");

        when(companyService.getAllCompanies()).thenReturn(List.of(company1, company2));

        ResponseEntity<List<CompanyDTO>> response = companyController.getAllCompanies();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(companyService).getAllCompanies();
    }

    @Test
    public void updateCompany_ShouldReturnUpdatedCompany() {
        Long companyId = 1L;
        CompanyCreateAndUpdateDTO updateDTO = new CompanyCreateAndUpdateDTO();
        updateDTO.setName("Updated Company");

        CompanyDTO updatedCompany = new CompanyDTO();
        updatedCompany.setId(companyId);
        updatedCompany.setName("Updated Company");

        when(companyService.updateCompany(companyId, updateDTO)).thenReturn(updatedCompany);

        ResponseEntity<CompanyDTO> response = companyController.updateCompany(companyId, updateDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedCompany, response.getBody());
        verify(companyService).updateCompany(companyId, updateDTO);
    }

    @Test
    public void deleteCompany_ShouldReturnNoContent() {
        Long companyId = 1L;

        doNothing().when(companyService).deleteCompany(companyId);

        ResponseEntity<Void> response = companyController.deleteCompany(companyId);

        assertEquals(204, response.getStatusCodeValue());
        verify(companyService).deleteCompany(companyId);
    }

    @Test
    public void getAllCompaniesWithEmployees_ShouldReturnList() {
        CompanyWithEmployeesDTO companyWithEmployees = new CompanyWithEmployeesDTO();
        companyWithEmployees.setId(1L);
        companyWithEmployees.setName("Company with Employees");

        when(companyService.getAllCompaniesWithEmployees()).thenReturn(List.of(companyWithEmployees));

        ResponseEntity<List<CompanyWithEmployeesDTO>> response = companyController.getAllCompaniesWithEmployees();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(companyService).getAllCompaniesWithEmployees();
    }

    @Test
    public void getCompanyWithEmployeesById_Found_ShouldReturnCompany() {
        Long companyId = 1L;
        CompanyWithEmployeesDTO company = new CompanyWithEmployeesDTO();
        company.setId(companyId);
        company.setName("Company 1");

        when(companyService.getCompanyWithEmployeesById(companyId)).thenReturn(company);

        ResponseEntity<CompanyWithEmployeesDTO> response = companyController.getCompanyWithEmployeesById(companyId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(company, response.getBody());
        verify(companyService).getCompanyWithEmployeesById(companyId);
    }

    @Test
    public void getCompanyWithEmployeesById_NotFound_ShouldReturn404() {
        Long companyId = 1L;

        when(companyService.getCompanyWithEmployeesById(companyId)).thenReturn(null);

        ResponseEntity<CompanyWithEmployeesDTO> response = companyController.getCompanyWithEmployeesById(companyId);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(companyService).getCompanyWithEmployeesById(companyId);
    }
}
