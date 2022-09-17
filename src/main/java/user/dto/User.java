package user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private int userStatus;
    private String phone;
    private int id;
    private String email;
    private String username;

    public User() {
    }
}

