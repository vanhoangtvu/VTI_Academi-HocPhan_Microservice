package vn.vti.dtn2504.shipmentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-manager")
public interface UserClient {
    @GetMapping("/api/v1/user/accounts/{username}")
    ApiResponse<UserInfo> getUserInfo(@PathVariable("username") String username);
}
