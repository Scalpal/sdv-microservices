package com.example.sdvauthapi.service.impl;

import com.example.sdvauthapi.dto.UserDto;
import com.example.sdvauthapi.entity.AuthResponse;
import com.example.sdvauthapi.entity.User;
import com.example.sdvauthapi.mapper.UserMapper;
import com.example.sdvauthapi.repository.UserRepository;
import com.example.sdvauthapi.security.JwtUtils;
import com.example.sdvauthapi.service.UserService;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Override
    public String register(UserDto userDto) {
        User newUser = userMapper.toEntity(userDto);
        newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));

        userRepository.save(newUser);

        return "User successfully created";
    }

    @Override
    public AuthResponse login(UserDto userDto) {
        User searchedUser = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(!BCrypt.checkpw(userDto.getPassword(), searchedUser.getPassword())){
            throw new IllegalArgumentException("Wrong email or password");
        }

        String accessToken = jwtUtils.generate(searchedUser.getId().toString(), "ACCESS");
        String refreshToken = jwtUtils.generate(searchedUser.getId().toString(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public String verifyToken(String token) {
        return null;
    }
}
