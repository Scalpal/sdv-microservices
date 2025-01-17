package com.example.sdvauthapi.mapper;

import com.example.sdvauthapi.dto.UserDto;
import com.example.sdvauthapi.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDtos(List<User> users);
}
