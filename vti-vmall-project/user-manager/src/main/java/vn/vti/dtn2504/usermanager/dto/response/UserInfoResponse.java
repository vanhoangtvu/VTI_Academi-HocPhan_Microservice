package vn.vti.dtn2504.usermanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private String username;
    private String name;
    private String email;
    private String address;
}
