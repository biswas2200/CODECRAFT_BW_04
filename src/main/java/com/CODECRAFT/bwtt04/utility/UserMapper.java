package com.CODECRAFT.bwtt04.utility;

import com.CODECRAFT.bwtt04.dto.UserDto;
import com.CODECRAFT.bwtt04.model.User;

public class UserMapper {
    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        return dto;
    }
}
