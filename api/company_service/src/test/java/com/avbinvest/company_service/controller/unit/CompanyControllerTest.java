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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompanyControllerTest {

//    @Mock
//    private CompanyService companyService;
//
//    @InjectMocks
//    private CompanyController companyController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void createCompany_ShouldReturnCreatedCompany() {
//        CompanyCreateAndUpdateDTO createDTO = new CompanyCreateAndUpdateDTO();
//        createDTO.setName("Test Company");
//
//        CompanyDTO createdCompany = new CompanyDTO();
//        createdCompany.setId(1L);
//        createdCompany.setName("Test Company");
//
//        when(companyService.createCompany(createDTO)).thenReturn(createdCompany);
//
//        CompanyDTO response = companyController.createCompany(createDTO);
//
//        assertEquals(createdCompany, response);
//        verify(companyService).createCompany(createDTO);
//    }
//
//    @Test
//    public void getCompanyById_ShouldReturnCompany() {
//        Long companyId = 1L;
//        CompanyDTO companyDTO = new CompanyDTO();
//        companyDTO.setId(companyId);
//        companyDTO.setName("Company 1");
//
//        when(companyService.getCompanyById(companyId)).thenReturn(companyDTO);
//
//        CompanyDTO response = companyController.getCompanyById(companyId);
//
//        assertEquals(companyDTO, response);
//        verify(companyService).getCompanyById(companyId);
//    }
//
////    @Test
////    public void getAllCompanies_ShouldReturnList() {
////        CompanyDTO company1 = new CompanyDTO();
////        company1.setId(1L);
////        company1.setName("Company 1");
////        CompanyDTO company2 = new CompanyDTO();
////        company2.setId(2L);
////        company2.setName("Company 2");
////
////        when(companyService.getAllCompanies()).thenReturn(List.of(company1, company2));
////
////        List<CompanyDTO> response = companyController.getAllCompanies();
////
////        assertNotNull(response);
////        assertEquals(2, response.size());
////        verify(companyService).getAllCompanies();
////    }
//
//    @Test
//    public void updateCompany_ShouldReturnUpdatedCompany() {
//        Long companyId = 1L;
//        CompanyCreateAndUpdateDTO updateDTO = new CompanyCreateAndUpdateDTO();
//        updateDTO.setName("Updated Company");
//
//        CompanyDTO updatedCompany = new CompanyDTO();
//        updatedCompany.setId(companyId);
//        updatedCompany.setName("Updated Company");
//
//        when(companyService.updateCompany(companyId, updateDTO)).thenReturn(updatedCompany);
//
//        CompanyDTO response = companyController.updateCompany(companyId, updateDTO);
//
//        assertEquals(updatedCompany, response);
//        verify(companyService).updateCompany(companyId, updateDTO);
//    }
//
//    @Test
//    public void deleteCompany_ShouldReturnNoContent() {
//        Long companyId = 1L;
//        doNothing().when(companyService).deleteCompany(companyId);
//        verify(companyService).deleteCompany(companyId);
//    }
//
////    @Test
////    public void getAllCompaniesWithEmployees_ShouldReturnList() {
////        CompanyWithEmployeesDTO companyWithEmployees = new CompanyWithEmployeesDTO();
////        companyWithEmployees.setId(1L);
////        companyWithEmployees.setName("Company with Employees");
////
////        when(companyService.getAllCompaniesWithEmployees()).thenReturn(List.of(companyWithEmployees));
////
////        List<CompanyWithEmployeesDTO> response = companyController.getAllCompaniesWithEmployees();
////
////        assertNotNull(response);
////        assertEquals(1, response.size());
////        verify(companyService).getAllCompaniesWithEmployees();
////    }
////
////    @Test
////    public void getCompanyWithEmployeesById_Found_ShouldReturnCompany() {
////        Long companyId = 1L;
////        CompanyWithEmployeesDTO company = new CompanyWithEmployeesDTO();
////        company.setId(companyId);
////        company.setName("Company 1");
////
////        when(companyService.getCompanyWithEmployeesById(companyId)).thenReturn(company);
////
////        CompanyWithEmployeesDTO response = companyController.getCompanyWithEmployeesById(companyId);
////        assertEquals(company, response);
////        verify(companyService).getCompanyWithEmployeesById(companyId);
////    }
////
////    @Test
////    public void getCompanyWithEmployeesById_NotFound_ShouldReturn404() {
////        Long companyId = 1L;
////
////        when(companyService.getCompanyWithEmployeesById(companyId)).thenReturn(null);
////
////        CompanyWithEmployeesDTO response = companyController.getCompanyWithEmployeesById(companyId);
////
////        assertNull(response);
////        verify(companyService).getCompanyWithEmployeesById(companyId);
////    }
}
