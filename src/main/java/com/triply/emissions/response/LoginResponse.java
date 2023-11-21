package com.triply.emissions.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@Data
public class LoginResponse {
    private String login;
    private String token;
}
