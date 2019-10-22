package ru.smartel.renderferma.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smartel.renderferma.server.dto.RegistrationDTO;
import ru.smartel.renderferma.server.entity.UserEntity;
import ru.smartel.renderferma.server.repository.UserRepository;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * User registration endpoint
     */
    @PostMapping
    public void register(@RequestBody @Validated RegistrationDTO userRequestDTO) {
        UserEntity user = new UserEntity();
        user.setEmail(userRequestDTO.getEmail());
        user.setPasswordHash(
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
        userRepository.save(user);
    }
}
