package com.photo.dao;

import com.photo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByIdAndIsDeleted(Long id, Boolean isDeleted);

    @Query(value = "select * from users us where us.email = ?1", nativeQuery = true)
    List<User> findAllByEmail(String email);
}
