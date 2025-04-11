package com.CODECRAFT.bwtt04.service;

import com.CODECRAFT.bwtt04.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserBYId(UUID id);
    List<UserDto> getAllUsers();
    UserDto updateUser(UUID id, UserDto userDetails);
    void deleteUser (UUID id);
}
