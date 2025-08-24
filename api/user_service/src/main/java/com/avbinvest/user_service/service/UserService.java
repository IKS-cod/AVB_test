package com.avbinvest.user_service.service;

import com.avbinvest.user_service.dto.UserCreateAndUpdateDTO;
import com.avbinvest.user_service.dto.UserDTO;
import com.avbinvest.user_service.dto.UserWithCompanyDTO;
import com.avbinvest.user_service.exceptions.UserNotFoundException;
import com.avbinvest.user_service.mapper.UserMappers;
import com.avbinvest.user_service.model.User;
import com.avbinvest.user_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMappers userMappers;

    public UserService(UserRepository userRepository, UserMappers userMappers) {
        this.userRepository = userRepository;
        this.userMappers = userMappers;
    }

    public UserDTO createUser(UserCreateAndUpdateDTO userCreateAndUpdateDTO) {
        log.info("Создание пользователя с данными: {}", userCreateAndUpdateDTO);
        User user = userMappers.userDtoToUser(userCreateAndUpdateDTO);
        log.debug("Преобразованный User: {}", user);
        User savedUser = userRepository.save(user);
        log.debug("Сохранённый User: {}", savedUser);
        UserDTO result = userMappers.userToUserDto(savedUser);
        log.info("Пользователь создан: {}", result);
        return result;
    }

    public UserDTO getUserById(Long id) {
        log.info("Получение пользователя по id: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        log.debug("Найденный пользователь: {}", user);
        UserDTO result = userMappers.userToUserDto(user);
        log.info("Пользователь с id {} получен: {}", id, result);
        return result;
    }

    public List<UserDTO> getAllUsers() {
        log.info("Получение всех пользователей");
        List<User> users = userRepository.findAll();
        log.debug("Найденные пользователи: {}", users);
        List<UserDTO> result = users.stream()
                .map(userMappers::userToUserDto)
                .collect(Collectors.toList());
        log.info("Пользователи, возвращённые из списка: {}", result);
        return result;
    }


    public UserDTO updateUser(Long id, UserCreateAndUpdateDTO userCreateAndUpdateDTO) {
        log.info("Обновление пользователя с id {} данными: {}", id, userCreateAndUpdateDTO);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        log.debug("Пользователь до обновления: {}", user);
        user.setFirstName(userCreateAndUpdateDTO.getFirstName());
        user.setLastName(userCreateAndUpdateDTO.getLastName());
        user.setPhoneNumber(userCreateAndUpdateDTO.getPhoneNumber());
        user.setCompanyId(userCreateAndUpdateDTO.getCompanyId());
        User updatedUser = userRepository.save(user);
        log.debug("Пользователь после обновления: {}", updatedUser);
        UserDTO result = userMappers.userToUserDto(updatedUser);
        log.info("Пользователь с id {} обновлён: {}", id, result);
        return result;
    }

    public void deleteUser(Long id) {
        log.info("Удаление пользователя с id: {}", id);
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
        log.info("Пользователь с id {} удалён", id);
    }

    public List<UserDTO> getUsersByIds(List<Long> ids) {
        log.info("Получение пользователей по списку id: {}", ids);

        List<User> users = userRepository.findAllById(ids);
        log.debug("Найденные пользователи: {}", users);

        Map<Long, UserDTO> foundUsersMap = users.stream()
                .map(userMappers::userToUserDto)
                .collect(Collectors.toMap(UserDTO::getId, userDto -> userDto));

        List<UserDTO> result = ids.stream()
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

        log.info("Пользователи из списка id: {}", result);
        return result;
    }


    public List<UserWithCompanyDTO> getAllUsersWithCompany() {
        log.info("Получение всех пользователей");
        List<User> users = userRepository.findAll();
        log.debug("Найденные пользователи: {}", users);
        List<UserWithCompanyDTO> result = users.stream()
                .map(userMappers::userToUserWithCompaniesDTO)
                .collect(Collectors.toList());
        log.info("Пользователи, возвращённые из списка: {}", result);
        return result;
    }


    public UserWithCompanyDTO getUserWithCompanyById(Long id) {
        log.info("Получение пользователя по id: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        log.debug("Найденный пользователь: {}", user);
        UserWithCompanyDTO result = userMappers.userToUserWithCompaniesDTO(user);
        log.info("Пользователь с id {} получен: {}", id, result);
        return result;
    }
}


