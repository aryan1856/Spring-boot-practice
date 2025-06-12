package in.spring_react_practice.Spring_React_Application.response;

import in.spring_react_practice.Spring_React_Application.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}