package com.example.calculator.data.repository;
import com.example.calculator.data.base_entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    @Query(value = "SELECT username FROM users WHERE username = ?1", nativeQuery = true)
//    String findByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

}
