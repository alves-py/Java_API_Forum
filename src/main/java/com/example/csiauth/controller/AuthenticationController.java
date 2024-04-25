package com.example.csiauth.controller;

import com.example.csiauth.infra.security.TokenService;
import com.example.csiauth.model.exception.ResourceNotFoundException;
import com.example.csiauth.model.users.User;
import com.example.csiauth.model.users.UserRole;
import com.example.csiauth.repository.user.UserRepository;
import com.example.csiauth.services.authorization.AuthorizationService;
import com.example.csiauth.shared.AuthenticationDTO;
import com.example.csiauth.shared.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        var user = userRepository.findByLogin(authenticationDTO.login());

        if(user == null || !user.getPassword().equals(authenticationDTO.password())) {
            throw new ResourceNotFoundException("Invalid login or password");
        }
        //var auth = this.authenticationManager.authenticate(usarnamePassword);

        var token = tokenService.generateToken((User) user);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
