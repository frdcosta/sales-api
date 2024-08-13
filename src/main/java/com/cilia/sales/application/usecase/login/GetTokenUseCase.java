package com.cilia.sales.application.usecase.login;

import com.cilia.sales.application.util.JWTUtils;
import com.cilia.sales.domain.service.SaleProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTokenUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleProducerService.class);
    private final AuthenticationManager authenticationManager;

    public String execute(String email, String password){
        try {
            LOGGER.info("Authenticating user: {}", email);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            LOGGER.error("Failed to authenticate user: {}", email);
            throw new IllegalArgumentException("Wrong email/password...");
        }
        return JWTUtils.generateToken(email);
    }
}
