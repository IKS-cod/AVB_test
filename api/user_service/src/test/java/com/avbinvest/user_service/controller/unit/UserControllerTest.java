package com.avbinvest.user_service.controller.unit;

import com.avbinvest.user_service.controller.UserController;
import com.avbinvest.user_service.dto.UserCreateAndUpdateDTO;
import com.avbinvest.user_service.dto.UserDTO;
import com.avbinvest.user_service.dto.UserWithCompanyDTO;
import com.avbinvest.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_ShouldReturnCreatedUser() {
        UserCreateAndUpdateDTO createDTO = new UserCreateAndUpdateDTO();
        createDTO.setFirstName("John");
        createDTO.setLastName("Doe");

        UserDTO createdUser = new UserDTO();
        createdUser.setId(1L);
        createdUser.setFirstName("John");
        createdUser.setLastName("Doe");

        when(userService.createUser(createDTO)).thenReturn(createdUser);

        ResponseEntity<UserDTO> response = userController.createUser(createDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(createdUser, response.getBody());
        verify(userService).createUser(createDTO);
    }

    @Test
    public void getUserById_ShouldReturnUser() {
        Long userId = 1L;
        UserDTO user = new UserDTO();
        user.setId(userId);
        user.setFirstName("Alice");

        when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.getUserById(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
        verify(userService).getUserById(userId);
    }

    @Test
    public void getAllUsers_ShouldReturnList() {
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        UserDTO user2 = new UserDTO();
        user2.setId(2L);

        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(userService).getAllUsers();
    }

    @Test
    public void updateUser_ShouldReturnUpdatedUser() {
        Long userId = 1L;
        UserCreateAndUpdateDTO updateDTO = new UserCreateAndUpdateDTO();
        updateDTO.setFirstName("Jane");

        UserDTO updatedUser = new UserDTO();
        updatedUser.setId(userId);
        updatedUser.setFirstName("Jane");

        when(userService.updateUser(userId, updateDTO)).thenReturn(updatedUser);

        ResponseEntity<UserDTO> response = userController.updateUser(userId, updateDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedUser, response.getBody());
        verify(userService).updateUser(userId, updateDTO);
    }

    @Test
    public void deleteUser_ShouldReturnNoContent() {
        Long userId = 1L;
        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService).deleteUser(userId);
    }

    @Test
    public void getUsersByIds_ShouldReturnList() {
        List<Long> ids = List.of(1L, 2L);
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        UserDTO user2 = new UserDTO();
        user2.setId(2L);

        when(userService.getUsersByIds(ids)).thenReturn(List.of(user1, user2));

        ResponseEntity<List<UserDTO>> response = userController.getUsersByIds(ids);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(userService).getUsersByIds(ids);
    }

    @Test
    public void getAllUsersWithCompany_ShouldReturnList() {
        UserWithCompanyDTO userWithCompany = new UserWithCompanyDTO();
        userWithCompany.setId(1L);
        userWithCompany.setFirstName("User");

        when(userService.getAllUsersWithCompany()).thenReturn(List.of(userWithCompany));

        ResponseEntity<List<UserWithCompanyDTO>> response = userController.getAllUsersWithCompany();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(userService).getAllUsersWithCompany();
    }

    @Test
    public void getUserWithCompanyById_Found_ShouldReturnUser() {
        Long userId = 1L;
        UserWithCompanyDTO user = new UserWithCompanyDTO();
        user.setId(userId);
        user.setFirstName("User");

        when(userService.getUserWithCompanyById(userId)).thenReturn(user);

        ResponseEntity<UserWithCompanyDTO> response = userController.getUserWithCompanyById(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
        verify(userService).getUserWithCompanyById(userId);
    }

    @Test
    public void getUserWithCompanyById_NotFound_ShouldReturn404() {
        Long userId = 1L;

        when(userService.getUserWithCompanyById(userId)).thenReturn(null);

        ResponseEntity<UserWithCompanyDTO> response = userController.getUserWithCompanyById(userId);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(userService).getUserWithCompanyById(userId);
    }
}
