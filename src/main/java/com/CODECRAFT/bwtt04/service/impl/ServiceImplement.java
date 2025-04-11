package com.CODECRAFT.bwtt04.service.impl;

import com.CODECRAFT.bwtt04.dto.UserDto;
import com.CODECRAFT.bwtt04.exception.UserNotFoundException;
import com.CODECRAFT.bwtt04.model.User;
import com.CODECRAFT.bwtt04.repository.UserRepository;
import com.CODECRAFT.bwtt04.service.UserService;
import com.CODECRAFT.bwtt04.utility.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @CachePut(value = "users", key = "#result.id")
    @Override
    public UserDto createUser(UserDto userDetails) {
        User user = UserMapper.toEntity(userDetails);
        if (user.getId() == null)
            user.setId(UUID.randomUUID());
        User saveUser = userRepository.save(user);
        return UserMapper.toDto(saveUser);
    }

    @Cacheable(value = "users", key = "#id")
    @Override
    public UserDto getUserBYId(UUID id) {
        User user =userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found : "+id));
        return UserMapper.toDto(user);
    }

    @Cacheable(value = "users", key = "all_users")
    @Override
    public List<UserDto> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @CachePut(value = "users", key = "#user.id")
    @Override
    public UserDto updateUser(UUID id, UserDto userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User id not found "+id));
        if (userDetails.getName() != null)
            existingUser.setName(userDetails.getName());
        if (userDetails.getEmail() != null)
            existingUser.setEmail(userDetails.getEmail());
        if (userDetails.getAge() != 0)
            existingUser.setAge(userDetails.getAge());
        User updateUserDetails = userRepository.save(existingUser);
        return UserMapper.toDto(updateUserDetails);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void deleteUser(UUID id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User id not found " + id));
        userRepository.deleteById(id);
    }
}
