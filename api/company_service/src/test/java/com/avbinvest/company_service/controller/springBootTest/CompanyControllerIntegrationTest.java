package com.avbinvest.company_service.controller.springBootTest;

import com.avbinvest.company_service.dto.CompanyCreateAndUpdateDTO;
import com.avbinvest.company_service.model.Company;
import com.avbinvest.company_service.repository.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerIntegrationTest {

//    @Container
//    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15-alpine")
//            .withDatabaseName("test_db")
//            .withUsername("sa")
//            .withPassword("sa");
//
//    @DynamicPropertySource
//    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
//        registry.add("spring.datasource.username", postgresContainer::getUsername);
//        registry.add("spring.datasource.password", postgresContainer::getPassword);
//    }
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private CompanyRepository companyRepository;
//
//    @BeforeEach
//    void cleanUp() {
//        companyRepository.deleteAll();
//    }
//
//    @Test
//    void createCompany_ReturnsCreatedCompany() throws Exception {
//        CompanyCreateAndUpdateDTO dto = new CompanyCreateAndUpdateDTO("TestCompany", 100000L, List.of(1L, 2L));
//        String json = objectMapper.writeValueAsString(dto);
//
//        mockMvc.perform(post("/companies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("TestCompany"))
//                .andExpect(jsonPath("$.budget").value(100000));
//    }
//
//    @Test
//    void getCompanyById_ReturnsCompany() throws Exception {
//        Company company = new Company();
//        company.setName("SampleCompany");
//        company.setBudget(50000L);
//        company.setEmployeeIds(List.of(3L, 4L));
//        company = companyRepository.save(company);
//
//        mockMvc.perform(get("/companies/{id}", company.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(company.getId()))
//                .andExpect(jsonPath("$.name").value("SampleCompany"))
//                .andExpect(jsonPath("$.budget").value(50000));
//    }
//
//    @Test
//    void getAllCompanies_ReturnsList() throws Exception {
//        Company company1 = new Company();
//        company1.setBudget(1000L);
//        company1.setId(null);
//        company1.setName("CompanyA");
//        company1.setEmployeeIds(List.of(1L));
//        Company company2 = new Company();
//        company1.setBudget(2000L);
//        company1.setId(null);
//        company1.setName("CompanyB");
//        company1.setEmployeeIds(List.of(2L));
//        companyRepository.save(company1);
//        companyRepository.save(company2);
//
//        mockMvc.perform(get("/companies"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2));
//    }
//
//    @Test
//    void updateCompany_ReturnsUpdatedCompany() throws Exception {
//        Company company = new Company();
//        company.setName("CompanyToUpdate");
//        company.setBudget(30000L);
//        company.setEmployeeIds(List.of(5L));
//        company = companyRepository.save(company);
//
//        CompanyCreateAndUpdateDTO updateDto = new CompanyCreateAndUpdateDTO("UpdatedCompany", 40000L, List.of(6L, 7L));
//        String json = objectMapper.writeValueAsString(updateDto);
//
//        mockMvc.perform(put("/companies/{id}", company.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("UpdatedCompany"))
//                .andExpect(jsonPath("$.budget").value(40000))
//                .andExpect(jsonPath("$.employees.length()").value(2));
//    }
//
//    @Test
//    void deleteCompany_ReturnsNoContent() throws Exception {
//        Company company = new Company();
//        company.setName("CompanyToDelete");
//        company.setBudget(70000L);
//        company.setEmployeeIds(List.of(8L, 9L));
//        company = companyRepository.save(company);
//
//        mockMvc.perform(delete("/companies/{id}", company.getId()))
//                .andExpect(status().isNoContent());
//    }
}

