package vn.vti.dtn2504.mallservice.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String username;
    private String name;
    private String email;
    private String address;
}
