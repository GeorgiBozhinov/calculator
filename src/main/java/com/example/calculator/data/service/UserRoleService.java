package com.example.calculator.data.service;
import com.example.calculator.data.enums.UserRoleEnum;
import com.example.calculator.data.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {

        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRoleEnum> returnAllUserRoles(){
        return userRoleRepository.getAll();
    }

}
