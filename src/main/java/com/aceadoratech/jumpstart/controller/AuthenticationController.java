package com.aceadoratech.jumpstart.controller;
import com.aceadoratech.jumpstart.exchanges.AuthenticationRequest;
import com.aceadoratech.jumpstart.exchanges.AuthenticationResponse;
import com.aceadoratech.jumpstart.exchanges.RegisterRequest;
import com.aceadoratech.jumpstart.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(service.register(registerRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(service.authenticate(authRequest));
    }
}
