package com.example.calculator.data.repository;

import com.example.calculator.data.base_entities.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;


    @Test
    public void checkIfFindByUsernameWillReturnValueIfExist(){
        String username = "admin_joro";

        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        Assert.assertEquals(username, userEntity);
    }

}
