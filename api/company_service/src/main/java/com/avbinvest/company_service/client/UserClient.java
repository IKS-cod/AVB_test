package com.avbinvest.company_service.client;

import com.avbinvest.company_service.model.UserPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/byIds")
    UserPage getUsersByIds(@RequestParam("ids") List<Long> ids,
                           @RequestParam("page") int page,
                           @RequestParam("size") int size);
}


