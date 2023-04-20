package com.example.security_upgrade_training.services;

import com.example.security_upgrade_training.models.User;
import com.example.security_upgrade_training.models.enums.Role;
import com.example.security_upgrade_training.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; //we used @RequiredArgsConstructor
    private final PasswordEncoder passwordEncoder;  //we used @RequiredArgsConstructor (from Spring Security)


    //Create User - boolean method
    //If user was found (result != null) => false (new user will not be created)
    //If user was not found = true (new user will be created)

    public boolean createUser (User user){
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false; //user with such email was found
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword())); //hashing password
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }
}