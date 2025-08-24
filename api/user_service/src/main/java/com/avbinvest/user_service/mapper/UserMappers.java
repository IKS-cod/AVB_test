package com.avbinvest.user_service.mapper;

import com.avbinvest.user_service.client.CompanyClient;
import com.avbinvest.user_service.dto.CompanyDTO;
import com.avbinvest.user_service.dto.UserCreateAndUpdateDTO;
import com.avbinvest.user_service.dto.UserDTO;
import com.avbinvest.user_service.dto.UserWithCompanyDTO;
import com.avbinvest.user_service.model.User;
import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class UserMappers {
    private final CompanyClient companyClient;

    public UserMappers(CompanyClient companyClient) {
        this.companyClient = companyClient;
    }

    public User userDtoToUser(UserCreateAndUpdateDTO userCreateAndUpdateDTO) {
        User user = new User();
        user.setLastName(userCreateAndUpdateDTO.getLastName());
        user.setFirstName(userCreateAndUpdateDTO.getFirstName());
        user.setPhoneNumber(userCreateAndUpdateDTO.getPhoneNumber());
        user.setCompanyId(userCreateAndUpdateDTO.getCompanyId());
        return user;
    }

    public UserDTO userToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLastName(user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setCompanyId(user.getCompanyId());
        return userDTO;
    }

    public UserWithCompanyDTO userToUserWithCompaniesDTO(User user) {
        UserWithCompanyDTO userDTO = new UserWithCompanyDTO();
        userDTO.setId(user.getId());
        userDTO.setLastName(user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setPhoneNumber(user.getPhoneNumber());

        try {
            CompanyDTO companyDTO = companyClient.getCompanyById(user.getCompanyId());
            userDTO.setCompany(companyDTO);
        } catch (FeignException.NotFound e) {
            CompanyDTO emptyCompanyDTO = new CompanyDTO();
            emptyCompanyDTO.setId(0L);
            emptyCompanyDTO.setName("Компания с id " + user.getCompanyId() + " не найдена");
            userDTO.setCompany(emptyCompanyDTO);
        }

        return userDTO;
    }

}
