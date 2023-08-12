package com.example.awsecs.service;

import java.util.Optional;

import com.example.awsecs.dto.LoginDTO;
import com.example.awsecs.dto.UserDTO;
import com.example.awsecs.entity.User;
import com.example.awsecs.repository.UserRepository;
import com.example.awsecs.security.CustomUserDetails;
import com.example.awsecs.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = userRepository.findByName(userName);
        return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(userName));
    }

    public UserDetails loadUserById(Integer id) {
        var user = userRepository.findById(id);
        return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
    }

    public String createUser(UserDTO userDTO) {
        var user = new User();
        user.setName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("user");
        userRepository.save(user);
        var jwt = jwtTokenProvider.generateToken(new CustomUserDetails(user));
        return jwt;
    }

    public LoginDTO login(UserDTO userDTO) {
        var user = userRepository.findByName(userDTO.getUserName());
        if (user.isPresent()) {
            var checkPassword = passwordEncoder.matches(userDTO.getPassword(), user.get().getPassword());
            if (checkPassword) {
                return generateToken(user);
            }
        }
        return null;
    }

    public LoginDTO refresh(String refreshToken) {
        var check = jwtTokenProvider.validateRefreshToken(refreshToken);
        if (check) {
            var userId = jwtTokenProvider.getUserIdFromJWTRefresh(refreshToken);
            var user = userRepository.findById(userId);
            if (user.isPresent()) {
                return generateToken(user);
            }
        }
        return null;
    }

    private LoginDTO generateToken(Optional<User> user){
        var token = jwtTokenProvider.generateToken(new CustomUserDetails(user.get()));
        var refreshTokenGenerated = jwtTokenProvider.generateRefreshToken(new CustomUserDetails(user.get()));
        return LoginDTO.builder()
                .token(token)
                .refreshToken(refreshTokenGenerated)
                .build();
    }
}
