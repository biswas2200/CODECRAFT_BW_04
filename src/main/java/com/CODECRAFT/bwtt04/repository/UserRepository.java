package com.CODECRAFT.bwtt04.repository;

import com.CODECRAFT.bwtt04.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
