package com.example.calculator.data.repository;
import com.example.calculator.data.base_entities.UserRoleEntity;
import com.example.calculator.data.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByUserRole(UserRoleEnum userRole);
}
