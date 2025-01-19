package com.linjiasong.admin.feign;

import com.linjiasong.admin.excepiton.AdminBaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author linjiasong
 * @date 2025/01/19 15:18
 */
@FeignClient("user-service")
public interface UserServiceClient {

    @PostMapping("/api/user/admin/ban/{id}")
    AdminBaseResponse banUser(@PathVariable("id") Long id);

}
