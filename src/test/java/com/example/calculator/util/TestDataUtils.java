package com.example.calculator.util;
import com.example.calculator.data.base_entities.UserEntity;
import com.example.calculator.data.base_entities.UserRoleEntity;
import com.example.calculator.data.enums.UserRoleEnum;
import com.example.calculator.data.repository.UserRepository;
import com.example.calculator.data.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class TestDataUtils {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;

    public TestDataUtils(UserRepository userRepository, UserRoleRepository userRoleRepository) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    private void initRoles() {

        if ( userRoleRepository.count() == 0 ) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestAdmin(String username) {

        initRoles();

        var admin = new UserEntity()
                .setUsername(username)
                .setFirstName("Test")
                .setLastName("Test")
                .setUserRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String username) {

        initRoles();

        var user = new UserEntity()
                .setUsername(username)
                .setFirstName("Test")
                .setLastName("Test")
                .setUserRoles(userRoleRepository.findAll());

        return userRepository.save(user);
    }


    public void cleanUpDatabase() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

}
