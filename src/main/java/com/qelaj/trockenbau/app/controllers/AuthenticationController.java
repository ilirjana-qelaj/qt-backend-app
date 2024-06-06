package com.qelaj.trockenbau.app.controllers;

import com.qelaj.trockenbau.app.dtos.UserProfileDTO;
import com.qelaj.trockenbau.app.entities.User;
import com.qelaj.trockenbau.app.services.AuthenticationService;
import com.qelaj.trockenbau.app.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RequestMapping("/authenticate")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody User loginUserDto, HttpServletRequest request, HttpServletResponse response) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto, request, response);

        if(authenticatedUser == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();

        return ResponseEntity.ok(loginResponse);
    }


    @GetMapping("/me")
    public ResponseEntity<?> loggedInUser() {
        return ResponseEntity.ok(authenticationService.getLoggedInUser());
    }


    @PatchMapping("/update/user/id/{id}")
    public ResponseEntity<?> updateUserInfo(@PathVariable Integer id, @ModelAttribute UserProfileDTO userDTO) throws SQLException, IOException {
        int status = authenticationService.updateUserInfo(id,userDTO);
        return new ResponseEntity<>(HttpStatus.valueOf(status));
    }

    @GetMapping("/password-matches")
    public ResponseEntity<?> passwordMatches(@RequestParam String password) throws SQLException, IOException {
        boolean matches = authenticationService.checkCurrentPasswordMatches(password);
        return ResponseEntity.ok(matches);
    }

}


@Builder
class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}