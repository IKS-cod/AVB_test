package com.avbinvest.user_service.controller;

import com.avbinvest.user_service.dto.UserCreateAndUpdateDTO;
import com.avbinvest.user_service.dto.UserDTO;
import com.avbinvest.user_service.dto.UserWithCompanyDTO;
import com.avbinvest.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User API", description = "API для управления пользователями")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "Создать пользователя")
    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserCreateAndUpdateDTO userCreateAndUpdateDTO) {
        log.info("Создание пользователя с данными: {}", userCreateAndUpdateDTO);
        return userService.createUser(userCreateAndUpdateDTO);
    }

    @Operation(summary = "Получить пользователя по ID")
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        log.info("Получение пользователя по id: {}", id);
        return userService.getUserById(id);
    }

    @Operation(summary = "Получить всех пользователей")
    @GetMapping
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        log.info("Получение всех пользователей с пагинацией");
        return userService.getAllUsers(pageable);
    }

    @Operation(summary = "Обновить пользователя по ID")
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserCreateAndUpdateDTO userCreateAndUpdateDTO) {
        log.info("Обновление пользователя id: {} с данными: {}", id, userCreateAndUpdateDTO);
        return userService.updateUser(id, userCreateAndUpdateDTO);
    }

    @Operation(summary = "Удалить пользователя по ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        log.info("Удаление пользователя с id: {}", id);
        userService.deleteUser(id);
    }

    @Operation(summary = "Получить пользователей по списку ID с пагинацией")
    @GetMapping("/byIds")
    public Page<UserDTO> getUsersByIds(
            @RequestParam("ids") List<Long> ids,
            Pageable pageable) {
        log.info("Получение пользователей по списку id: {} с пагинацией", ids);
        return userService.getUsersByIds(ids, pageable);
    }

    @Operation(summary = "Получить всех пользователей с информацией о компаниях", description = "Возвращает список всех пользователей с полными данными о компаниях, в которых они работают")
    @ApiResponse(responseCode = "200", description = "Список пользователей с компаниями успешно получен")
    @GetMapping("/with-companies")
    public Page<UserWithCompanyDTO> getAllUsersWithCompany(Pageable pageable) {
        log.info("Запрос на получение всех пользователей с компаниями с пагинацией");
        return userService.getAllUsersWithCompany(pageable);
    }


    @Operation(summary = "Получить пользователя с информацией о компании по ID", description = "Возвращает пользователя с полной информацией о компании по идентификатору пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь с компанией успешно найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/with-companies/{id}")
    public UserWithCompanyDTO getUserWithCompanyById(@PathVariable("id") Long id) {
        log.info("Запрос на получение пользователя с компанией по id: {}", id);
        return userService.getUserWithCompanyById(id);
    }
}
