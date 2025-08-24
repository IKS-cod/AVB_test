package com.avbinvest.company_service.service.unit;

import com.avbinvest.company_service.dto.CompanyCreateAndUpdateDTO;
import com.avbinvest.company_service.dto.CompanyDTO;
import com.avbinvest.company_service.dto.CompanyWithEmployeesDTO;
import com.avbinvest.company_service.exceptions.CompanyNotFoundException;
import com.avbinvest.company_service.mapper.CompanyMappers;
import com.avbinvest.company_service.model.Company;
import com.avbinvest.company_service.repository.CompanyRepository;
import com.avbinvest.company_service.service.CompanyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMappers companyMappers;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCompany_ShouldReturnCreatedCompanyDTO() {
        CompanyCreateAndUpdateDTO createDTO = new CompanyCreateAndUpdateDTO();
        createDTO.setName("Test Company");

        Company companyEntity = new Company();
        companyEntity.setName("Test Company");

        Company savedCompany = new Company();
        savedCompany.setId(1L);
        savedCompany.setName("Test Company");

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(1L);
        companyDTO.setName("Test Company");

        when(companyMappers.companyDtoToCompany(createDTO)).thenReturn(companyEntity);
        when(companyRepository.save(companyEntity)).thenReturn(savedCompany);
        when(companyMappers.companyToCompanyDto(savedCompany)).thenReturn(companyDTO);

        CompanyDTO result = companyService.createCompany(createDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Company", result.getName());

        verify(companyMappers).companyDtoToCompany(createDTO);
        verify(companyRepository).save(companyEntity);
        verify(companyMappers).companyToCompanyDto(savedCompany);
    }

    @Test
    public void getCompanyById_WhenCompanyExists_ShouldReturnCompanyDTO() {
        Long companyId = 1L;
        Company company = new Company();
        company.setId(companyId);
        company.setName("Company 1");

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(companyId);
        companyDTO.setName("Company 1");

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        when(companyMappers.companyToCompanyDto(company)).thenReturn(companyDTO);

        CompanyDTO result = companyService.getCompanyById(companyId);

        assertNotNull(result);
        assertEquals(companyId, result.getId());
        assertEquals("Company 1", result.getName());

        verify(companyRepository).findById(companyId);
        verify(companyMappers).companyToCompanyDto(company);
    }

    @Test
    public void getCompanyById_WhenCompanyNotFound_ShouldThrowException() {
        Long companyId = 1L;

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> companyService.getCompanyById(companyId));

        verify(companyRepository).findById(companyId);
        verifyNoMoreInteractions(companyMappers);
    }

    @Test
    public void getAllCompanies_ShouldReturnListOfCompanyDTO() {
        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("Company 1");
        Company company2 = new Company();
        company2.setId(2L);
        company2.setName("Company 2");

        CompanyDTO companyDTO1 = new CompanyDTO();
        companyDTO1.setId(1L);
        companyDTO1.setName("Company 1");
        CompanyDTO companyDTO2 = new CompanyDTO();
        companyDTO2.setId(2L);
        companyDTO2.setName("Company 2");

        when(companyRepository.findAll()).thenReturn(List.of(company1, company2));
        when(companyMappers.companyToCompanyDto(company1)).thenReturn(companyDTO1);
        when(companyMappers.companyToCompanyDto(company2)).thenReturn(companyDTO2);

        List<CompanyDTO> result = companyService.getAllCompanies();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Company 1", result.get(0).getName());
        assertEquals("Company 2", result.get(1).getName());

        verify(companyRepository).findAll();
        verify(companyMappers, times(2)).companyToCompanyDto(any(Company.class));
    }

    @Test
    public void updateCompany_WhenCompanyExists_ShouldReturnUpdatedCompanyDTO() {
        Long companyId = 1L;
        CompanyCreateAndUpdateDTO updateDTO = new CompanyCreateAndUpdateDTO();
        updateDTO.setName("Updated Company");
        updateDTO.setBudget(1000L);
        updateDTO.setEmployeeIds(List.of(1L, 2L));

        Company company = new Company();
        company.setId(companyId);
        company.setName("Old Company");

        Company savedCompany = new Company();
        savedCompany.setId(companyId);
        savedCompany.setName("Updated Company");
        savedCompany.setBudget(1000L);
        savedCompany.setEmployeeIds(List.of(1L, 2L));

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(companyId);
        companyDTO.setName("Updated Company");

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(savedCompany);
        when(companyMappers.companyToCompanyDto(savedCompany)).thenReturn(companyDTO);

        CompanyDTO result = companyService.updateCompany(companyId, updateDTO);

        assertNotNull(result);
        assertEquals(companyId, result.getId());
        assertEquals("Updated Company", result.getName());

        verify(companyRepository).findById(companyId);
        verify(companyRepository).save(company);
        verify(companyMappers).companyToCompanyDto(savedCompany);
    }

    @Test
    public void updateCompany_WhenCompanyNotFound_ShouldThrowException() {
        Long companyId = 1L;
        CompanyCreateAndUpdateDTO updateDTO = new CompanyCreateAndUpdateDTO();

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> companyService.updateCompany(companyId, updateDTO));

        verify(companyRepository).findById(companyId);
        verifyNoMoreInteractions(companyMappers);
    }

    @Test
    public void deleteCompany_WhenCompanyExists_ShouldDeleteCompany() {
        Long companyId = 1L;
        Company company = new Company();
        company.setId(companyId);

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        doNothing().when(companyRepository).deleteById(companyId);

        companyService.deleteCompany(companyId);

        verify(companyRepository).findById(companyId);
        verify(companyRepository).deleteById(companyId);
    }

    @Test
    public void deleteCompany_WhenCompanyNotFound_ShouldThrowException() {
        Long companyId = 1L;

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> companyService.deleteCompany(companyId));

        verify(companyRepository).findById(companyId);
        verifyNoMoreInteractions(companyRepository);
    }

    @Test
    public void getCompanyWithEmployeesById_WhenFound_ShouldReturnDTO() {
        Long companyId = 1L;
        Company company = new Company();
        company.setId(companyId);
        company.setName("Test Company");

        CompanyWithEmployeesDTO companyWithEmployeesDTO = new CompanyWithEmployeesDTO();
        companyWithEmployeesDTO.setId(companyId);
        companyWithEmployeesDTO.setName("Test Company");

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        when(companyMappers.companyToCompanyWithEmployeesDTO(company)).thenReturn(companyWithEmployeesDTO);

        CompanyWithEmployeesDTO result = companyService.getCompanyWithEmployeesById(companyId);

        assertNotNull(result);
        assertEquals(companyId, result.getId());
        assertEquals("Test Company", result.getName());

        verify(companyRepository).findById(companyId);
        verify(companyMappers).companyToCompanyWithEmployeesDTO(company);
    }

    @Test
    public void getCompanyWithEmployeesById_WhenNotFound_ShouldThrowException() {
        Long companyId = 1L;

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> companyService.getCompanyWithEmployeesById(companyId));

        verify(companyRepository).findById(companyId);
        verifyNoMoreInteractions(companyMappers);
    }

    @Test
    public void getAllCompaniesWithEmployees_ShouldReturnList() {
        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("Company 1");
        Company company2 = new Company();
        company2.setId(2L);
        company2.setName("Company 2");

        CompanyWithEmployeesDTO dto1 = new CompanyWithEmployeesDTO();
        dto1.setId(1L);
        dto1.setName("Company 1");
        CompanyWithEmployeesDTO dto2 = new CompanyWithEmployeesDTO();
        dto2.setId(2L);
        dto2.setName("Company 2");

        when(companyRepository.findAll()).thenReturn(List.of(company1, company2));
        when(companyMappers.companyToCompanyWithEmployeesDTO(company1)).thenReturn(dto1);
        when(companyMappers.companyToCompanyWithEmployeesDTO(company2)).thenReturn(dto2);

        List<CompanyWithEmployeesDTO> result = companyService.getAllCompaniesWithEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(companyRepository).findAll();
        verify(companyMappers, times(2)).companyToCompanyWithEmployeesDTO(any(Company.class));
    }
}
