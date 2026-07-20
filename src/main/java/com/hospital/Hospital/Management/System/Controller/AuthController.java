package com.hospital.Hospital.Management.System.Controller;

import com.hospital.Hospital.Management.System.Services.JwtService;

import com.hospital.Hospital.Management.System.model.LoginRequest;
import com.hospital.Hospital.Management.System.model.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {
        try {
            // 1. Authenticate — throws exception if wrong credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Get user details from authenticated object
            UserDetails userDetails =
                    (UserDetails) authentication.getPrincipal();

            // 3. Generate JWT token
            String token = jwtService.generateToken(userDetails);

            // 4. Return token
            return ResponseEntity.ok(
                    new LoginResponse(token, userDetails.getUsername()));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}