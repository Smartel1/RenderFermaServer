package ru.smartel.renderferma.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> register(@RequestBody @Validated RegistrationDTO userRequestDTO) {
        UserEntity user = userRepository.findByEmail(userRequestDTO.getEmail());
        if (null != user) {
            return new ResponseEntity<>("User with this email already exists", null, HttpStatus.CONFLICT);
        }

        user = new UserEntity();
        user.setEmail(userRequestDTO.getEmail());
        user.setPasswordHash(
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
        userRepository.save(user);
        return new ResponseEntity<>("User created", null, HttpStatus.OK);
    }
}
