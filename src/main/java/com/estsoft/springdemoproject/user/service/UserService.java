package com.estsoft.springdemoproject.user.service;

import com.estsoft.springdemoproject.user.domain.Users;
import com.estsoft.springdemoproject.user.domain.dto.AddUserRequest;
import com.estsoft.springdemoproject.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    // PW Encoder
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository,
                       BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    // 회원가입 처리 (비즈니스 로직)
    public Users save(AddUserRequest dto) {
        String password = dto.getPassword();
        String email = dto.getEmail();
        String encodedPassword = encoder.encode(password);

        Users users = new Users(email, encodedPassword);
        return repository.save(users);
    }

}
