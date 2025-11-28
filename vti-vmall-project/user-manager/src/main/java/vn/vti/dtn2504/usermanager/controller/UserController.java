package vn.vti.dtn2504.usermanager.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.vti.dtn2504.common.api.response.ApiResponse;
import vn.vti.dtn2504.usermanager.dto.request.CreateAccountRequest;
import vn.vti.dtn2504.usermanager.dto.response.CreateAccountResponse;
import vn.vti.dtn2504.usermanager.dto.response.UserInfoResponse;
import vn.vti.dtn2504.usermanager.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/user/accounts")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<CreateAccountResponse>> createAccount(
    @RequestBody CreateAccountRequest request){
        CreateAccountResponse response = userService.createAccount(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<UserInfoResponse>> getUserByUsername(@PathVariable String username) {
        UserInfoResponse response = userService.getUserByUsername(username);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
