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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class UserControllerTest {

//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void createUser_ShouldReturnCreatedUser() {
//        UserCreateAndUpdateDTO createDTO = new UserCreateAndUpdateDTO();
//        createDTO.setFirstName("John");
//        createDTO.setLastName("Doe");
//
//        UserDTO createdUser = new UserDTO();
//        createdUser.setId(1L);
//        createdUser.setFirstName("John");
//        createdUser.setLastName("Doe");
//
//        when(userService.createUser(createDTO)).thenReturn(createdUser);
//
//        UserDTO response = userController.createUser(createDTO);
//
//        verify(userService).createUser(createDTO);
//    }
//
//    @Test
//    public void getUserById_ShouldReturnUser() {
//        Long userId = 1L;
//        UserDTO user = new UserDTO();
//        user.setId(userId);
//        user.setFirstName("Alice");
//
//        when(userService.getUserById(userId)).thenReturn(user);
//
//        UserDTO response = userController.getUserById(userId);
//
//        verify(userService).getUserById(userId);
//    }
//
////    @Test
////    public void getAllUsers_ShouldReturnList() {
////        UserDTO user1 = new UserDTO();
////        user1.setId(1L);
////        UserDTO user2 = new UserDTO();
////        user2.setId(2L);
////
////        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));
////
////        List<UserDTO> response = userController.getAllUsers();
////
////        assertNotNull(response);
////        assertEquals(2, response.size());
////        verify(userService).getAllUsers();
////    }
//
//    @Test
//    public void updateUser_ShouldReturnUpdatedUser() {
//        Long userId = 1L;
//        UserCreateAndUpdateDTO updateDTO = new UserCreateAndUpdateDTO();
//        updateDTO.setFirstName("Jane");
//
//        UserDTO updatedUser = new UserDTO();
//        updatedUser.setId(userId);
//        updatedUser.setFirstName("Jane");
//
//        when(userService.updateUser(userId, updateDTO)).thenReturn(updatedUser);
//
//        UserDTO response = userController.updateUser(userId, updateDTO);
//
//        assertEquals(updatedUser, response);
//        verify(userService).updateUser(userId, updateDTO);
//    }
//
//    @Test
//    public void deleteUser_ShouldReturnNoContent() {
//        Long userId = 1L;
//        doNothing().when(userService).deleteUser(userId);
//        verify(userService).deleteUser(userId);
//    }
//
////    @Test
////    public void getUsersByIds_ShouldReturnList() {
////        List<Long> ids = List.of(1L, 2L);
////        UserDTO user1 = new UserDTO();
////        user1.setId(1L);
////        UserDTO user2 = new UserDTO();
////        user2.setId(2L);
////
////        when(userService.getUsersByIds(ids)).thenReturn(List.of(user1, user2));
////
////        List<UserDTO> response = userController.getUsersByIds(ids);
////
////        assertEquals(2, response.size());
////        verify(userService).getUsersByIds(ids);
////    }
//
////    @Test
////    public void getAllUsersWithCompany_ShouldReturnList() {
////        UserWithCompanyDTO userWithCompany = new UserWithCompanyDTO();
////        userWithCompany.setId(1L);
////        userWithCompany.setFirstName("User");
////
////        when(userService.getAllUsersWithCompany()).thenReturn(List.of(userWithCompany));
////
////        List<UserWithCompanyDTO> response = userController.getAllUsersWithCompany();
////
////        assertEquals(1, response.size());
////        verify(userService).getAllUsersWithCompany();
////    }
//
//    @Test
//    public void getUserWithCompanyById_Found_ShouldReturnUser() {
//        Long userId = 1L;
//        UserWithCompanyDTO user = new UserWithCompanyDTO();
//        user.setId(userId);
//        user.setFirstName("User");
//
//        when(userService.getUserWithCompanyById(userId)).thenReturn(user);
//
//        UserWithCompanyDTO response = userController.getUserWithCompanyById(userId);
//
//        assertEquals(user, response);
//        verify(userService).getUserWithCompanyById(userId);
//    }
//
//    @Test
//    public void getUserWithCompanyById_NotFound_ShouldReturn404() {
//        Long userId = 1L;
//
//        when(userService.getUserWithCompanyById(userId)).thenReturn(null);
//
//        UserWithCompanyDTO response = userController.getUserWithCompanyById(userId);
//
//        assertNull(response);
//        verify(userService).getUserWithCompanyById(userId);
//    }
}
