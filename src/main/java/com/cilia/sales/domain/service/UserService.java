package com.cilia.sales.domain.service;

import com.cilia.sales.domain.entity.User;
import com.cilia.sales.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Boolean signUp(String username, String password) {
        Optional<User> existingUser = repository.findByEmail(username);
        String hashedPassword = passwordEncoder.encode(password);
        return existingUser.get().getPassword().equals(hashedPassword);
    }
}
