package com.example.security_upgrade_training.repositories;

import com.example.security_upgrade_training.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    //As our users login with e-mail we need to find them in DB by e-mail
    User findByEmail(String email);
}
