package com.example.csiauth.controller.auth;

import com.example.csiauth.infra.security.TokenService;
import com.example.csiauth.model.exception.ResourceNotFoundException;
import com.example.csiauth.model.users.User;
import com.example.csiauth.repository.user.UserRepository;
import com.example.csiauth.services.users.UserService;
import com.example.csiauth.shared.auth.AuthenticationDTO;
import com.example.csiauth.shared.auth.LoginResponseDTO;
import com.example.csiauth.shared.user.RegisterDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        var user = userRepository.findByLogin(authenticationDTO.login());

        if(user == null || !user.getPassword().equals(authenticationDTO.password())) {
            throw new ResourceNotFoundException("Invalid login or password");
        }
        var token = tokenService.generateToken((User) user);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if (userService.existsUser(registerDTO.login())) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

