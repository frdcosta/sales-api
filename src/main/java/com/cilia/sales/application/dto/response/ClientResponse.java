package com.cilia.sales.application.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientResponse {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String phone;
    private String address;
}
