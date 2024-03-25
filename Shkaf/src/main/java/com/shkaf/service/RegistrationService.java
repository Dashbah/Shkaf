package com.shkaf.service;

import com.shkaf.model.Role;
import com.shkaf.model.User;
import com.shkaf.config.JwtService;
import com.shkaf.dao.UserRepository;
import com.shkaf.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String registerUser(UserDTO userDTO) {
        if (userRepository.findFirstByName(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        var user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .build();

        userRepository.save(user);

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(), userDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(authentication);

        // super strange cast
        return jwtService.generateToken(user);
    }

    public String authenticateUser(UserDTO userDTO) throws AuthenticationException {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(), userDTO.getPassword()
                )
        );
        System.out.println(authentication);
        var user = userRepository.findFirstByName(userDTO.getUsername());
        System.out.println(user);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username doesn't exists");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtService.generateToken(user.get());
    }
}

