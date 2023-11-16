package com.photo.dao;

import com.photo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByIdAndIsDeleted(Long id, Boolean isDeleted);
}
