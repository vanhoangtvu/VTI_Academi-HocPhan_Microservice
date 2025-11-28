package vn.vti.dtn2504.usermanager.service;

import vn.vti.dtn2504.usermanager.dto.request.CreateAccountRequest;
import vn.vti.dtn2504.usermanager.dto.response.CreateAccountResponse;
import vn.vti.dtn2504.usermanager.dto.response.UserInfoResponse;

public interface UserService {
    CreateAccountResponse createAccount(CreateAccountRequest request);
    UserInfoResponse getUserByUsername(String username);
}
