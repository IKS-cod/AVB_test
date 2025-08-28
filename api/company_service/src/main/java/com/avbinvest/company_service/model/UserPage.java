package com.avbinvest.company_service.model;

import com.avbinvest.company_service.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPage {
    private List<UserDTO> content;
    private int totalPages;
    private long totalElements;
}

