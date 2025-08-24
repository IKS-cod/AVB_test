package com.avbinvest.user_service.controller.springBootTest;

import com.avbinvest.user_service.dto.UserCreateAndUpdateDTO;
import com.avbinvest.user_service.dto.UserDTO;
import com.avbinvest.user_service.model.User;
import com.avbinvest.user_service.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:15-alpine")
                    .withDatabaseName("test_db")
                    .withUsername("sa")
                    .withPassword("sa");

    @DynamicPropertySource
    static void datasourceConfig(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    void createUser_ReturnsCreatedUser() throws Exception {
        UserCreateAndUpdateDTO newUser = new UserCreateAndUpdateDTO("John", "Doe", "1234567890", 1L);

        String jsonRequest = objectMapper.writeValueAsString(newUser);

        String response = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDTO createdUser = objectMapper.readValue(response, UserDTO.class);

        assertThat(createdUser.getId()).isNotNull();
        assertThat(createdUser.getFirstName()).isEqualTo("John");
        assertThat(createdUser.getLastName()).isEqualTo("Doe");
    }

    @Test
    void getUserById_ReturnsUser() throws Exception {
        UserCreateAndUpdateDTO newUser = new UserCreateAndUpdateDTO("Alice", "Smith", "555-1234", 2L);
        String content = objectMapper.writeValueAsString(newUser);

        String createResponse = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        UserDTO created = objectMapper.readValue(createResponse, UserDTO.class);

        mockMvc.perform(get("/users/{id}", created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alice"))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    @Test
    void getAllUsers_ReturnsList() throws Exception {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setPhoneNumber("1234567890");
        user1.setCompanyId(1L);

        User user2 = new User();
        user2.setFirstName("Alice");
        user2.setLastName("Smith");
        user2.setPhoneNumber("555-1234");
        user2.setCompanyId(2L);

        userRepository.save(user1);
        userRepository.save(user2);

        String response = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<UserDTO> users = objectMapper.readValue(response, new TypeReference<List<UserDTO>>() {
        });

        assertThat(users).hasSize(2);
        assertThat(users).extracting("firstName").containsExactlyInAnyOrder("John", "Alice");
        assertThat(users).extracting("lastName").containsExactlyInAnyOrder("Doe", "Smith");
    }


    @Test
    void updateUser_ReturnsUpdatedUser() throws Exception {
        UserCreateAndUpdateDTO newUser = new UserCreateAndUpdateDTO("Bob", "Jones", "987654321", 3L);
        UserDTO created = objectMapper.readValue(mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString(), UserDTO.class);

        UserCreateAndUpdateDTO updateUser = new UserCreateAndUpdateDTO("Robert", "Jones", "987654321", 3L);

        mockMvc.perform(put("/users/{id}", created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Robert"));
    }

    @Test
    void deleteUser_ReturnsNoContent() throws Exception {
        UserCreateAndUpdateDTO newUser = new UserCreateAndUpdateDTO("Eve", "Adams", "111-2222", 4L);
        UserDTO created = objectMapper.readValue(mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString(), UserDTO.class);

        mockMvc.perform(delete("/users/{id}", created.getId()))
                .andExpect(status().isNoContent());
    }
}
