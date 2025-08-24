package com.avbinvest.user_service.controller;

import com.avbinvest.user_service.dto.UserCreateAndUpdateDTO;
import com.avbinvest.user_service.dto.UserDTO;
import com.avbinvest.user_service.dto.UserWithCompanyDTO;
import com.avbinvest.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User API", description = "API для управления пользователями")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Создать пользователя")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateAndUpdateDTO userCreateAndUpdateDTO) {
        log.info("Создание пользователя с данными: {}", userCreateAndUpdateDTO);
        UserDTO createdUser = userService.createUser(userCreateAndUpdateDTO);
        log.info("Пользователь создан: {}", createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Получить пользователя по ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        log.info("Получение пользователя по id: {}", id);
        UserDTO user = userService.getUserById(id);
        log.info("Получен пользователь: {}", user);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Получить всех пользователей")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("Получение всех пользователей");
        List<UserDTO> users = userService.getAllUsers();
        log.info("Всего пользователей получено: {}", users);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Обновить пользователя по ID")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserCreateAndUpdateDTO userCreateAndUpdateDTO) {
        log.info("Обновление пользователя id: {} с данными: {}", id, userCreateAndUpdateDTO);
        UserDTO updatedUser = userService.updateUser(id, userCreateAndUpdateDTO);
        log.info("Пользователь обновлён: {}", updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Удалить пользователя по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        log.info("Удаление пользователя с id: {}", id);
        userService.deleteUser(id);
        log.info("Пользователь с id {} удалён", id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить пользователей по списку ID")
    @GetMapping("/byIds")
    public ResponseEntity<List<UserDTO>> getUsersByIds(@RequestParam("ids") List<Long> ids) {
        log.info("Получение пользователей по списку id: {}", ids);
        List<UserDTO> users = userService.getUsersByIds(ids);
        log.info("Пользователей найдено: {}", users);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Получить всех пользователей с информацией о компаниях", description = "Возвращает список всех пользователей с полными данными о компаниях, в которых они работают")
    @ApiResponse(responseCode = "200", description = "Список пользователей с компаниями успешно получен")
    @GetMapping("/with-companies")
    public ResponseEntity<List<UserWithCompanyDTO>> getAllUsersWithCompany() {
        log.info("Запрос на получение всех пользователей с компаниями");
        List<UserWithCompanyDTO> users = userService.getAllUsersWithCompany();
        log.info("Найдено пользователей с компаниями: {}", users);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Получить пользователя с информацией о компании по ID", description = "Возвращает пользователя с полной информацией о компании по идентификатору пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь с компанией успешно найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/with-companies/{id}")
    public ResponseEntity<UserWithCompanyDTO> getUserWithCompanyById(@PathVariable("id") Long id) {
        log.info("Запрос на получение пользователя с компанией по id: {}", id);
        UserWithCompanyDTO user = userService.getUserWithCompanyById(id);
        if (user == null) {
            log.warn("Пользователь с id {} не найден", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Пользователь с id {} успешно найден", id);
        return ResponseEntity.ok(user);
    }
}


