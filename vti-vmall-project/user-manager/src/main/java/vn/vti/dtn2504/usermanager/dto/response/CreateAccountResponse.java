package vn.vti.dtn2504.usermanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountResponse {
    private String username;
    private String name;
    private String email;
    private String address;
}
