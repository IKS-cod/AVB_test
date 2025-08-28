package com.avbinvest.user_service.service.unit;

import com.avbinvest.user_service.dto.UserCreateAndUpdateDTO;
import com.avbinvest.user_service.dto.UserDTO;
import com.avbinvest.user_service.dto.UserWithCompanyDTO;
import com.avbinvest.user_service.exceptions.UserNotFoundException;
import com.avbinvest.user_service.mapper.UserMappers;
import com.avbinvest.user_service.model.User;
import com.avbinvest.user_service.repository.UserRepository;
import com.avbinvest.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private UserMappers userMappers;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void createUser_ShouldReturnCreatedUserDTO() {
//        UserCreateAndUpdateDTO createDTO = new UserCreateAndUpdateDTO();
//        createDTO.setFirstName("John");
//        createDTO.setLastName("Doe");
//
//        User userEntity = new User();
//        userEntity.setFirstName("John");
//        userEntity.setLastName("Doe");
//
//        User savedUser = new User();
//        savedUser.setId(1L);
//        savedUser.setFirstName("John");
//        savedUser.setLastName("Doe");
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(1L);
//        userDTO.setFirstName("John");
//        userDTO.setLastName("Doe");
//
//        when(userMappers.userDtoToUser(createDTO)).thenReturn(userEntity);
//        when(userRepository.save(userEntity)).thenReturn(savedUser);
//        when(userMappers.userToUserDto(savedUser)).thenReturn(userDTO);
//
//        UserDTO result = userService.createUser(createDTO);
//
//        assertNotNull(result);
//        assertEquals("John", result.getFirstName());
//        verify(userRepository).save(userEntity);
//        verify(userMappers).userToUserDto(savedUser);
//    }
//
//    @Test
//    public void getUserById_WhenFound_ReturnsUserDTO() {
//        Long userId = 1L;
//        User user = new User();
//        user.setId(userId);
//        user.setFirstName("Alice");
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(userId);
//        userDTO.setFirstName("Alice");
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(userMappers.userToUserDto(user)).thenReturn(userDTO);
//
//        UserDTO result = userService.getUserById(userId);
//
//        assertNotNull(result);
//        assertEquals("Alice", result.getFirstName());
//        verify(userRepository).findById(userId);
//        verify(userMappers).userToUserDto(user);
//    }
//
//    @Test
//    public void getUserById_WhenNotFound_ThrowsException() {
//        Long userId = 1L;
//
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
//        verify(userRepository).findById(userId);
//        verifyNoMoreInteractions(userMappers);
//    }
//
////    @Test
////    public void getAllUsers_ShouldReturnListOfUserDTO() {
////        User user1 = new User();
////        user1.setId(1L);
////        User user2 = new User();
////        user2.setId(2L);
////
////        UserDTO userDTO1 = new UserDTO();
////        userDTO1.setId(1L);
////        UserDTO userDTO2 = new UserDTO();
////        userDTO2.setId(2L);
////
////        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
////        when(userMappers.userToUserDto(user1)).thenReturn(userDTO1);
////        when(userMappers.userToUserDto(user2)).thenReturn(userDTO2);
////
////        List<UserDTO> result = userService.getAllUsers();
////
////        assertNotNull(result);
////        assertEquals(2, result.size());
////        verify(userRepository).findAll();
////        verify(userMappers, times(2)).userToUserDto(any(User.class));
////    }
//
//    @Test
//    public void updateUser_WhenFound_ShouldReturnUpdatedUserDTO() {
//        Long userId = 1L;
//        UserCreateAndUpdateDTO updateDTO = new UserCreateAndUpdateDTO();
//        updateDTO.setFirstName("Jane");
//        updateDTO.setLastName("Smith");
//
//        User user = new User();
//        user.setId(userId);
//        user.setFirstName("OldName");
//
//        User updatedUser = new User();
//        updatedUser.setId(userId);
//        updatedUser.setFirstName("Jane");
//        updatedUser.setLastName("Smith");
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(userId);
//        userDTO.setFirstName("Jane");
//        userDTO.setLastName("Smith");
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(userRepository.save(user)).thenReturn(updatedUser);
//        when(userMappers.userToUserDto(updatedUser)).thenReturn(userDTO);
//
//        UserDTO result = userService.updateUser(userId, updateDTO);
//
//        assertNotNull(result);
//        assertEquals("Jane", result.getFirstName());
//        verify(userRepository).findById(userId);
//        verify(userRepository).save(user);
//        verify(userMappers).userToUserDto(updatedUser);
//    }
//
//    @Test
//    public void updateUser_WhenNotFound_ShouldThrowException() {
//        Long userId = 1L;
//        UserCreateAndUpdateDTO updateDTO = new UserCreateAndUpdateDTO();
//
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, updateDTO));
//        verify(userRepository).findById(userId);
//        verifyNoMoreInteractions(userMappers);
//    }
//
//    @Test
//    public void deleteUser_WhenFound_ShouldDeleteUser() {
//        Long userId = 1L;
//        User user = new User();
//        user.setId(userId);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        doNothing().when(userRepository).deleteById(userId);
//
//        userService.deleteUser(userId);
//
//        verify(userRepository).findById(userId);
//        verify(userRepository).deleteById(userId);
//    }
//
//    @Test
//    public void deleteUser_WhenNotFound_ShouldThrowException() {
//        Long userId = 1L;
//
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
//        verify(userRepository).findById(userId);
//        verifyNoMoreInteractions(userRepository);
//    }
//
////    @Test
////    public void getUsersByIds_ShouldReturnListIncludingNotFoundUsers() {
////        List<Long> ids = List.of(1L, 2L);
////        User user1 = new User();
////        user1.setId(1L);
////
////        UserDTO userDTO1 = new UserDTO();
////        userDTO1.setId(1L);
////
////        when(userRepository.findAllById(ids)).thenReturn(List.of(user1));
////        when(userMappers.userToUserDto(user1)).thenReturn(userDTO1);
////
////        List<UserDTO> result = userService.getUsersByIds(ids);
////
////        assertEquals(2, result.size());
////        assertEquals("Пользователь с id 2 не найден", result.get(1).getFirstName());
////        verify(userRepository).findAllById(ids);
////        verify(userMappers).userToUserDto(user1);
////    }
//
////    @Test
////    public void getAllUsersWithCompany_ShouldReturnList() {
////        User user = new User();
////        user.setId(1L);
////
////        UserWithCompanyDTO dto = new UserWithCompanyDTO();
////        dto.setId(1L);
////
////        when(userRepository.findAll()).thenReturn(List.of(user));
////        when(userMappers.userToUserWithCompaniesDTO(user)).thenReturn(dto);
////
////        List<UserWithCompanyDTO> result = userService.getAllUsersWithCompany();
////
////        assertEquals(1, result.size());
////        assertEquals(1L, result.get(0).getId());
////        verify(userRepository).findAll();
////        verify(userMappers).userToUserWithCompaniesDTO(user);
////    }
//
//    @Test
//    public void getUserWithCompanyById_WhenFound_ShouldReturnDTO() {
//        Long userId = 1L;
//        User user = new User();
//        user.setId(userId);
//
//        UserWithCompanyDTO dto = new UserWithCompanyDTO();
//        dto.setId(userId);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(userMappers.userToUserWithCompaniesDTO(user)).thenReturn(dto);
//
//        UserWithCompanyDTO result = userService.getUserWithCompanyById(userId);
//
//        assertNotNull(result);
//        assertEquals(userId, result.getId());
//        verify(userRepository).findById(userId);
//        verify(userMappers).userToUserWithCompaniesDTO(user);
//    }
//
//    @Test
//    public void getUserWithCompanyById_WhenNotFound_ShouldThrowException() {
//        Long userId = 1L;
//
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> userService.getUserWithCompanyById(userId));
//        verify(userRepository).findById(userId);
//        verifyNoMoreInteractions(userMappers);
//    }
}
