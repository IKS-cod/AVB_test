package com.avbinvest.user_service.service;

import com.avbinvest.user_service.dto.UserCreateAndUpdateDTO;
import com.avbinvest.user_service.dto.UserDTO;
import com.avbinvest.user_service.dto.UserWithCompanyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserCreateAndUpdateDTO dto);
    UserDTO getUserById(Long id);
    Page<UserDTO> getAllUsers(Pageable pageable);
    UserDTO updateUser(Long id, UserCreateAndUpdateDTO dto);
    void deleteUser(Long id);
    Page<UserDTO> getUsersByIds(List<Long> ids, Pageable pageable);
    Page<UserWithCompanyDTO> getAllUsersWithCompany(Pageable pageable);
    UserWithCompanyDTO getUserWithCompanyById(Long id);
}


