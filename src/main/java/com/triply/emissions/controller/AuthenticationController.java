package com.triply.emissions.controller;

import com.triply.emissions.command.LoginReq;
import com.triply.emissions.model.User;
import com.triply.emissions.response.ErrorAuthResponse;
import com.triply.emissions.response.LoginResponse;
import com.triply.emissions.security.jwt.JwtTokenProvider;
import com.triply.emissions.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping(path="/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getLogin(), loginReq.getPassword()));
            User user = userService.findByUserName(authentication.getName());
            String token = jwtTokenProvider.createToken(user.getLogin(),user.getRole());
            LoginResponse loginRes = new LoginResponse(user.getLogin(),token);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorAuthResponse errorResponse = new ErrorAuthResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorAuthResponse errorResponse = new ErrorAuthResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
