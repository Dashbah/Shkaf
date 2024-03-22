package com.shkaf.service;

import com.shkaf.dto.UserDTO;
import com.shkaf.model.Role;
import com.shkaf.model.User;
import com.shkaf.dao.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            throw new RuntimeException("Passwords are not equal");
        }
        if (userRepository.findFirstByName(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        User user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .build();
        userRepository.save(user);
        return true;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public Optional<User> findByName(String name) {
//        var user = userRepository.findFirstByName(name);
//        if (user.isEmpty()) {
//            return Optional.empty();
//        }
//        return Optional.ofNullable(UserDTO.builder()
//                .username(user.get().getUsername())
//                .email(user.get().getEmail())
//                .build());
//    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findFirstByName(name);
    }


    @Override
    public void updateProfile(UserDTO dto) {
        if (userRepository.findFirstByName(dto.getUsername()).isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + dto.getUsername());
        }
        var savedUser = userRepository.findFirstByName(dto.getUsername()).orElseThrow();
        boolean isChanged = false;
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            isChanged = true;
        }

        if (!dto.getEmail().equals(savedUser.getEmail())) {
            savedUser.setEmail(dto.getEmail());
            isChanged = true;
        }

        if (isChanged) {
            userRepository.save(savedUser);
        }
    }

    private UserDTO toDto(User user) {
        return UserDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findFirstByName(username).isEmpty()) {
            throw new UsernameNotFoundException("User not found with name " + username);
        }
        User user = userRepository.findFirstByName(username).orElseThrow();

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles
        );
    }
}
