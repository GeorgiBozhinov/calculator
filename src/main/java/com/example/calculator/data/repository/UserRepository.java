package com.example.calculator.data.repository;

import com.example.calculator.data.base_entities.UserEntity;
import com.example.calculator.data.interfaces.UserInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByUsername(String username);

    @Query(value = "SELECT username, first_name as firstName, last_name as lastName FROM users", nativeQuery = true)
    List<UserInterface> findAllUsers();

}
