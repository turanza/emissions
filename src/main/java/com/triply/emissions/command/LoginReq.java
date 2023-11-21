package com.triply.emissions.command;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class LoginReq {

    private String login;
    private String password;

}
