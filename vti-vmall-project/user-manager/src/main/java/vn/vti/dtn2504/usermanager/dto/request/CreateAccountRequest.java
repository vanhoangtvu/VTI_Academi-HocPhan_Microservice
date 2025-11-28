package vn.vti.dtn2504.usermanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String address;
}
