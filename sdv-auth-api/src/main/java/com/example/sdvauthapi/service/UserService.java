package com.example.sdvauthapi.service;

import com.example.sdvauthapi.dto.UserDto;
import com.example.sdvauthapi.entity.AuthResponse;

public interface UserService {
    public String register(UserDto userDto);

    public AuthResponse login(UserDto userDto);

    public String verifyToken(String token);
}
