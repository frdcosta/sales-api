package com.cilia.sales.usecase;

import com.cilia.sales.application.usecase.login.GetTokenUseCase;
import com.cilia.sales.application.util.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GetTokenUseCaseTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private GetTokenUseCase getTokenUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ValidCredentials_ReturnsToken() {
        String email = "user@example.com";
        String password = "password";
        String token = "jwt-token";

        mockStatic(JWTUtils.class);
        when(JWTUtils.generateToken(email)).thenReturn(token);

        when(authenticationManager.authenticate(any())).thenReturn(any());

        String result = getTokenUseCase.execute(email, password);

        assertEquals(token, result);
        verify(authenticationManager, times(1)).authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Test
    void testExecute_InvalidCredentials_ThrowsException() {
        String email = "user@example.com";
        String password = "wrongpassword";

        doThrow(new BadCredentialsException("")).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            getTokenUseCase.execute(email, password);
        });

        assertEquals("Wrong email/password...", thrown.getMessage());
        verify(authenticationManager, times(1)).authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
