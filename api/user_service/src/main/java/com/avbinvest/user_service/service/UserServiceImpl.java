package com.avbinvest.user_service.service;

import com.avbinvest.user_service.dto.UserCreateAndUpdateDTO;
import com.avbinvest.user_service.dto.UserDTO;
import com.avbinvest.user_service.dto.UserWithCompanyDTO;
import com.avbinvest.user_service.exceptions.UserNotFoundException;
import com.avbinvest.user_service.mapper.UserMappers;
import com.avbinvest.user_service.model.User;
import com.avbinvest.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMappers userMappers;

    @Override
    public UserDTO createUser(UserCreateAndUpdateDTO userCreateAndUpdateDTO) {
        User user = new User();
        userMappers.userDtoToUser(userCreateAndUpdateDTO, user);
        User savedUser = userRepository.save(user);
        UserDTO result = userMappers.userToUserDto(savedUser);
        log.info("Пользователь создан в сервисе user_service в методе createUser: {}", result);
        return result;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = findUserByIdOrThrow(id);
        UserDTO result = userMappers.userToUserDto(user);
        log.info("Пользователь с id {} получен в сервисе user_service в методе getUserById: {}", id, result);
        return result;
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        List<User> users = usersPage.getContent();
        List<UserDTO> userDTOList = userMappers.usersListToUsersDtoList(users);
        log.info("Преобразованные UserDTO в сервисе user_service в методе getAllUsers: {}", userDTOList);
        return new PageImpl<>(userDTOList, pageable, usersPage.getTotalElements());
    }



    @Override
    public UserDTO updateUser(Long id, UserCreateAndUpdateDTO userCreateAndUpdateDTO) {
        User user = findUserByIdOrThrow(id);
        userMappers.userDtoToUser(userCreateAndUpdateDTO, user);
        User updatedUser = userRepository.save(user);
        UserDTO result = userMappers.userToUserDto(updatedUser);
        log.info("Пользователь с id {} обновлён в сервисе user_service в методе updateUser: {}", id, result);
        return result;
    }

    @Override
    public void deleteUser(Long id) {
        findUserByIdOrThrow(id);
        userRepository.deleteById(id);
        log.info("Пользователь с id {} удалён в сервисе user_service в методе deleteUser", id);
    }

    @Override
    public Page<UserDTO> getUsersByIds(List<Long> ids, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), ids.size());
        if (start > end) {
            return new PageImpl<>(new ArrayList<>(), pageable, ids.size());
        }
        List<Long> idsPage = ids.subList(start, end);
        Page<User> usersPage = userRepository.findByIdIn(idsPage, pageable);
        List<UserDTO> userDtoList = userMappers.usersListToUsersDtoList(usersPage.getContent());

        Map<Long, UserDTO> foundUsersMap = userDtoList.stream()
                .collect(Collectors.toMap(UserDTO::getId, userDto -> userDto));

        List<UserDTO> result = idsPage.stream()
                .map(id -> {
                    UserDTO userDTO = foundUsersMap.get(id);
                    if (userDTO == null) {
                        UserDTO emptyUser = new UserDTO();
                        emptyUser.setId(id);
                        emptyUser.setFirstName("Пользователь с id " + id + " не найден");
                        emptyUser.setLastName("");
                        emptyUser.setPhoneNumber("");
                        return emptyUser;
                    }
                    return userDTO;
                })
                .collect(Collectors.toList());
        log.info("Формирование итогового списка UserDTO в сервисе user_service в методе getUsersByIds: {}", result);
        return new PageImpl<>(result, pageable, ids.size());
    }

    @Override
    public Page<UserWithCompanyDTO> getAllUsersWithCompany(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        List<UserWithCompanyDTO> dtos = userMappers.usersToUserWithCompanyDTOs(usersPage.getContent());
        Page<UserWithCompanyDTO> resultPage = new PageImpl<>(dtos, pageable, usersPage.getTotalElements());
        log.info("Создана страница с DTO из сервиса user_service в методе getAllUsersWithCompany: {}", resultPage.getContent());
        return resultPage;
    }


    @Override
    public UserWithCompanyDTO getUserWithCompanyById(Long id) {
        User user = findUserByIdOrThrow(id);
        UserWithCompanyDTO result = userMappers.userToUserWithCompaniesDTO(user);
        log.info("Пользователь из сервиса user_service в методе getUserWithCompanyById  с id {} получен: {}", id, result);
        return result;
    }

    private User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
