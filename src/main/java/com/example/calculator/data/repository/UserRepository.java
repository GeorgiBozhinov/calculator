package com.example.calculator.data.repository;

import com.example.calculator.data.base_entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE UserEntity u SET u.username = :username, u.firstName = :firstName, u.lastName = :lastName WHERE u.id = :id")
    void updateUser(@Param(value = "id") long id, @Param(value = "username") String username, @Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE UserEntity u WHERE u.id = :id")
    void deleteUser(@Param(value = "id") long id);
}
