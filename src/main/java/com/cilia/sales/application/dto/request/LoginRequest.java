package com.cilia.sales.application.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
