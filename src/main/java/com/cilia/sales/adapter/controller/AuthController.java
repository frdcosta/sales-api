package com.cilia.sales.adapter.controller;

import com.cilia.sales.application.dto.request.LoginRequest;
import com.cilia.sales.application.dto.response.LoginResponse;
import com.cilia.sales.application.usecase.login.GetTokenUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GetTokenUseCase getTokenUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        var token = getTokenUseCase.execute(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(LoginResponse.builder().email(request.getEmail()).token(token).build());
    }
}
