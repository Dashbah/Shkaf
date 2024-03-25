package com.shkaf.controller;

import com.shkaf.dto.UserDTO;
import com.shkaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    // ADMIN
    @PostMapping("/new")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
        try {
            userService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }

    // ADMIN, MANAGER
    // CLIENT for themselves
    @GetMapping("/{userName}")
    public ResponseEntity<UserDTO> profileUser(@PathVariable String userName) {
        var user = userService.findByName(userName).orElse(null);
        if (user != null) {
            var userDTO = UserDTO.builder()
                    .username(user.getName())
                    .email(user.getEmail())
                    .build();
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // ADMIN, MANAGER
    // CLIENT for themselves
    @PutMapping
    public ResponseEntity<String> updateProfileUser(@RequestBody UserDTO dto) {
        if (dto.getPassword() != null && !dto.getPassword().isEmpty() && dto.getPassword().equals(dto.getMatchingPassword())) {
            try {
                userService.updateProfile(dto);
                return ResponseEntity.ok().build();
            } catch (UsernameNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords don't match");
        }
    }

    // DELETE ACCOUNT
    // ADMIN, MANAGER
    // CLIENT for themselves
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
