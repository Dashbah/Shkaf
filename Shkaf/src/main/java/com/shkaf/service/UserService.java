package com.shkaf.service;

import com.shkaf.dto.UserDTO;
import com.shkaf.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService { // security
    boolean save(UserDTO userDTO);
    void save(User user);
    List<UserDTO> getAll();
    Optional<User> findByName(String name);
    void updateProfile(UserDTO dto);
}